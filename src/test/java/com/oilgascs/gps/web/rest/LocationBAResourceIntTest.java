package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.LocationBA;
import com.oilgascs.gps.repository.LocationBARepository;
import com.oilgascs.gps.service.LocationBAService;
import com.oilgascs.gps.service.dto.LocationBADTO;
import com.oilgascs.gps.service.mapper.LocationBAMapper;
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
 * Test class for the LocationBAResource REST controller.
 *
 * @see LocationBAResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class LocationBAResourceIntTest {

    private static final String DEFAULT_LOCATION_NBR = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION_BA_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION_BA_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATE_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private LocationBARepository locationBARepository;

    @Autowired
    private LocationBAMapper locationBAMapper;
    
    @Autowired
    private LocationBAService locationBAService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLocationBAMockMvc;

    private LocationBA locationBA;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LocationBAResource locationBAResource = new LocationBAResource(locationBAService);
        this.restLocationBAMockMvc = MockMvcBuilders.standaloneSetup(locationBAResource)
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
    public static LocationBA createEntity(EntityManager em) {
        LocationBA locationBA = new LocationBA()
            .locationNbr(DEFAULT_LOCATION_NBR)
            .locationBAType(DEFAULT_LOCATION_BA_TYPE)
            .businessUnit(DEFAULT_BUSINESS_UNIT)
            .updater(DEFAULT_UPDATER)
            .updateTimestamp(DEFAULT_UPDATE_TIMESTAMP);
        return locationBA;
    }

    @Before
    public void initTest() {
        locationBA = createEntity(em);
    }

    @Test
    @Transactional
    public void createLocationBA() throws Exception {
        int databaseSizeBeforeCreate = locationBARepository.findAll().size();

        // Create the LocationBA
        LocationBADTO locationBADTO = locationBAMapper.toDto(locationBA);
        restLocationBAMockMvc.perform(post("/api/location-bas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationBADTO)))
            .andExpect(status().isCreated());

        // Validate the LocationBA in the database
        List<LocationBA> locationBAList = locationBARepository.findAll();
        assertThat(locationBAList).hasSize(databaseSizeBeforeCreate + 1);
        LocationBA testLocationBA = locationBAList.get(locationBAList.size() - 1);
        assertThat(testLocationBA.getLocationNbr()).isEqualTo(DEFAULT_LOCATION_NBR);
        assertThat(testLocationBA.getLocationBAType()).isEqualTo(DEFAULT_LOCATION_BA_TYPE);
        assertThat(testLocationBA.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
        assertThat(testLocationBA.getUpdater()).isEqualTo(DEFAULT_UPDATER);
        assertThat(testLocationBA.getUpdateTimestamp()).isEqualTo(DEFAULT_UPDATE_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createLocationBAWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = locationBARepository.findAll().size();

        // Create the LocationBA with an existing ID
        locationBA.setId(1L);
        LocationBADTO locationBADTO = locationBAMapper.toDto(locationBA);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationBAMockMvc.perform(post("/api/location-bas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationBADTO)))
            .andExpect(status().isBadRequest());

        // Validate the LocationBA in the database
        List<LocationBA> locationBAList = locationBARepository.findAll();
        assertThat(locationBAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLocationNbrIsRequired() throws Exception {
        int databaseSizeBeforeTest = locationBARepository.findAll().size();
        // set the field null
        locationBA.setLocationNbr(null);

        // Create the LocationBA, which fails.
        LocationBADTO locationBADTO = locationBAMapper.toDto(locationBA);

        restLocationBAMockMvc.perform(post("/api/location-bas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationBADTO)))
            .andExpect(status().isBadRequest());

        List<LocationBA> locationBAList = locationBARepository.findAll();
        assertThat(locationBAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusinessUnitIsRequired() throws Exception {
        int databaseSizeBeforeTest = locationBARepository.findAll().size();
        // set the field null
        locationBA.setBusinessUnit(null);

        // Create the LocationBA, which fails.
        LocationBADTO locationBADTO = locationBAMapper.toDto(locationBA);

        restLocationBAMockMvc.perform(post("/api/location-bas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationBADTO)))
            .andExpect(status().isBadRequest());

        List<LocationBA> locationBAList = locationBARepository.findAll();
        assertThat(locationBAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLocationBAS() throws Exception {
        // Initialize the database
        locationBARepository.saveAndFlush(locationBA);

        // Get all the locationBAList
        restLocationBAMockMvc.perform(get("/api/location-bas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(locationBA.getId().intValue())))
            .andExpect(jsonPath("$.[*].locationNbr").value(hasItem(DEFAULT_LOCATION_NBR.toString())))
            .andExpect(jsonPath("$.[*].locationBAType").value(hasItem(DEFAULT_LOCATION_BA_TYPE.toString())))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT.toString())))
            .andExpect(jsonPath("$.[*].updater").value(hasItem(DEFAULT_UPDATER.toString())))
            .andExpect(jsonPath("$.[*].updateTimestamp").value(hasItem(sameInstant(DEFAULT_UPDATE_TIMESTAMP))));
    }
    
    @Test
    @Transactional
    public void getLocationBA() throws Exception {
        // Initialize the database
        locationBARepository.saveAndFlush(locationBA);

        // Get the locationBA
        restLocationBAMockMvc.perform(get("/api/location-bas/{id}", locationBA.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(locationBA.getId().intValue()))
            .andExpect(jsonPath("$.locationNbr").value(DEFAULT_LOCATION_NBR.toString()))
            .andExpect(jsonPath("$.locationBAType").value(DEFAULT_LOCATION_BA_TYPE.toString()))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT.toString()))
            .andExpect(jsonPath("$.updater").value(DEFAULT_UPDATER.toString()))
            .andExpect(jsonPath("$.updateTimestamp").value(sameInstant(DEFAULT_UPDATE_TIMESTAMP)));
    }

    @Test
    @Transactional
    public void getNonExistingLocationBA() throws Exception {
        // Get the locationBA
        restLocationBAMockMvc.perform(get("/api/location-bas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLocationBA() throws Exception {
        // Initialize the database
        locationBARepository.saveAndFlush(locationBA);

        int databaseSizeBeforeUpdate = locationBARepository.findAll().size();

        // Update the locationBA
        LocationBA updatedLocationBA = locationBARepository.findById(locationBA.getId()).get();
        // Disconnect from session so that the updates on updatedLocationBA are not directly saved in db
        em.detach(updatedLocationBA);
        updatedLocationBA
            .locationNbr(UPDATED_LOCATION_NBR)
            .locationBAType(UPDATED_LOCATION_BA_TYPE)
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .updater(UPDATED_UPDATER)
            .updateTimestamp(UPDATED_UPDATE_TIMESTAMP);
        LocationBADTO locationBADTO = locationBAMapper.toDto(updatedLocationBA);

        restLocationBAMockMvc.perform(put("/api/location-bas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationBADTO)))
            .andExpect(status().isOk());

        // Validate the LocationBA in the database
        List<LocationBA> locationBAList = locationBARepository.findAll();
        assertThat(locationBAList).hasSize(databaseSizeBeforeUpdate);
        LocationBA testLocationBA = locationBAList.get(locationBAList.size() - 1);
        assertThat(testLocationBA.getLocationNbr()).isEqualTo(UPDATED_LOCATION_NBR);
        assertThat(testLocationBA.getLocationBAType()).isEqualTo(UPDATED_LOCATION_BA_TYPE);
        assertThat(testLocationBA.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
        assertThat(testLocationBA.getUpdater()).isEqualTo(UPDATED_UPDATER);
        assertThat(testLocationBA.getUpdateTimestamp()).isEqualTo(UPDATED_UPDATE_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingLocationBA() throws Exception {
        int databaseSizeBeforeUpdate = locationBARepository.findAll().size();

        // Create the LocationBA
        LocationBADTO locationBADTO = locationBAMapper.toDto(locationBA);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationBAMockMvc.perform(put("/api/location-bas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(locationBADTO)))
            .andExpect(status().isBadRequest());

        // Validate the LocationBA in the database
        List<LocationBA> locationBAList = locationBARepository.findAll();
        assertThat(locationBAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLocationBA() throws Exception {
        // Initialize the database
        locationBARepository.saveAndFlush(locationBA);

        int databaseSizeBeforeDelete = locationBARepository.findAll().size();

        // Get the locationBA
        restLocationBAMockMvc.perform(delete("/api/location-bas/{id}", locationBA.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LocationBA> locationBAList = locationBARepository.findAll();
        assertThat(locationBAList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocationBA.class);
        LocationBA locationBA1 = new LocationBA();
        locationBA1.setId(1L);
        LocationBA locationBA2 = new LocationBA();
        locationBA2.setId(locationBA1.getId());
        assertThat(locationBA1).isEqualTo(locationBA2);
        locationBA2.setId(2L);
        assertThat(locationBA1).isNotEqualTo(locationBA2);
        locationBA1.setId(null);
        assertThat(locationBA1).isNotEqualTo(locationBA2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocationBADTO.class);
        LocationBADTO locationBADTO1 = new LocationBADTO();
        locationBADTO1.setId(1L);
        LocationBADTO locationBADTO2 = new LocationBADTO();
        assertThat(locationBADTO1).isNotEqualTo(locationBADTO2);
        locationBADTO2.setId(locationBADTO1.getId());
        assertThat(locationBADTO1).isEqualTo(locationBADTO2);
        locationBADTO2.setId(2L);
        assertThat(locationBADTO1).isNotEqualTo(locationBADTO2);
        locationBADTO1.setId(null);
        assertThat(locationBADTO1).isNotEqualTo(locationBADTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(locationBAMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(locationBAMapper.fromId(null)).isNull();
    }
}
