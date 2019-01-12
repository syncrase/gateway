package com.olympp.gateway.web.rest;

import com.olympp.gateway.GatewayApp;

import com.olympp.gateway.domain.Strate;
import com.olympp.gateway.repository.StrateRepository;
import com.olympp.gateway.service.StrateService;
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
 * Test class for the StrateResource REST controller.
 *
 * @see StrateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApp.class)
public class StrateResourceIntTest {

    private static final String DEFAULT_STRATE = "AAAAAAAAAA";
    private static final String UPDATED_STRATE = "BBBBBBBBBB";

    @Autowired
    private StrateRepository strateRepository;

    @Autowired
    private StrateService strateService;

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

    private MockMvc restStrateMockMvc;

    private Strate strate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StrateResource strateResource = new StrateResource(strateService);
        this.restStrateMockMvc = MockMvcBuilders.standaloneSetup(strateResource)
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
    public static Strate createEntity(EntityManager em) {
        Strate strate = new Strate()
            .strate(DEFAULT_STRATE);
        return strate;
    }

    @Before
    public void initTest() {
        strate = createEntity(em);
    }

    @Test
    @Transactional
    public void createStrate() throws Exception {
        int databaseSizeBeforeCreate = strateRepository.findAll().size();

        // Create the Strate
        restStrateMockMvc.perform(post("/api/strates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(strate)))
            .andExpect(status().isCreated());

        // Validate the Strate in the database
        List<Strate> strateList = strateRepository.findAll();
        assertThat(strateList).hasSize(databaseSizeBeforeCreate + 1);
        Strate testStrate = strateList.get(strateList.size() - 1);
        assertThat(testStrate.getStrate()).isEqualTo(DEFAULT_STRATE);
    }

    @Test
    @Transactional
    public void createStrateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = strateRepository.findAll().size();

        // Create the Strate with an existing ID
        strate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStrateMockMvc.perform(post("/api/strates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(strate)))
            .andExpect(status().isBadRequest());

        // Validate the Strate in the database
        List<Strate> strateList = strateRepository.findAll();
        assertThat(strateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllStrates() throws Exception {
        // Initialize the database
        strateRepository.saveAndFlush(strate);

        // Get all the strateList
        restStrateMockMvc.perform(get("/api/strates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(strate.getId().intValue())))
            .andExpect(jsonPath("$.[*].strate").value(hasItem(DEFAULT_STRATE.toString())));
    }
    
    @Test
    @Transactional
    public void getStrate() throws Exception {
        // Initialize the database
        strateRepository.saveAndFlush(strate);

        // Get the strate
        restStrateMockMvc.perform(get("/api/strates/{id}", strate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(strate.getId().intValue()))
            .andExpect(jsonPath("$.strate").value(DEFAULT_STRATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStrate() throws Exception {
        // Get the strate
        restStrateMockMvc.perform(get("/api/strates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStrate() throws Exception {
        // Initialize the database
        strateService.save(strate);

        int databaseSizeBeforeUpdate = strateRepository.findAll().size();

        // Update the strate
        Strate updatedStrate = strateRepository.findById(strate.getId()).get();
        // Disconnect from session so that the updates on updatedStrate are not directly saved in db
        em.detach(updatedStrate);
        updatedStrate
            .strate(UPDATED_STRATE);

        restStrateMockMvc.perform(put("/api/strates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStrate)))
            .andExpect(status().isOk());

        // Validate the Strate in the database
        List<Strate> strateList = strateRepository.findAll();
        assertThat(strateList).hasSize(databaseSizeBeforeUpdate);
        Strate testStrate = strateList.get(strateList.size() - 1);
        assertThat(testStrate.getStrate()).isEqualTo(UPDATED_STRATE);
    }

    @Test
    @Transactional
    public void updateNonExistingStrate() throws Exception {
        int databaseSizeBeforeUpdate = strateRepository.findAll().size();

        // Create the Strate

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStrateMockMvc.perform(put("/api/strates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(strate)))
            .andExpect(status().isBadRequest());

        // Validate the Strate in the database
        List<Strate> strateList = strateRepository.findAll();
        assertThat(strateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStrate() throws Exception {
        // Initialize the database
        strateService.save(strate);

        int databaseSizeBeforeDelete = strateRepository.findAll().size();

        // Get the strate
        restStrateMockMvc.perform(delete("/api/strates/{id}", strate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Strate> strateList = strateRepository.findAll();
        assertThat(strateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Strate.class);
        Strate strate1 = new Strate();
        strate1.setId(1L);
        Strate strate2 = new Strate();
        strate2.setId(strate1.getId());
        assertThat(strate1).isEqualTo(strate2);
        strate2.setId(2L);
        assertThat(strate1).isNotEqualTo(strate2);
        strate1.setId(null);
        assertThat(strate1).isNotEqualTo(strate2);
    }
}
