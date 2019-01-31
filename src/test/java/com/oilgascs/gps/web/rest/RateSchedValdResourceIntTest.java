package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.RateSchedVald;
import com.oilgascs.gps.repository.RateSchedValdRepository;
import com.oilgascs.gps.service.RateSchedValdService;
import com.oilgascs.gps.service.dto.RateSchedValdDTO;
import com.oilgascs.gps.service.mapper.RateSchedValdMapper;
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
 * Test class for the RateSchedValdResource REST controller.
 *
 * @see RateSchedValdResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class RateSchedValdResourceIntTest {

    private static final String DEFAULT_VALID_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_VALID_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATE_TIME_STAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME_STAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    @Autowired
    private RateSchedValdRepository rateSchedValdRepository;

    @Autowired
    private RateSchedValdMapper rateSchedValdMapper;
    
    @Autowired
    private RateSchedValdService rateSchedValdService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRateSchedValdMockMvc;

    private RateSchedVald rateSchedVald;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RateSchedValdResource rateSchedValdResource = new RateSchedValdResource(rateSchedValdService);
        this.restRateSchedValdMockMvc = MockMvcBuilders.standaloneSetup(rateSchedValdResource)
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
    public static RateSchedVald createEntity(EntityManager em) {
        RateSchedVald rateSchedVald = new RateSchedVald()
            .validType(DEFAULT_VALID_TYPE)
            .updater(DEFAULT_UPDATER)
            .updateTimeStamp(DEFAULT_UPDATE_TIME_STAMP)
            .businessUnit(DEFAULT_BUSINESS_UNIT);
        return rateSchedVald;
    }

    @Before
    public void initTest() {
        rateSchedVald = createEntity(em);
    }

    @Test
    @Transactional
    public void createRateSchedVald() throws Exception {
        int databaseSizeBeforeCreate = rateSchedValdRepository.findAll().size();

        // Create the RateSchedVald
        RateSchedValdDTO rateSchedValdDTO = rateSchedValdMapper.toDto(rateSchedVald);
        restRateSchedValdMockMvc.perform(post("/api/rate-sched-valds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedValdDTO)))
            .andExpect(status().isCreated());

        // Validate the RateSchedVald in the database
        List<RateSchedVald> rateSchedValdList = rateSchedValdRepository.findAll();
        assertThat(rateSchedValdList).hasSize(databaseSizeBeforeCreate + 1);
        RateSchedVald testRateSchedVald = rateSchedValdList.get(rateSchedValdList.size() - 1);
        assertThat(testRateSchedVald.getValidType()).isEqualTo(DEFAULT_VALID_TYPE);
        assertThat(testRateSchedVald.getUpdater()).isEqualTo(DEFAULT_UPDATER);
        assertThat(testRateSchedVald.getUpdateTimeStamp()).isEqualTo(DEFAULT_UPDATE_TIME_STAMP);
        assertThat(testRateSchedVald.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void createRateSchedValdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rateSchedValdRepository.findAll().size();

        // Create the RateSchedVald with an existing ID
        rateSchedVald.setId(1L);
        RateSchedValdDTO rateSchedValdDTO = rateSchedValdMapper.toDto(rateSchedVald);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRateSchedValdMockMvc.perform(post("/api/rate-sched-valds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedValdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RateSchedVald in the database
        List<RateSchedVald> rateSchedValdList = rateSchedValdRepository.findAll();
        assertThat(rateSchedValdList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRateSchedValds() throws Exception {
        // Initialize the database
        rateSchedValdRepository.saveAndFlush(rateSchedVald);

        // Get all the rateSchedValdList
        restRateSchedValdMockMvc.perform(get("/api/rate-sched-valds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rateSchedVald.getId().intValue())))
            .andExpect(jsonPath("$.[*].validType").value(hasItem(DEFAULT_VALID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].updater").value(hasItem(DEFAULT_UPDATER.toString())))
            .andExpect(jsonPath("$.[*].updateTimeStamp").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME_STAMP))))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT.toString())));
    }
    
    @Test
    @Transactional
    public void getRateSchedVald() throws Exception {
        // Initialize the database
        rateSchedValdRepository.saveAndFlush(rateSchedVald);

        // Get the rateSchedVald
        restRateSchedValdMockMvc.perform(get("/api/rate-sched-valds/{id}", rateSchedVald.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rateSchedVald.getId().intValue()))
            .andExpect(jsonPath("$.validType").value(DEFAULT_VALID_TYPE.toString()))
            .andExpect(jsonPath("$.updater").value(DEFAULT_UPDATER.toString()))
            .andExpect(jsonPath("$.updateTimeStamp").value(sameInstant(DEFAULT_UPDATE_TIME_STAMP)))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRateSchedVald() throws Exception {
        // Get the rateSchedVald
        restRateSchedValdMockMvc.perform(get("/api/rate-sched-valds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRateSchedVald() throws Exception {
        // Initialize the database
        rateSchedValdRepository.saveAndFlush(rateSchedVald);

        int databaseSizeBeforeUpdate = rateSchedValdRepository.findAll().size();

        // Update the rateSchedVald
        RateSchedVald updatedRateSchedVald = rateSchedValdRepository.findById(rateSchedVald.getId()).get();
        // Disconnect from session so that the updates on updatedRateSchedVald are not directly saved in db
        em.detach(updatedRateSchedVald);
        updatedRateSchedVald
            .validType(UPDATED_VALID_TYPE)
            .updater(UPDATED_UPDATER)
            .updateTimeStamp(UPDATED_UPDATE_TIME_STAMP)
            .businessUnit(UPDATED_BUSINESS_UNIT);
        RateSchedValdDTO rateSchedValdDTO = rateSchedValdMapper.toDto(updatedRateSchedVald);

        restRateSchedValdMockMvc.perform(put("/api/rate-sched-valds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedValdDTO)))
            .andExpect(status().isOk());

        // Validate the RateSchedVald in the database
        List<RateSchedVald> rateSchedValdList = rateSchedValdRepository.findAll();
        assertThat(rateSchedValdList).hasSize(databaseSizeBeforeUpdate);
        RateSchedVald testRateSchedVald = rateSchedValdList.get(rateSchedValdList.size() - 1);
        assertThat(testRateSchedVald.getValidType()).isEqualTo(UPDATED_VALID_TYPE);
        assertThat(testRateSchedVald.getUpdater()).isEqualTo(UPDATED_UPDATER);
        assertThat(testRateSchedVald.getUpdateTimeStamp()).isEqualTo(UPDATED_UPDATE_TIME_STAMP);
        assertThat(testRateSchedVald.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingRateSchedVald() throws Exception {
        int databaseSizeBeforeUpdate = rateSchedValdRepository.findAll().size();

        // Create the RateSchedVald
        RateSchedValdDTO rateSchedValdDTO = rateSchedValdMapper.toDto(rateSchedVald);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRateSchedValdMockMvc.perform(put("/api/rate-sched-valds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedValdDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RateSchedVald in the database
        List<RateSchedVald> rateSchedValdList = rateSchedValdRepository.findAll();
        assertThat(rateSchedValdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRateSchedVald() throws Exception {
        // Initialize the database
        rateSchedValdRepository.saveAndFlush(rateSchedVald);

        int databaseSizeBeforeDelete = rateSchedValdRepository.findAll().size();

        // Get the rateSchedVald
        restRateSchedValdMockMvc.perform(delete("/api/rate-sched-valds/{id}", rateSchedVald.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RateSchedVald> rateSchedValdList = rateSchedValdRepository.findAll();
        assertThat(rateSchedValdList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RateSchedVald.class);
        RateSchedVald rateSchedVald1 = new RateSchedVald();
        rateSchedVald1.setId(1L);
        RateSchedVald rateSchedVald2 = new RateSchedVald();
        rateSchedVald2.setId(rateSchedVald1.getId());
        assertThat(rateSchedVald1).isEqualTo(rateSchedVald2);
        rateSchedVald2.setId(2L);
        assertThat(rateSchedVald1).isNotEqualTo(rateSchedVald2);
        rateSchedVald1.setId(null);
        assertThat(rateSchedVald1).isNotEqualTo(rateSchedVald2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RateSchedValdDTO.class);
        RateSchedValdDTO rateSchedValdDTO1 = new RateSchedValdDTO();
        rateSchedValdDTO1.setId(1L);
        RateSchedValdDTO rateSchedValdDTO2 = new RateSchedValdDTO();
        assertThat(rateSchedValdDTO1).isNotEqualTo(rateSchedValdDTO2);
        rateSchedValdDTO2.setId(rateSchedValdDTO1.getId());
        assertThat(rateSchedValdDTO1).isEqualTo(rateSchedValdDTO2);
        rateSchedValdDTO2.setId(2L);
        assertThat(rateSchedValdDTO1).isNotEqualTo(rateSchedValdDTO2);
        rateSchedValdDTO1.setId(null);
        assertThat(rateSchedValdDTO1).isNotEqualTo(rateSchedValdDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rateSchedValdMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rateSchedValdMapper.fromId(null)).isNull();
    }
}
