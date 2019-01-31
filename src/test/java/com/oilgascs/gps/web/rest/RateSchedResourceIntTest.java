package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.RateSched;
import com.oilgascs.gps.repository.RateSchedRepository;
import com.oilgascs.gps.service.RateSchedService;
import com.oilgascs.gps.service.dto.RateSchedDTO;
import com.oilgascs.gps.service.mapper.RateSchedMapper;
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
 * Test class for the RateSchedResource REST controller.
 *
 * @see RateSchedResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class RateSchedResourceIntTest {

    private static final String DEFAULT_RS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_RS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RATE_SCHEDULE_CD = "AAAAAAAAAA";
    private static final String UPDATED_RATE_SCHEDULE_CD = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATE_TIME_STAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME_STAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    @Autowired
    private RateSchedRepository rateSchedRepository;

    @Autowired
    private RateSchedMapper rateSchedMapper;
    
    @Autowired
    private RateSchedService rateSchedService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRateSchedMockMvc;

    private RateSched rateSched;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RateSchedResource rateSchedResource = new RateSchedResource(rateSchedService);
        this.restRateSchedMockMvc = MockMvcBuilders.standaloneSetup(rateSchedResource)
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
    public static RateSched createEntity(EntityManager em) {
        RateSched rateSched = new RateSched()
            .rsType(DEFAULT_RS_TYPE)
            .rateScheduleCD(DEFAULT_RATE_SCHEDULE_CD)
            .updater(DEFAULT_UPDATER)
            .updateTimeStamp(DEFAULT_UPDATE_TIME_STAMP)
            .businessUnit(DEFAULT_BUSINESS_UNIT);
        return rateSched;
    }

    @Before
    public void initTest() {
        rateSched = createEntity(em);
    }

    @Test
    @Transactional
    public void createRateSched() throws Exception {
        int databaseSizeBeforeCreate = rateSchedRepository.findAll().size();

        // Create the RateSched
        RateSchedDTO rateSchedDTO = rateSchedMapper.toDto(rateSched);
        restRateSchedMockMvc.perform(post("/api/rate-scheds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedDTO)))
            .andExpect(status().isCreated());

        // Validate the RateSched in the database
        List<RateSched> rateSchedList = rateSchedRepository.findAll();
        assertThat(rateSchedList).hasSize(databaseSizeBeforeCreate + 1);
        RateSched testRateSched = rateSchedList.get(rateSchedList.size() - 1);
        assertThat(testRateSched.getRsType()).isEqualTo(DEFAULT_RS_TYPE);
        assertThat(testRateSched.getRateScheduleCD()).isEqualTo(DEFAULT_RATE_SCHEDULE_CD);
        assertThat(testRateSched.getUpdater()).isEqualTo(DEFAULT_UPDATER);
        assertThat(testRateSched.getUpdateTimeStamp()).isEqualTo(DEFAULT_UPDATE_TIME_STAMP);
        assertThat(testRateSched.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void createRateSchedWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rateSchedRepository.findAll().size();

        // Create the RateSched with an existing ID
        rateSched.setId(1L);
        RateSchedDTO rateSchedDTO = rateSchedMapper.toDto(rateSched);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRateSchedMockMvc.perform(post("/api/rate-scheds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RateSched in the database
        List<RateSched> rateSchedList = rateSchedRepository.findAll();
        assertThat(rateSchedList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRsTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = rateSchedRepository.findAll().size();
        // set the field null
        rateSched.setRsType(null);

        // Create the RateSched, which fails.
        RateSchedDTO rateSchedDTO = rateSchedMapper.toDto(rateSched);

        restRateSchedMockMvc.perform(post("/api/rate-scheds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedDTO)))
            .andExpect(status().isBadRequest());

        List<RateSched> rateSchedList = rateSchedRepository.findAll();
        assertThat(rateSchedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateScheduleCDIsRequired() throws Exception {
        int databaseSizeBeforeTest = rateSchedRepository.findAll().size();
        // set the field null
        rateSched.setRateScheduleCD(null);

        // Create the RateSched, which fails.
        RateSchedDTO rateSchedDTO = rateSchedMapper.toDto(rateSched);

        restRateSchedMockMvc.perform(post("/api/rate-scheds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedDTO)))
            .andExpect(status().isBadRequest());

        List<RateSched> rateSchedList = rateSchedRepository.findAll();
        assertThat(rateSchedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusinessUnitIsRequired() throws Exception {
        int databaseSizeBeforeTest = rateSchedRepository.findAll().size();
        // set the field null
        rateSched.setBusinessUnit(null);

        // Create the RateSched, which fails.
        RateSchedDTO rateSchedDTO = rateSchedMapper.toDto(rateSched);

        restRateSchedMockMvc.perform(post("/api/rate-scheds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedDTO)))
            .andExpect(status().isBadRequest());

        List<RateSched> rateSchedList = rateSchedRepository.findAll();
        assertThat(rateSchedList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRateScheds() throws Exception {
        // Initialize the database
        rateSchedRepository.saveAndFlush(rateSched);

        // Get all the rateSchedList
        restRateSchedMockMvc.perform(get("/api/rate-scheds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rateSched.getId().intValue())))
            .andExpect(jsonPath("$.[*].rsType").value(hasItem(DEFAULT_RS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].rateScheduleCD").value(hasItem(DEFAULT_RATE_SCHEDULE_CD.toString())))
            .andExpect(jsonPath("$.[*].updater").value(hasItem(DEFAULT_UPDATER.toString())))
            .andExpect(jsonPath("$.[*].updateTimeStamp").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME_STAMP))))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT.toString())));
    }
    
    @Test
    @Transactional
    public void getRateSched() throws Exception {
        // Initialize the database
        rateSchedRepository.saveAndFlush(rateSched);

        // Get the rateSched
        restRateSchedMockMvc.perform(get("/api/rate-scheds/{id}", rateSched.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rateSched.getId().intValue()))
            .andExpect(jsonPath("$.rsType").value(DEFAULT_RS_TYPE.toString()))
            .andExpect(jsonPath("$.rateScheduleCD").value(DEFAULT_RATE_SCHEDULE_CD.toString()))
            .andExpect(jsonPath("$.updater").value(DEFAULT_UPDATER.toString()))
            .andExpect(jsonPath("$.updateTimeStamp").value(sameInstant(DEFAULT_UPDATE_TIME_STAMP)))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRateSched() throws Exception {
        // Get the rateSched
        restRateSchedMockMvc.perform(get("/api/rate-scheds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRateSched() throws Exception {
        // Initialize the database
        rateSchedRepository.saveAndFlush(rateSched);

        int databaseSizeBeforeUpdate = rateSchedRepository.findAll().size();

        // Update the rateSched
        RateSched updatedRateSched = rateSchedRepository.findById(rateSched.getId()).get();
        // Disconnect from session so that the updates on updatedRateSched are not directly saved in db
        em.detach(updatedRateSched);
        updatedRateSched
            .rsType(UPDATED_RS_TYPE)
            .rateScheduleCD(UPDATED_RATE_SCHEDULE_CD)
            .updater(UPDATED_UPDATER)
            .updateTimeStamp(UPDATED_UPDATE_TIME_STAMP)
            .businessUnit(UPDATED_BUSINESS_UNIT);
        RateSchedDTO rateSchedDTO = rateSchedMapper.toDto(updatedRateSched);

        restRateSchedMockMvc.perform(put("/api/rate-scheds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedDTO)))
            .andExpect(status().isOk());

        // Validate the RateSched in the database
        List<RateSched> rateSchedList = rateSchedRepository.findAll();
        assertThat(rateSchedList).hasSize(databaseSizeBeforeUpdate);
        RateSched testRateSched = rateSchedList.get(rateSchedList.size() - 1);
        assertThat(testRateSched.getRsType()).isEqualTo(UPDATED_RS_TYPE);
        assertThat(testRateSched.getRateScheduleCD()).isEqualTo(UPDATED_RATE_SCHEDULE_CD);
        assertThat(testRateSched.getUpdater()).isEqualTo(UPDATED_UPDATER);
        assertThat(testRateSched.getUpdateTimeStamp()).isEqualTo(UPDATED_UPDATE_TIME_STAMP);
        assertThat(testRateSched.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingRateSched() throws Exception {
        int databaseSizeBeforeUpdate = rateSchedRepository.findAll().size();

        // Create the RateSched
        RateSchedDTO rateSchedDTO = rateSchedMapper.toDto(rateSched);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRateSchedMockMvc.perform(put("/api/rate-scheds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rateSchedDTO)))
            .andExpect(status().isBadRequest());

        // Validate the RateSched in the database
        List<RateSched> rateSchedList = rateSchedRepository.findAll();
        assertThat(rateSchedList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRateSched() throws Exception {
        // Initialize the database
        rateSchedRepository.saveAndFlush(rateSched);

        int databaseSizeBeforeDelete = rateSchedRepository.findAll().size();

        // Get the rateSched
        restRateSchedMockMvc.perform(delete("/api/rate-scheds/{id}", rateSched.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<RateSched> rateSchedList = rateSchedRepository.findAll();
        assertThat(rateSchedList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RateSched.class);
        RateSched rateSched1 = new RateSched();
        rateSched1.setId(1L);
        RateSched rateSched2 = new RateSched();
        rateSched2.setId(rateSched1.getId());
        assertThat(rateSched1).isEqualTo(rateSched2);
        rateSched2.setId(2L);
        assertThat(rateSched1).isNotEqualTo(rateSched2);
        rateSched1.setId(null);
        assertThat(rateSched1).isNotEqualTo(rateSched2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RateSchedDTO.class);
        RateSchedDTO rateSchedDTO1 = new RateSchedDTO();
        rateSchedDTO1.setId(1L);
        RateSchedDTO rateSchedDTO2 = new RateSchedDTO();
        assertThat(rateSchedDTO1).isNotEqualTo(rateSchedDTO2);
        rateSchedDTO2.setId(rateSchedDTO1.getId());
        assertThat(rateSchedDTO1).isEqualTo(rateSchedDTO2);
        rateSchedDTO2.setId(2L);
        assertThat(rateSchedDTO1).isNotEqualTo(rateSchedDTO2);
        rateSchedDTO1.setId(null);
        assertThat(rateSchedDTO1).isNotEqualTo(rateSchedDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rateSchedMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rateSchedMapper.fromId(null)).isNull();
    }
}
