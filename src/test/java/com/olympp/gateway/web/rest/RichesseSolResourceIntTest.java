package com.olympp.gateway.web.rest;

import com.olympp.gateway.GatewayApp;

import com.olympp.gateway.domain.RichesseSol;
import com.olympp.gateway.repository.RichesseSolRepository;
import com.olympp.gateway.service.RichesseSolService;
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
 * Test class for the RichesseSolResource REST controller.
 *
 * @see RichesseSolResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApp.class)
public class RichesseSolResourceIntTest {

    private static final String DEFAULT_RICHESSE_SOL = "AAAAAAAAAA";
    private static final String UPDATED_RICHESSE_SOL = "BBBBBBBBBB";

    @Autowired
    private RichesseSolRepository richesseSolRepository;

    @Autowired
    private RichesseSolService richesseSolService;

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

    private MockMvc restRichesseSolMockMvc;

    private RichesseSol richesseSol;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RichesseSolResource richesseSolResource = new RichesseSolResource(richesseSolService);
        this.restRichesseSolMockMvc = MockMvcBuilders.standaloneSetup(richesseSolResource)
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
    public static RichesseSol createEntity(EntityManager em) {
        RichesseSol richesseSol = new RichesseSol()
            .richesseSol(DEFAULT_RICHESSE_SOL);
        return richesseSol;
    }

    @Before
    public void initTest() {
        richesseSol = createEntity(em);
    }

    @Test
    @Transactional
    public void createRichesseSol() throws Exception {
        int databaseSizeBeforeCreate = richesseSolRepository.findAll().size();

        // Create the RichesseSol
        restRichesseSolMockMvc.perform(post("/api/richesse-sols")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(richesseSol)))
            .andExpect(status().isCreated());

        // Validate the RichesseSol in the database
        List<RichesseSol> richesseSolList = richesseSolRepository.findAll();
        assertThat(richesseSolList).hasSize(databaseSizeBeforeCreate + 1);
        RichesseSol testRichesseSol = richesseSolList.get(richesseSolList.size() - 1);
        assertThat(testRichesseSol.getRichesseSol()).isEqualTo(DEFAULT_RICHESSE_SOL);
    }

    @Test
    @Transactional
    public void createRichesseSolWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = richesseSolRepository.findAll().size();

        // Create the RichesseSol with an existing ID
        richesseSol.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRichesseSolMockMvc.perform(post("/api/richesse-sols")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(richesseSol)))
            .andExpect(status().isBadRequest());

        // Validate the RichesseSol in the database
        List<RichesseSol> richesseSolList = richesseSolRepository.findAll();
        assertThat(richesseSolList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRichesseSols() throws Exception {
        // Initialize the database
        richesseSolRepository.saveAndFlush(richesseSol);

        // Get all the richesseSolList
        restRichesseSolMockMvc.perform(get("/api/richesse-sols?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(richesseSol.getId().intValue())))
            .andExpect(jsonPath("$.[*].richesseSol").value(hasItem(DEFAULT_RICHESSE_SOL.toString())));
    }
    
    @Test
    @Transactional
    public void getRichesseSol() throws Exception {
        // Initialize the database
        richesseSolRepository.saveAndFlush(richesseSol);

        // Get the richesseSol
        restRichesseSolMockMvc.perform(get("/api/richesse-sols/{id}", richesseSol.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(richesseSol.getId().intValue()))
            .andExpect(jsonPath("$.richesseSol").value(DEFAULT_RICHESSE_SOL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRichesseSol() throws Exception {
        // Get the richesseSol
        restRichesseSolMockMvc.perform(get("/api/richesse-sols/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRichesseSol() throws Exception {
        // Initialize the database
        richesseSolService.save(richesseSol);

        int databaseSizeBeforeUpdate = richesseSolRepository.findAll().size();

        // Update the richesseSol
        RichesseSol updatedRichesseSol = richesseSolRepository.findById(richesseSol.getId()).get();
        // Disconnect from session so that the updates on updatedRichesseSol are not directly saved in db
        em.detach(updatedRichesseSol);
        updatedRichesseSol
            .richesseSol(UPDATED_RICHESSE_SOL);

        restRichesseSolMockMvc.perform(put("/api/richesse-sols")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRichesseSol)))
            .andExpect(status().isOk());

        // Validate the RichesseSol in the database
        List<RichesseSol> richesseSolList = richesseSolRepository.findAll();
        assertThat(richesseSolList).hasSize(databaseSizeBeforeUpdate);
        RichesseSol testRichesseSol = richesseSolList.get(richesseSolList.size() - 1);
        assertThat(testRichesseSol.getRichesseSol()).isEqualTo(UPDATED_RICHESSE_SOL);
    }

    @Test
    @Transactional
    public void updateNonExistingRichesseSol() throws Exception {
        int databaseSizeBeforeUpdate = richesseSolRepository.findAll().size();

        // Create the RichesseSol

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRichesseSolMockMvc.perform(put("/api/richesse-sols")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(richesseSol)))
            .andExpect(status().isBadRequest());

        // Validate the RichesseSol in the database
        List<RichesseSol> richesseSolList = richesseSolRepository.findAll();
        assertThat(richesseSolList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRichesseSol() throws Exception {
        // Initialize the database
        richesseSolService.save(richesseSol);

        int databaseSizeBeforeDelete = richesseSolRepository.findAll().size();

        // Get the richesseSol
        restRichesseSolMockMvc.perform(delete("/api/richesse-sols/{id}", richesseSol.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RichesseSol> richesseSolList = richesseSolRepository.findAll();
        assertThat(richesseSolList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RichesseSol.class);
        RichesseSol richesseSol1 = new RichesseSol();
        richesseSol1.setId(1L);
        RichesseSol richesseSol2 = new RichesseSol();
        richesseSol2.setId(richesseSol1.getId());
        assertThat(richesseSol1).isEqualTo(richesseSol2);
        richesseSol2.setId(2L);
        assertThat(richesseSol1).isNotEqualTo(richesseSol2);
        richesseSol1.setId(null);
        assertThat(richesseSol1).isNotEqualTo(richesseSol2);
    }
}
