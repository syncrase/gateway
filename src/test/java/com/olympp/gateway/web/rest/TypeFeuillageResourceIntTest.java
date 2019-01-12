package com.olympp.gateway.web.rest;

import com.olympp.gateway.GatewayApp;

import com.olympp.gateway.domain.TypeFeuillage;
import com.olympp.gateway.repository.TypeFeuillageRepository;
import com.olympp.gateway.service.TypeFeuillageService;
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
 * Test class for the TypeFeuillageResource REST controller.
 *
 * @see TypeFeuillageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApp.class)
public class TypeFeuillageResourceIntTest {

    private static final String DEFAULT_TYPE_FEUILLAGE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_FEUILLAGE = "BBBBBBBBBB";

    @Autowired
    private TypeFeuillageRepository typeFeuillageRepository;

    @Autowired
    private TypeFeuillageService typeFeuillageService;

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

    private MockMvc restTypeFeuillageMockMvc;

    private TypeFeuillage typeFeuillage;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeFeuillageResource typeFeuillageResource = new TypeFeuillageResource(typeFeuillageService);
        this.restTypeFeuillageMockMvc = MockMvcBuilders.standaloneSetup(typeFeuillageResource)
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
    public static TypeFeuillage createEntity(EntityManager em) {
        TypeFeuillage typeFeuillage = new TypeFeuillage()
            .typeFeuillage(DEFAULT_TYPE_FEUILLAGE);
        return typeFeuillage;
    }

    @Before
    public void initTest() {
        typeFeuillage = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeFeuillage() throws Exception {
        int databaseSizeBeforeCreate = typeFeuillageRepository.findAll().size();

        // Create the TypeFeuillage
        restTypeFeuillageMockMvc.perform(post("/api/type-feuillages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeFeuillage)))
            .andExpect(status().isCreated());

        // Validate the TypeFeuillage in the database
        List<TypeFeuillage> typeFeuillageList = typeFeuillageRepository.findAll();
        assertThat(typeFeuillageList).hasSize(databaseSizeBeforeCreate + 1);
        TypeFeuillage testTypeFeuillage = typeFeuillageList.get(typeFeuillageList.size() - 1);
        assertThat(testTypeFeuillage.getTypeFeuillage()).isEqualTo(DEFAULT_TYPE_FEUILLAGE);
    }

    @Test
    @Transactional
    public void createTypeFeuillageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeFeuillageRepository.findAll().size();

        // Create the TypeFeuillage with an existing ID
        typeFeuillage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeFeuillageMockMvc.perform(post("/api/type-feuillages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeFeuillage)))
            .andExpect(status().isBadRequest());

        // Validate the TypeFeuillage in the database
        List<TypeFeuillage> typeFeuillageList = typeFeuillageRepository.findAll();
        assertThat(typeFeuillageList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTypeFeuillages() throws Exception {
        // Initialize the database
        typeFeuillageRepository.saveAndFlush(typeFeuillage);

        // Get all the typeFeuillageList
        restTypeFeuillageMockMvc.perform(get("/api/type-feuillages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeFeuillage.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeFeuillage").value(hasItem(DEFAULT_TYPE_FEUILLAGE.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeFeuillage() throws Exception {
        // Initialize the database
        typeFeuillageRepository.saveAndFlush(typeFeuillage);

        // Get the typeFeuillage
        restTypeFeuillageMockMvc.perform(get("/api/type-feuillages/{id}", typeFeuillage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeFeuillage.getId().intValue()))
            .andExpect(jsonPath("$.typeFeuillage").value(DEFAULT_TYPE_FEUILLAGE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeFeuillage() throws Exception {
        // Get the typeFeuillage
        restTypeFeuillageMockMvc.perform(get("/api/type-feuillages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeFeuillage() throws Exception {
        // Initialize the database
        typeFeuillageService.save(typeFeuillage);

        int databaseSizeBeforeUpdate = typeFeuillageRepository.findAll().size();

        // Update the typeFeuillage
        TypeFeuillage updatedTypeFeuillage = typeFeuillageRepository.findById(typeFeuillage.getId()).get();
        // Disconnect from session so that the updates on updatedTypeFeuillage are not directly saved in db
        em.detach(updatedTypeFeuillage);
        updatedTypeFeuillage
            .typeFeuillage(UPDATED_TYPE_FEUILLAGE);

        restTypeFeuillageMockMvc.perform(put("/api/type-feuillages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeFeuillage)))
            .andExpect(status().isOk());

        // Validate the TypeFeuillage in the database
        List<TypeFeuillage> typeFeuillageList = typeFeuillageRepository.findAll();
        assertThat(typeFeuillageList).hasSize(databaseSizeBeforeUpdate);
        TypeFeuillage testTypeFeuillage = typeFeuillageList.get(typeFeuillageList.size() - 1);
        assertThat(testTypeFeuillage.getTypeFeuillage()).isEqualTo(UPDATED_TYPE_FEUILLAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeFeuillage() throws Exception {
        int databaseSizeBeforeUpdate = typeFeuillageRepository.findAll().size();

        // Create the TypeFeuillage

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeFeuillageMockMvc.perform(put("/api/type-feuillages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeFeuillage)))
            .andExpect(status().isBadRequest());

        // Validate the TypeFeuillage in the database
        List<TypeFeuillage> typeFeuillageList = typeFeuillageRepository.findAll();
        assertThat(typeFeuillageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeFeuillage() throws Exception {
        // Initialize the database
        typeFeuillageService.save(typeFeuillage);

        int databaseSizeBeforeDelete = typeFeuillageRepository.findAll().size();

        // Get the typeFeuillage
        restTypeFeuillageMockMvc.perform(delete("/api/type-feuillages/{id}", typeFeuillage.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeFeuillage> typeFeuillageList = typeFeuillageRepository.findAll();
        assertThat(typeFeuillageList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeFeuillage.class);
        TypeFeuillage typeFeuillage1 = new TypeFeuillage();
        typeFeuillage1.setId(1L);
        TypeFeuillage typeFeuillage2 = new TypeFeuillage();
        typeFeuillage2.setId(typeFeuillage1.getId());
        assertThat(typeFeuillage1).isEqualTo(typeFeuillage2);
        typeFeuillage2.setId(2L);
        assertThat(typeFeuillage1).isNotEqualTo(typeFeuillage2);
        typeFeuillage1.setId(null);
        assertThat(typeFeuillage1).isNotEqualTo(typeFeuillage2);
    }
}
