package com.olympp.gateway.web.rest;

import com.olympp.gateway.GatewayApp;

import com.olympp.gateway.domain.VitesseCroissance;
import com.olympp.gateway.repository.VitesseCroissanceRepository;
import com.olympp.gateway.service.VitesseCroissanceService;
import com.olympp.gateway.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static com.olympp.gateway.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VitesseCroissanceResource REST controller.
 *
 * @see VitesseCroissanceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApp.class)
public class VitesseCroissanceResourceIntTest {

    private static final String DEFAULT_VITESSE_CROISSANCE = "AAAAAAAAAA";
    private static final String UPDATED_VITESSE_CROISSANCE = "BBBBBBBBBB";

    @Autowired
    private VitesseCroissanceRepository vitesseCroissanceRepository;

    @Autowired
    private VitesseCroissanceService vitesseCroissanceService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restVitesseCroissanceMockMvc;

    private VitesseCroissance vitesseCroissance;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VitesseCroissanceResource vitesseCroissanceResource = new VitesseCroissanceResource(vitesseCroissanceService);
        this.restVitesseCroissanceMockMvc = MockMvcBuilders.standaloneSetup(vitesseCroissanceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VitesseCroissance createEntity(EntityManager em) {
        VitesseCroissance vitesseCroissance = new VitesseCroissance()
            .vitesseCroissance(DEFAULT_VITESSE_CROISSANCE);
        return vitesseCroissance;
    }

    @Before
    public void initTest() {
        vitesseCroissance = createEntity(em);
    }

    @Test
    @Transactional
    public void createVitesseCroissance() throws Exception {
        int databaseSizeBeforeCreate = vitesseCroissanceRepository.findAll().size();

        // Create the VitesseCroissance
        restVitesseCroissanceMockMvc.perform(post("/api/vitesse-croissances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vitesseCroissance)))
            .andExpect(status().isCreated());

        // Validate the VitesseCroissance in the database
        List<VitesseCroissance> vitesseCroissanceList = vitesseCroissanceRepository.findAll();
        assertThat(vitesseCroissanceList).hasSize(databaseSizeBeforeCreate + 1);
        VitesseCroissance testVitesseCroissance = vitesseCroissanceList.get(vitesseCroissanceList.size() - 1);
        assertThat(testVitesseCroissance.getVitesseCroissance()).isEqualTo(DEFAULT_VITESSE_CROISSANCE);
    }

    @Test
    @Transactional
    public void createVitesseCroissanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vitesseCroissanceRepository.findAll().size();

        // Create the VitesseCroissance with an existing ID
        vitesseCroissance.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVitesseCroissanceMockMvc.perform(post("/api/vitesse-croissances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vitesseCroissance)))
            .andExpect(status().isBadRequest());

        // Validate the VitesseCroissance in the database
        List<VitesseCroissance> vitesseCroissanceList = vitesseCroissanceRepository.findAll();
        assertThat(vitesseCroissanceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllVitesseCroissances() throws Exception {
        // Initialize the database
        vitesseCroissanceRepository.saveAndFlush(vitesseCroissance);

        // Get all the vitesseCroissanceList
        restVitesseCroissanceMockMvc.perform(get("/api/vitesse-croissances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vitesseCroissance.getId().intValue())))
            .andExpect(jsonPath("$.[*].vitesseCroissance").value(hasItem(DEFAULT_VITESSE_CROISSANCE.toString())));
    }
    
    @Test
    @Transactional
    public void getVitesseCroissance() throws Exception {
        // Initialize the database
        vitesseCroissanceRepository.saveAndFlush(vitesseCroissance);

        // Get the vitesseCroissance
        restVitesseCroissanceMockMvc.perform(get("/api/vitesse-croissances/{id}", vitesseCroissance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vitesseCroissance.getId().intValue()))
            .andExpect(jsonPath("$.vitesseCroissance").value(DEFAULT_VITESSE_CROISSANCE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVitesseCroissance() throws Exception {
        // Get the vitesseCroissance
        restVitesseCroissanceMockMvc.perform(get("/api/vitesse-croissances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVitesseCroissance() throws Exception {
        // Initialize the database
        vitesseCroissanceService.save(vitesseCroissance);

        int databaseSizeBeforeUpdate = vitesseCroissanceRepository.findAll().size();

        // Update the vitesseCroissance
        VitesseCroissance updatedVitesseCroissance = vitesseCroissanceRepository.findById(vitesseCroissance.getId()).get();
        // Disconnect from session so that the updates on updatedVitesseCroissance are not directly saved in db
        em.detach(updatedVitesseCroissance);
        updatedVitesseCroissance
            .vitesseCroissance(UPDATED_VITESSE_CROISSANCE);

        restVitesseCroissanceMockMvc.perform(put("/api/vitesse-croissances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVitesseCroissance)))
            .andExpect(status().isOk());

        // Validate the VitesseCroissance in the database
        List<VitesseCroissance> vitesseCroissanceList = vitesseCroissanceRepository.findAll();
        assertThat(vitesseCroissanceList).hasSize(databaseSizeBeforeUpdate);
        VitesseCroissance testVitesseCroissance = vitesseCroissanceList.get(vitesseCroissanceList.size() - 1);
        assertThat(testVitesseCroissance.getVitesseCroissance()).isEqualTo(UPDATED_VITESSE_CROISSANCE);
    }

    @Test
    @Transactional
    public void updateNonExistingVitesseCroissance() throws Exception {
        int databaseSizeBeforeUpdate = vitesseCroissanceRepository.findAll().size();

        // Create the VitesseCroissance

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVitesseCroissanceMockMvc.perform(put("/api/vitesse-croissances")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vitesseCroissance)))
            .andExpect(status().isBadRequest());

        // Validate the VitesseCroissance in the database
        List<VitesseCroissance> vitesseCroissanceList = vitesseCroissanceRepository.findAll();
        assertThat(vitesseCroissanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVitesseCroissance() throws Exception {
        // Initialize the database
        vitesseCroissanceService.save(vitesseCroissance);

        int databaseSizeBeforeDelete = vitesseCroissanceRepository.findAll().size();

        // Get the vitesseCroissance
        restVitesseCroissanceMockMvc.perform(delete("/api/vitesse-croissances/{id}", vitesseCroissance.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<VitesseCroissance> vitesseCroissanceList = vitesseCroissanceRepository.findAll();
        assertThat(vitesseCroissanceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VitesseCroissance.class);
        VitesseCroissance vitesseCroissance1 = new VitesseCroissance();
        vitesseCroissance1.setId(1L);
        VitesseCroissance vitesseCroissance2 = new VitesseCroissance();
        vitesseCroissance2.setId(vitesseCroissance1.getId());
        assertThat(vitesseCroissance1).isEqualTo(vitesseCroissance2);
        vitesseCroissance2.setId(2L);
        assertThat(vitesseCroissance1).isNotEqualTo(vitesseCroissance2);
        vitesseCroissance1.setId(null);
        assertThat(vitesseCroissance1).isNotEqualTo(vitesseCroissance2);
    }
}
