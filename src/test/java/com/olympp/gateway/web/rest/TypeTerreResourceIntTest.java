package com.olympp.gateway.web.rest;

import com.olympp.gateway.GatewayApp;

import com.olympp.gateway.domain.TypeTerre;
import com.olympp.gateway.repository.TypeTerreRepository;
import com.olympp.gateway.service.TypeTerreService;
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
 * Test class for the TypeTerreResource REST controller.
 *
 * @see TypeTerreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApp.class)
public class TypeTerreResourceIntTest {

    private static final String DEFAULT_TYPE_TERRE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_TERRE = "BBBBBBBBBB";

    @Autowired
    private TypeTerreRepository typeTerreRepository;

    @Autowired
    private TypeTerreService typeTerreService;

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

    private MockMvc restTypeTerreMockMvc;

    private TypeTerre typeTerre;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TypeTerreResource typeTerreResource = new TypeTerreResource(typeTerreService);
        this.restTypeTerreMockMvc = MockMvcBuilders.standaloneSetup(typeTerreResource)
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
    public static TypeTerre createEntity(EntityManager em) {
        TypeTerre typeTerre = new TypeTerre()
            .typeTerre(DEFAULT_TYPE_TERRE);
        return typeTerre;
    }

    @Before
    public void initTest() {
        typeTerre = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeTerre() throws Exception {
        int databaseSizeBeforeCreate = typeTerreRepository.findAll().size();

        // Create the TypeTerre
        restTypeTerreMockMvc.perform(post("/api/type-terres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeTerre)))
            .andExpect(status().isCreated());

        // Validate the TypeTerre in the database
        List<TypeTerre> typeTerreList = typeTerreRepository.findAll();
        assertThat(typeTerreList).hasSize(databaseSizeBeforeCreate + 1);
        TypeTerre testTypeTerre = typeTerreList.get(typeTerreList.size() - 1);
        assertThat(testTypeTerre.getTypeTerre()).isEqualTo(DEFAULT_TYPE_TERRE);
    }

    @Test
    @Transactional
    public void createTypeTerreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeTerreRepository.findAll().size();

        // Create the TypeTerre with an existing ID
        typeTerre.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeTerreMockMvc.perform(post("/api/type-terres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeTerre)))
            .andExpect(status().isBadRequest());

        // Validate the TypeTerre in the database
        List<TypeTerre> typeTerreList = typeTerreRepository.findAll();
        assertThat(typeTerreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTypeTerres() throws Exception {
        // Initialize the database
        typeTerreRepository.saveAndFlush(typeTerre);

        // Get all the typeTerreList
        restTypeTerreMockMvc.perform(get("/api/type-terres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeTerre.getId().intValue())))
            .andExpect(jsonPath("$.[*].typeTerre").value(hasItem(DEFAULT_TYPE_TERRE.toString())));
    }
    
    @Test
    @Transactional
    public void getTypeTerre() throws Exception {
        // Initialize the database
        typeTerreRepository.saveAndFlush(typeTerre);

        // Get the typeTerre
        restTypeTerreMockMvc.perform(get("/api/type-terres/{id}", typeTerre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(typeTerre.getId().intValue()))
            .andExpect(jsonPath("$.typeTerre").value(DEFAULT_TYPE_TERRE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTypeTerre() throws Exception {
        // Get the typeTerre
        restTypeTerreMockMvc.perform(get("/api/type-terres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeTerre() throws Exception {
        // Initialize the database
        typeTerreService.save(typeTerre);

        int databaseSizeBeforeUpdate = typeTerreRepository.findAll().size();

        // Update the typeTerre
        TypeTerre updatedTypeTerre = typeTerreRepository.findById(typeTerre.getId()).get();
        // Disconnect from session so that the updates on updatedTypeTerre are not directly saved in db
        em.detach(updatedTypeTerre);
        updatedTypeTerre
            .typeTerre(UPDATED_TYPE_TERRE);

        restTypeTerreMockMvc.perform(put("/api/type-terres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTypeTerre)))
            .andExpect(status().isOk());

        // Validate the TypeTerre in the database
        List<TypeTerre> typeTerreList = typeTerreRepository.findAll();
        assertThat(typeTerreList).hasSize(databaseSizeBeforeUpdate);
        TypeTerre testTypeTerre = typeTerreList.get(typeTerreList.size() - 1);
        assertThat(testTypeTerre.getTypeTerre()).isEqualTo(UPDATED_TYPE_TERRE);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeTerre() throws Exception {
        int databaseSizeBeforeUpdate = typeTerreRepository.findAll().size();

        // Create the TypeTerre

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeTerreMockMvc.perform(put("/api/type-terres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(typeTerre)))
            .andExpect(status().isBadRequest());

        // Validate the TypeTerre in the database
        List<TypeTerre> typeTerreList = typeTerreRepository.findAll();
        assertThat(typeTerreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeTerre() throws Exception {
        // Initialize the database
        typeTerreService.save(typeTerre);

        int databaseSizeBeforeDelete = typeTerreRepository.findAll().size();

        // Get the typeTerre
        restTypeTerreMockMvc.perform(delete("/api/type-terres/{id}", typeTerre.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TypeTerre> typeTerreList = typeTerreRepository.findAll();
        assertThat(typeTerreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeTerre.class);
        TypeTerre typeTerre1 = new TypeTerre();
        typeTerre1.setId(1L);
        TypeTerre typeTerre2 = new TypeTerre();
        typeTerre2.setId(typeTerre1.getId());
        assertThat(typeTerre1).isEqualTo(typeTerre2);
        typeTerre2.setId(2L);
        assertThat(typeTerre1).isNotEqualTo(typeTerre2);
        typeTerre1.setId(null);
        assertThat(typeTerre1).isNotEqualTo(typeTerre2);
    }
}
