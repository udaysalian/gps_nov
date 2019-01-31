package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.MeasurementStation;
import com.oilgascs.gps.repository.MeasurementStationRepository;
import com.oilgascs.gps.service.MeasurementStationService;
import com.oilgascs.gps.service.dto.MeasurementStationDTO;
import com.oilgascs.gps.service.mapper.MeasurementStationMapper;
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
 * Test class for the MeasurementStationResource REST controller.
 *
 * @see MeasurementStationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class MeasurementStationResourceIntTest {

    private static final String DEFAULT_LOCATION_NBR = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NBR = "BBBBBBBBBB";

    private static final Double DEFAULT_MILEPOST_NBR = 1D;
    private static final Double UPDATED_MILEPOST_NBR = 2D;

    private static final String DEFAULT_UPSTREAM_PIPE_NODE = "AAAAAAAAAA";
    private static final String UPDATED_UPSTREAM_PIPE_NODE = "BBBBBBBBBB";

    private static final String DEFAULT_DOWN_STREAM_PIPE_NODE = "AAAAAAAAAA";
    private static final String UPDATED_DOWN_STREAM_PIPE_NODE = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATE_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private MeasurementStationRepository measurementStationRepository;

    @Autowired
    private MeasurementStationMapper measurementStationMapper;
    
    @Autowired
    private MeasurementStationService measurementStationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMeasurementStationMockMvc;

    private MeasurementStation measurementStation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MeasurementStationResource measurementStationResource = new MeasurementStationResource(measurementStationService);
        this.restMeasurementStationMockMvc = MockMvcBuilders.standaloneSetup(measurementStationResource)
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
    public static MeasurementStation createEntity(EntityManager em) {
        MeasurementStation measurementStation = new MeasurementStation()
            .locationNbr(DEFAULT_LOCATION_NBR)
            .milepostNbr(DEFAULT_MILEPOST_NBR)
            .upstreamPipeNode(DEFAULT_UPSTREAM_PIPE_NODE)
            .downStreamPipeNode(DEFAULT_DOWN_STREAM_PIPE_NODE)
            .businessUnit(DEFAULT_BUSINESS_UNIT)
            .updater(DEFAULT_UPDATER)
            .updateTimestamp(DEFAULT_UPDATE_TIMESTAMP);
        return measurementStation;
    }

    @Before
    public void initTest() {
        measurementStation = createEntity(em);
    }

    @Test
    @Transactional
    public void createMeasurementStation() throws Exception {
        int databaseSizeBeforeCreate = measurementStationRepository.findAll().size();

        // Create the MeasurementStation
        MeasurementStationDTO measurementStationDTO = measurementStationMapper.toDto(measurementStation);
        restMeasurementStationMockMvc.perform(post("/api/measurement-stations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(measurementStationDTO)))
            .andExpect(status().isCreated());

        // Validate the MeasurementStation in the database
        List<MeasurementStation> measurementStationList = measurementStationRepository.findAll();
        assertThat(measurementStationList).hasSize(databaseSizeBeforeCreate + 1);
        MeasurementStation testMeasurementStation = measurementStationList.get(measurementStationList.size() - 1);
        assertThat(testMeasurementStation.getLocationNbr()).isEqualTo(DEFAULT_LOCATION_NBR);
        assertThat(testMeasurementStation.getMilepostNbr()).isEqualTo(DEFAULT_MILEPOST_NBR);
        assertThat(testMeasurementStation.getUpstreamPipeNode()).isEqualTo(DEFAULT_UPSTREAM_PIPE_NODE);
        assertThat(testMeasurementStation.getDownStreamPipeNode()).isEqualTo(DEFAULT_DOWN_STREAM_PIPE_NODE);
        assertThat(testMeasurementStation.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
        assertThat(testMeasurementStation.getUpdater()).isEqualTo(DEFAULT_UPDATER);
        assertThat(testMeasurementStation.getUpdateTimestamp()).isEqualTo(DEFAULT_UPDATE_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createMeasurementStationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = measurementStationRepository.findAll().size();

        // Create the MeasurementStation with an existing ID
        measurementStation.setId(1L);
        MeasurementStationDTO measurementStationDTO = measurementStationMapper.toDto(measurementStation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMeasurementStationMockMvc.perform(post("/api/measurement-stations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(measurementStationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MeasurementStation in the database
        List<MeasurementStation> measurementStationList = measurementStationRepository.findAll();
        assertThat(measurementStationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLocationNbrIsRequired() throws Exception {
        int databaseSizeBeforeTest = measurementStationRepository.findAll().size();
        // set the field null
        measurementStation.setLocationNbr(null);

        // Create the MeasurementStation, which fails.
        MeasurementStationDTO measurementStationDTO = measurementStationMapper.toDto(measurementStation);

        restMeasurementStationMockMvc.perform(post("/api/measurement-stations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(measurementStationDTO)))
            .andExpect(status().isBadRequest());

        List<MeasurementStation> measurementStationList = measurementStationRepository.findAll();
        assertThat(measurementStationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusinessUnitIsRequired() throws Exception {
        int databaseSizeBeforeTest = measurementStationRepository.findAll().size();
        // set the field null
        measurementStation.setBusinessUnit(null);

        // Create the MeasurementStation, which fails.
        MeasurementStationDTO measurementStationDTO = measurementStationMapper.toDto(measurementStation);

        restMeasurementStationMockMvc.perform(post("/api/measurement-stations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(measurementStationDTO)))
            .andExpect(status().isBadRequest());

        List<MeasurementStation> measurementStationList = measurementStationRepository.findAll();
        assertThat(measurementStationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMeasurementStations() throws Exception {
        // Initialize the database
        measurementStationRepository.saveAndFlush(measurementStation);

        // Get all the measurementStationList
        restMeasurementStationMockMvc.perform(get("/api/measurement-stations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(measurementStation.getId().intValue())))
            .andExpect(jsonPath("$.[*].locationNbr").value(hasItem(DEFAULT_LOCATION_NBR.toString())))
            .andExpect(jsonPath("$.[*].milepostNbr").value(hasItem(DEFAULT_MILEPOST_NBR.doubleValue())))
            .andExpect(jsonPath("$.[*].upstreamPipeNode").value(hasItem(DEFAULT_UPSTREAM_PIPE_NODE.toString())))
            .andExpect(jsonPath("$.[*].downStreamPipeNode").value(hasItem(DEFAULT_DOWN_STREAM_PIPE_NODE.toString())))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT.toString())))
            .andExpect(jsonPath("$.[*].updater").value(hasItem(DEFAULT_UPDATER.toString())))
            .andExpect(jsonPath("$.[*].updateTimestamp").value(hasItem(sameInstant(DEFAULT_UPDATE_TIMESTAMP))));
    }
    
    @Test
    @Transactional
    public void getMeasurementStation() throws Exception {
        // Initialize the database
        measurementStationRepository.saveAndFlush(measurementStation);

        // Get the measurementStation
        restMeasurementStationMockMvc.perform(get("/api/measurement-stations/{id}", measurementStation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(measurementStation.getId().intValue()))
            .andExpect(jsonPath("$.locationNbr").value(DEFAULT_LOCATION_NBR.toString()))
            .andExpect(jsonPath("$.milepostNbr").value(DEFAULT_MILEPOST_NBR.doubleValue()))
            .andExpect(jsonPath("$.upstreamPipeNode").value(DEFAULT_UPSTREAM_PIPE_NODE.toString()))
            .andExpect(jsonPath("$.downStreamPipeNode").value(DEFAULT_DOWN_STREAM_PIPE_NODE.toString()))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT.toString()))
            .andExpect(jsonPath("$.updater").value(DEFAULT_UPDATER.toString()))
            .andExpect(jsonPath("$.updateTimestamp").value(sameInstant(DEFAULT_UPDATE_TIMESTAMP)));
    }

    @Test
    @Transactional
    public void getNonExistingMeasurementStation() throws Exception {
        // Get the measurementStation
        restMeasurementStationMockMvc.perform(get("/api/measurement-stations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeasurementStation() throws Exception {
        // Initialize the database
        measurementStationRepository.saveAndFlush(measurementStation);

        int databaseSizeBeforeUpdate = measurementStationRepository.findAll().size();

        // Update the measurementStation
        MeasurementStation updatedMeasurementStation = measurementStationRepository.findById(measurementStation.getId()).get();
        // Disconnect from session so that the updates on updatedMeasurementStation are not directly saved in db
        em.detach(updatedMeasurementStation);
        updatedMeasurementStation
            .locationNbr(UPDATED_LOCATION_NBR)
            .milepostNbr(UPDATED_MILEPOST_NBR)
            .upstreamPipeNode(UPDATED_UPSTREAM_PIPE_NODE)
            .downStreamPipeNode(UPDATED_DOWN_STREAM_PIPE_NODE)
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .updater(UPDATED_UPDATER)
            .updateTimestamp(UPDATED_UPDATE_TIMESTAMP);
        MeasurementStationDTO measurementStationDTO = measurementStationMapper.toDto(updatedMeasurementStation);

        restMeasurementStationMockMvc.perform(put("/api/measurement-stations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(measurementStationDTO)))
            .andExpect(status().isOk());

        // Validate the MeasurementStation in the database
        List<MeasurementStation> measurementStationList = measurementStationRepository.findAll();
        assertThat(measurementStationList).hasSize(databaseSizeBeforeUpdate);
        MeasurementStation testMeasurementStation = measurementStationList.get(measurementStationList.size() - 1);
        assertThat(testMeasurementStation.getLocationNbr()).isEqualTo(UPDATED_LOCATION_NBR);
        assertThat(testMeasurementStation.getMilepostNbr()).isEqualTo(UPDATED_MILEPOST_NBR);
        assertThat(testMeasurementStation.getUpstreamPipeNode()).isEqualTo(UPDATED_UPSTREAM_PIPE_NODE);
        assertThat(testMeasurementStation.getDownStreamPipeNode()).isEqualTo(UPDATED_DOWN_STREAM_PIPE_NODE);
        assertThat(testMeasurementStation.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
        assertThat(testMeasurementStation.getUpdater()).isEqualTo(UPDATED_UPDATER);
        assertThat(testMeasurementStation.getUpdateTimestamp()).isEqualTo(UPDATED_UPDATE_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingMeasurementStation() throws Exception {
        int databaseSizeBeforeUpdate = measurementStationRepository.findAll().size();

        // Create the MeasurementStation
        MeasurementStationDTO measurementStationDTO = measurementStationMapper.toDto(measurementStation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMeasurementStationMockMvc.perform(put("/api/measurement-stations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(measurementStationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MeasurementStation in the database
        List<MeasurementStation> measurementStationList = measurementStationRepository.findAll();
        assertThat(measurementStationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMeasurementStation() throws Exception {
        // Initialize the database
        measurementStationRepository.saveAndFlush(measurementStation);

        int databaseSizeBeforeDelete = measurementStationRepository.findAll().size();

        // Get the measurementStation
        restMeasurementStationMockMvc.perform(delete("/api/measurement-stations/{id}", measurementStation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MeasurementStation> measurementStationList = measurementStationRepository.findAll();
        assertThat(measurementStationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeasurementStation.class);
        MeasurementStation measurementStation1 = new MeasurementStation();
        measurementStation1.setId(1L);
        MeasurementStation measurementStation2 = new MeasurementStation();
        measurementStation2.setId(measurementStation1.getId());
        assertThat(measurementStation1).isEqualTo(measurementStation2);
        measurementStation2.setId(2L);
        assertThat(measurementStation1).isNotEqualTo(measurementStation2);
        measurementStation1.setId(null);
        assertThat(measurementStation1).isNotEqualTo(measurementStation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MeasurementStationDTO.class);
        MeasurementStationDTO measurementStationDTO1 = new MeasurementStationDTO();
        measurementStationDTO1.setId(1L);
        MeasurementStationDTO measurementStationDTO2 = new MeasurementStationDTO();
        assertThat(measurementStationDTO1).isNotEqualTo(measurementStationDTO2);
        measurementStationDTO2.setId(measurementStationDTO1.getId());
        assertThat(measurementStationDTO1).isEqualTo(measurementStationDTO2);
        measurementStationDTO2.setId(2L);
        assertThat(measurementStationDTO1).isNotEqualTo(measurementStationDTO2);
        measurementStationDTO1.setId(null);
        assertThat(measurementStationDTO1).isNotEqualTo(measurementStationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(measurementStationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(measurementStationMapper.fromId(null)).isNull();
    }
}
