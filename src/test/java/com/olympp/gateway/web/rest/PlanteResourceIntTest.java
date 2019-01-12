package com.olympp.gateway.web.rest;

import com.olympp.gateway.GatewayApp;

import com.olympp.gateway.domain.Plante;
import com.olympp.gateway.domain.ClassificationCronquist;
import com.olympp.gateway.repository.PlanteRepository;
import com.olympp.gateway.service.PlanteService;
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
 * Test class for the PlanteResource REST controller.
 *
 * @see PlanteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApp.class)
public class PlanteResourceIntTest {

    private static final String DEFAULT_PH_MIN = "4,1";
    private static final String UPDATED_PH_MIN = "5,6";

    private static final String DEFAULT_PH_MAX = "3,2";
    private static final String UPDATED_PH_MAX = "4,3";

    private static final Integer DEFAULT_TEMP_MIN = 1;
    private static final Integer UPDATED_TEMP_MIN = 2;

    private static final Integer DEFAULT_TEMP_MAX = 1;
    private static final Integer UPDATED_TEMP_MAX = 2;

    @Autowired
    private PlanteRepository planteRepository;

    @Autowired
    private PlanteService planteService;

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

    private MockMvc restPlanteMockMvc;

    private Plante plante;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlanteResource planteResource = new PlanteResource(planteService);
        this.restPlanteMockMvc = MockMvcBuilders.standaloneSetup(planteResource)
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
    public static Plante createEntity(EntityManager em) {
        Plante plante = new Plante()
            .phMin(DEFAULT_PH_MIN)
            .phMax(DEFAULT_PH_MAX)
            .tempMin(DEFAULT_TEMP_MIN)
            .tempMax(DEFAULT_TEMP_MAX);
        // Add required entity
        ClassificationCronquist classificationCronquist = ClassificationCronquistResourceIntTest.createEntity(em);
        em.persist(classificationCronquist);
        em.flush();
        plante.setClassificationCronquist(classificationCronquist);
        return plante;
    }

    @Before
    public void initTest() {
        plante = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlante() throws Exception {
        int databaseSizeBeforeCreate = planteRepository.findAll().size();

        // Create the Plante
        restPlanteMockMvc.perform(post("/api/plantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plante)))
            .andExpect(status().isCreated());

        // Validate the Plante in the database
        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeCreate + 1);
        Plante testPlante = planteList.get(planteList.size() - 1);
        assertThat(testPlante.getPhMin()).isEqualTo(DEFAULT_PH_MIN);
        assertThat(testPlante.getPhMax()).isEqualTo(DEFAULT_PH_MAX);
        assertThat(testPlante.getTempMin()).isEqualTo(DEFAULT_TEMP_MIN);
        assertThat(testPlante.getTempMax()).isEqualTo(DEFAULT_TEMP_MAX);
    }

    @Test
    @Transactional
    public void createPlanteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planteRepository.findAll().size();

        // Create the Plante with an existing ID
        plante.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanteMockMvc.perform(post("/api/plantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plante)))
            .andExpect(status().isBadRequest());

        // Validate the Plante in the database
        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPlantes() throws Exception {
        // Initialize the database
        planteRepository.saveAndFlush(plante);

        // Get all the planteList
        restPlanteMockMvc.perform(get("/api/plantes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(plante.getId().intValue())))
            .andExpect(jsonPath("$.[*].phMin").value(hasItem(DEFAULT_PH_MIN.toString())))
            .andExpect(jsonPath("$.[*].phMax").value(hasItem(DEFAULT_PH_MAX.toString())))
            .andExpect(jsonPath("$.[*].tempMin").value(hasItem(DEFAULT_TEMP_MIN)))
            .andExpect(jsonPath("$.[*].tempMax").value(hasItem(DEFAULT_TEMP_MAX)));
    }
    
    @Test
    @Transactional
    public void getPlante() throws Exception {
        // Initialize the database
        planteRepository.saveAndFlush(plante);

        // Get the plante
        restPlanteMockMvc.perform(get("/api/plantes/{id}", plante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(plante.getId().intValue()))
            .andExpect(jsonPath("$.phMin").value(DEFAULT_PH_MIN.toString()))
            .andExpect(jsonPath("$.phMax").value(DEFAULT_PH_MAX.toString()))
            .andExpect(jsonPath("$.tempMin").value(DEFAULT_TEMP_MIN))
            .andExpect(jsonPath("$.tempMax").value(DEFAULT_TEMP_MAX));
    }

    @Test
    @Transactional
    public void getNonExistingPlante() throws Exception {
        // Get the plante
        restPlanteMockMvc.perform(get("/api/plantes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlante() throws Exception {
        // Initialize the database
        planteService.save(plante);

        int databaseSizeBeforeUpdate = planteRepository.findAll().size();

        // Update the plante
        Plante updatedPlante = planteRepository.findById(plante.getId()).get();
        // Disconnect from session so that the updates on updatedPlante are not directly saved in db
        em.detach(updatedPlante);
        updatedPlante
            .phMin(UPDATED_PH_MIN)
            .phMax(UPDATED_PH_MAX)
            .tempMin(UPDATED_TEMP_MIN)
            .tempMax(UPDATED_TEMP_MAX);

        restPlanteMockMvc.perform(put("/api/plantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPlante)))
            .andExpect(status().isOk());

        // Validate the Plante in the database
        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeUpdate);
        Plante testPlante = planteList.get(planteList.size() - 1);
        assertThat(testPlante.getPhMin()).isEqualTo(UPDATED_PH_MIN);
        assertThat(testPlante.getPhMax()).isEqualTo(UPDATED_PH_MAX);
        assertThat(testPlante.getTempMin()).isEqualTo(UPDATED_TEMP_MIN);
        assertThat(testPlante.getTempMax()).isEqualTo(UPDATED_TEMP_MAX);
    }

    @Test
    @Transactional
    public void updateNonExistingPlante() throws Exception {
        int databaseSizeBeforeUpdate = planteRepository.findAll().size();

        // Create the Plante

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanteMockMvc.perform(put("/api/plantes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(plante)))
            .andExpect(status().isBadRequest());

        // Validate the Plante in the database
        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlante() throws Exception {
        // Initialize the database
        planteService.save(plante);

        int databaseSizeBeforeDelete = planteRepository.findAll().size();

        // Get the plante
        restPlanteMockMvc.perform(delete("/api/plantes/{id}", plante.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Plante> planteList = planteRepository.findAll();
        assertThat(planteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Plante.class);
        Plante plante1 = new Plante();
        plante1.setId(1L);
        Plante plante2 = new Plante();
        plante2.setId(plante1.getId());
        assertThat(plante1).isEqualTo(plante2);
        plante2.setId(2L);
        assertThat(plante1).isNotEqualTo(plante2);
        plante1.setId(null);
        assertThat(plante1).isNotEqualTo(plante2);
    }
}
