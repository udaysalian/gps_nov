package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.ContrLoc;
import com.oilgascs.gps.repository.ContrLocRepository;
import com.oilgascs.gps.service.ContrLocService;
import com.oilgascs.gps.service.dto.ContrLocDTO;
import com.oilgascs.gps.service.mapper.ContrLocMapper;
import com.oilgascs.gps.web.rest.errors.ExceptionTranslator;

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

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static com.oilgascs.gps.web.rest.TestUtil.sameInstant;
import static com.oilgascs.gps.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ContrLocResource REST controller.
 *
 * @see ContrLocResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class ContrLocResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITY = 0;
    private static final Integer UPDATED_QUANTITY = 1;

    private static final LocalDate DEFAULT_EFFECTIVE_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVE_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EFFECTIVE_TO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVE_TO_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_UPDATER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATE_TIME_STAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME_STAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    @Autowired
    private ContrLocRepository contrLocRepository;

    @Autowired
    private ContrLocMapper contrLocMapper;
    
    @Autowired
    private ContrLocService contrLocService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContrLocMockMvc;

    private ContrLoc contrLoc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContrLocResource contrLocResource = new ContrLocResource(contrLocService);
        this.restContrLocMockMvc = MockMvcBuilders.standaloneSetup(contrLocResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContrLoc createEntity(EntityManager em) {
        ContrLoc contrLoc = new ContrLoc()
            .type(DEFAULT_TYPE)
            .quantity(DEFAULT_QUANTITY)
            .effectiveFromDate(DEFAULT_EFFECTIVE_FROM_DATE)
            .effectiveToDate(DEFAULT_EFFECTIVE_TO_DATE)
            .updater(DEFAULT_UPDATER)
            .updateTimeStamp(DEFAULT_UPDATE_TIME_STAMP)
            .businessUnit(DEFAULT_BUSINESS_UNIT);
        return contrLoc;
    }

    @Before
    public void initTest() {
        contrLoc = createEntity(em);
    }

    @Test
    @Transactional
    public void createContrLoc() throws Exception {
        int databaseSizeBeforeCreate = contrLocRepository.findAll().size();

        // Create the ContrLoc
        ContrLocDTO contrLocDTO = contrLocMapper.toDto(contrLoc);
        restContrLocMockMvc.perform(post("/api/contr-locs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contrLocDTO)))
            .andExpect(status().isCreated());

        // Validate the ContrLoc in the database
        List<ContrLoc> contrLocList = contrLocRepository.findAll();
        assertThat(contrLocList).hasSize(databaseSizeBeforeCreate + 1);
        ContrLoc testContrLoc = contrLocList.get(contrLocList.size() - 1);
        assertThat(testContrLoc.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testContrLoc.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testContrLoc.getEffectiveFromDate()).isEqualTo(DEFAULT_EFFECTIVE_FROM_DATE);
        assertThat(testContrLoc.getEffectiveToDate()).isEqualTo(DEFAULT_EFFECTIVE_TO_DATE);
        assertThat(testContrLoc.getUpdater()).isEqualTo(DEFAULT_UPDATER);
        assertThat(testContrLoc.getUpdateTimeStamp()).isEqualTo(DEFAULT_UPDATE_TIME_STAMP);
        assertThat(testContrLoc.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void createContrLocWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contrLocRepository.findAll().size();

        // Create the ContrLoc with an existing ID
        contrLoc.setId(1L);
        ContrLocDTO contrLocDTO = contrLocMapper.toDto(contrLoc);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContrLocMockMvc.perform(post("/api/contr-locs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contrLocDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContrLoc in the database
        List<ContrLoc> contrLocList = contrLocRepository.findAll();
        assertThat(contrLocList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = contrLocRepository.findAll().size();
        // set the field null
        contrLoc.setType(null);

        // Create the ContrLoc, which fails.
        ContrLocDTO contrLocDTO = contrLocMapper.toDto(contrLoc);

        restContrLocMockMvc.perform(post("/api/contr-locs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contrLocDTO)))
            .andExpect(status().isBadRequest());

        List<ContrLoc> contrLocList = contrLocRepository.findAll();
        assertThat(contrLocList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllContrLocs() throws Exception {
        // Initialize the database
        contrLocRepository.saveAndFlush(contrLoc);

        // Get all the contrLocList
        restContrLocMockMvc.perform(get("/api/contr-locs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contrLoc.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
            .andExpect(jsonPath("$.[*].effectiveFromDate").value(hasItem(DEFAULT_EFFECTIVE_FROM_DATE.toString())))
            .andExpect(jsonPath("$.[*].effectiveToDate").value(hasItem(DEFAULT_EFFECTIVE_TO_DATE.toString())))
            .andExpect(jsonPath("$.[*].updater").value(hasItem(DEFAULT_UPDATER.toString())))
            .andExpect(jsonPath("$.[*].updateTimeStamp").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME_STAMP))))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT.toString())));
    }
    
    @Test
    @Transactional
    public void getContrLoc() throws Exception {
        // Initialize the database
        contrLocRepository.saveAndFlush(contrLoc);

        // Get the contrLoc
        restContrLocMockMvc.perform(get("/api/contr-locs/{id}", contrLoc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contrLoc.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.effectiveFromDate").value(DEFAULT_EFFECTIVE_FROM_DATE.toString()))
            .andExpect(jsonPath("$.effectiveToDate").value(DEFAULT_EFFECTIVE_TO_DATE.toString()))
            .andExpect(jsonPath("$.updater").value(DEFAULT_UPDATER.toString()))
            .andExpect(jsonPath("$.updateTimeStamp").value(sameInstant(DEFAULT_UPDATE_TIME_STAMP)))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContrLoc() throws Exception {
        // Get the contrLoc
        restContrLocMockMvc.perform(get("/api/contr-locs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContrLoc() throws Exception {
        // Initialize the database
        contrLocRepository.saveAndFlush(contrLoc);

        int databaseSizeBeforeUpdate = contrLocRepository.findAll().size();

        // Update the contrLoc
        ContrLoc updatedContrLoc = contrLocRepository.findById(contrLoc.getId()).get();
        // Disconnect from session so that the updates on updatedContrLoc are not directly saved in db
        em.detach(updatedContrLoc);
        updatedContrLoc
            .type(UPDATED_TYPE)
            .quantity(UPDATED_QUANTITY)
            .effectiveFromDate(UPDATED_EFFECTIVE_FROM_DATE)
            .effectiveToDate(UPDATED_EFFECTIVE_TO_DATE)
            .updater(UPDATED_UPDATER)
            .updateTimeStamp(UPDATED_UPDATE_TIME_STAMP)
            .businessUnit(UPDATED_BUSINESS_UNIT);
        ContrLocDTO contrLocDTO = contrLocMapper.toDto(updatedContrLoc);

        restContrLocMockMvc.perform(put("/api/contr-locs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contrLocDTO)))
            .andExpect(status().isOk());

        // Validate the ContrLoc in the database
        List<ContrLoc> contrLocList = contrLocRepository.findAll();
        assertThat(contrLocList).hasSize(databaseSizeBeforeUpdate);
        ContrLoc testContrLoc = contrLocList.get(contrLocList.size() - 1);
        assertThat(testContrLoc.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testContrLoc.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testContrLoc.getEffectiveFromDate()).isEqualTo(UPDATED_EFFECTIVE_FROM_DATE);
        assertThat(testContrLoc.getEffectiveToDate()).isEqualTo(UPDATED_EFFECTIVE_TO_DATE);
        assertThat(testContrLoc.getUpdater()).isEqualTo(UPDATED_UPDATER);
        assertThat(testContrLoc.getUpdateTimeStamp()).isEqualTo(UPDATED_UPDATE_TIME_STAMP);
        assertThat(testContrLoc.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingContrLoc() throws Exception {
        int databaseSizeBeforeUpdate = contrLocRepository.findAll().size();

        // Create the ContrLoc
        ContrLocDTO contrLocDTO = contrLocMapper.toDto(contrLoc);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContrLocMockMvc.perform(put("/api/contr-locs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contrLocDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContrLoc in the database
        List<ContrLoc> contrLocList = contrLocRepository.findAll();
        assertThat(contrLocList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContrLoc() throws Exception {
        // Initialize the database
        contrLocRepository.saveAndFlush(contrLoc);

        int databaseSizeBeforeDelete = contrLocRepository.findAll().size();

        // Get the contrLoc
        restContrLocMockMvc.perform(delete("/api/contr-locs/{id}", contrLoc.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ContrLoc> contrLocList = contrLocRepository.findAll();
        assertThat(contrLocList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContrLoc.class);
        ContrLoc contrLoc1 = new ContrLoc();
        contrLoc1.setId(1L);
        ContrLoc contrLoc2 = new ContrLoc();
        contrLoc2.setId(contrLoc1.getId());
        assertThat(contrLoc1).isEqualTo(contrLoc2);
        contrLoc2.setId(2L);
        assertThat(contrLoc1).isNotEqualTo(contrLoc2);
        contrLoc1.setId(null);
        assertThat(contrLoc1).isNotEqualTo(contrLoc2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContrLocDTO.class);
        ContrLocDTO contrLocDTO1 = new ContrLocDTO();
        contrLocDTO1.setId(1L);
        ContrLocDTO contrLocDTO2 = new ContrLocDTO();
        assertThat(contrLocDTO1).isNotEqualTo(contrLocDTO2);
        contrLocDTO2.setId(contrLocDTO1.getId());
        assertThat(contrLocDTO1).isEqualTo(contrLocDTO2);
        contrLocDTO2.setId(2L);
        assertThat(contrLocDTO1).isNotEqualTo(contrLocDTO2);
        contrLocDTO1.setId(null);
        assertThat(contrLocDTO1).isNotEqualTo(contrLocDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(contrLocMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(contrLocMapper.fromId(null)).isNull();
    }
}
