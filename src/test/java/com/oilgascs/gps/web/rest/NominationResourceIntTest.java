package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;
import com.oilgascs.gps.domain.Nomination;
import com.oilgascs.gps.domain.enumeration.NomRequestStatus;
import com.oilgascs.gps.repository.NominationRepository;
import com.oilgascs.gps.service.dto.NominationDTO;
import com.oilgascs.gps.service.impl.GPSNominationServiceImpl;
import com.oilgascs.gps.service.mapper.NominationMapper;
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
import java.time.*;
import java.util.List;

import static com.oilgascs.gps.web.rest.TestUtil.createFormattingConversionService;
import static com.oilgascs.gps.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Test class for the NominationResource REST controller.
 *
 * @see NominationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class NominationResourceIntTest {

    private static final LocalDate DEFAULT_GAS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_GAS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_REQUESTED_RCPT_QTY = 0;
    private static final Integer UPDATED_REQUESTED_RCPT_QTY = 1;

    private static final Integer DEFAULT_REQESTED_DLVRY_QTY = 0;
    private static final Integer UPDATED_REQESTED_DLVRY_QTY = 1;

    private static final Integer DEFAULT_SCHEDULED_RCPT_QTY = 0;
    private static final Integer UPDATED_SCHEDULED_RCPT_QTY = 1;

    private static final Integer DEFAULT_SCHEDULED_DLVRY_QTY = 0;
    private static final Integer UPDATED_SCHEDULED_DLVRY_QTY = 1;

    private static final NomRequestStatus DEFAULT_REQUEST_STATUS = NomRequestStatus.NOMINATED;
    private static final NomRequestStatus UPDATED_REQUEST_STATUS = NomRequestStatus.REJECTED;

    private static final String DEFAULT_UPDATER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATE_TIME_STAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME_STAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    @Autowired
    private NominationRepository nominationRepository;

    @Autowired
    private NominationMapper nominationMapper;

    @Autowired
    private GPSNominationServiceImpl nominationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNominationMockMvc;

    private Nomination nomination;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NominationResource nominationResource = new NominationResource(nominationService);
        this.restNominationMockMvc = MockMvcBuilders.standaloneSetup(nominationResource)
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
    public static Nomination createEntity(EntityManager em) {
        Nomination nomination = new Nomination()
            .gasDate(DEFAULT_GAS_DATE)
            .requestedRcptQty(DEFAULT_REQUESTED_RCPT_QTY)
            .reqestedDlvryQty(DEFAULT_REQESTED_DLVRY_QTY)
            .scheduledRcptQty(DEFAULT_SCHEDULED_RCPT_QTY)
            .scheduledDlvryQty(DEFAULT_SCHEDULED_DLVRY_QTY)
            .requestStatus(DEFAULT_REQUEST_STATUS)
            .updater(DEFAULT_UPDATER)
            .updateTimeStamp(DEFAULT_UPDATE_TIME_STAMP)
            .businessUnit(DEFAULT_BUSINESS_UNIT);
        return nomination;
    }

    @Before
    public void initTest() {
        nomination = createEntity(em);
    }

    @Test
    @Transactional
    public void createNomination() throws Exception {
        int databaseSizeBeforeCreate = nominationRepository.findAll().size();

        // Create the Nomination
        NominationDTO nominationDTO = nominationMapper.toDto(nomination);
        restNominationMockMvc.perform(post("/api/nominations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationDTO)))
            .andExpect(status().isCreated());

        // Validate the Nomination in the database
        List<Nomination> nominationList = nominationRepository.findAll();
        assertThat(nominationList).hasSize(databaseSizeBeforeCreate + 1);
        Nomination testNomination = nominationList.get(nominationList.size() - 1);
        assertThat(testNomination.getGasDate()).isEqualTo(DEFAULT_GAS_DATE);
        assertThat(testNomination.getRequestedRcptQty()).isEqualTo(DEFAULT_REQUESTED_RCPT_QTY);
        assertThat(testNomination.getReqestedDlvryQty()).isEqualTo(DEFAULT_REQESTED_DLVRY_QTY);
        assertThat(testNomination.getScheduledRcptQty()).isEqualTo(DEFAULT_SCHEDULED_RCPT_QTY);
        assertThat(testNomination.getScheduledDlvryQty()).isEqualTo(DEFAULT_SCHEDULED_DLVRY_QTY);
        assertThat(testNomination.getRequestStatus()).isEqualTo(DEFAULT_REQUEST_STATUS);
        assertThat(testNomination.getUpdater()).isEqualTo(DEFAULT_UPDATER);
        assertThat(testNomination.getUpdateTimeStamp()).isEqualTo(DEFAULT_UPDATE_TIME_STAMP);
        assertThat(testNomination.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void createNominationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nominationRepository.findAll().size();

        // Create the Nomination with an existing ID
        nomination.setId(1L);
        NominationDTO nominationDTO = nominationMapper.toDto(nomination);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNominationMockMvc.perform(post("/api/nominations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nomination in the database
        List<Nomination> nominationList = nominationRepository.findAll();
        assertThat(nominationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkGasDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = nominationRepository.findAll().size();
        // set the field null
        nomination.setGasDate(null);

        // Create the Nomination, which fails.
        NominationDTO nominationDTO = nominationMapper.toDto(nomination);

        restNominationMockMvc.perform(post("/api/nominations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationDTO)))
            .andExpect(status().isBadRequest());

        List<Nomination> nominationList = nominationRepository.findAll();
        assertThat(nominationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBusinessUnitIsRequired() throws Exception {
        int databaseSizeBeforeTest = nominationRepository.findAll().size();
        // set the field null
        nomination.setBusinessUnit(null);

        // Create the Nomination, which fails.
        NominationDTO nominationDTO = nominationMapper.toDto(nomination);

        restNominationMockMvc.perform(post("/api/nominations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationDTO)))
            .andExpect(status().isBadRequest());

        List<Nomination> nominationList = nominationRepository.findAll();
        assertThat(nominationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNominations() throws Exception {
        // Initialize the database
        nominationRepository.saveAndFlush(nomination);

        // Get all the nominationList
        restNominationMockMvc.perform(get("/api/nominations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nomination.getId().intValue())))
            .andExpect(jsonPath("$.[*].gasDate").value(hasItem(DEFAULT_GAS_DATE.toString())))
            .andExpect(jsonPath("$.[*].requestedRcptQty").value(hasItem(DEFAULT_REQUESTED_RCPT_QTY)))
            .andExpect(jsonPath("$.[*].reqestedDlvryQty").value(hasItem(DEFAULT_REQESTED_DLVRY_QTY)))
            .andExpect(jsonPath("$.[*].scheduledRcptQty").value(hasItem(DEFAULT_SCHEDULED_RCPT_QTY)))
            .andExpect(jsonPath("$.[*].scheduledDlvryQty").value(hasItem(DEFAULT_SCHEDULED_DLVRY_QTY)))
            .andExpect(jsonPath("$.[*].requestStatus").value(hasItem(DEFAULT_REQUEST_STATUS.toString())))
            .andExpect(jsonPath("$.[*].updater").value(hasItem(DEFAULT_UPDATER.toString())))
            .andExpect(jsonPath("$.[*].updateTimeStamp").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME_STAMP))))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT.toString())));
    }

    @Test
    @Transactional
    public void getNomination() throws Exception {
        // Initialize the database
        nominationRepository.saveAndFlush(nomination);

        // Get the nomination
        restNominationMockMvc.perform(get("/api/nominations/{id}", nomination.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nomination.getId().intValue()))
            .andExpect(jsonPath("$.gasDate").value(DEFAULT_GAS_DATE.toString()))
            .andExpect(jsonPath("$.requestedRcptQty").value(DEFAULT_REQUESTED_RCPT_QTY))
            .andExpect(jsonPath("$.reqestedDlvryQty").value(DEFAULT_REQESTED_DLVRY_QTY))
            .andExpect(jsonPath("$.scheduledRcptQty").value(DEFAULT_SCHEDULED_RCPT_QTY))
            .andExpect(jsonPath("$.scheduledDlvryQty").value(DEFAULT_SCHEDULED_DLVRY_QTY))
            .andExpect(jsonPath("$.requestStatus").value(DEFAULT_REQUEST_STATUS.toString()))
            .andExpect(jsonPath("$.updater").value(DEFAULT_UPDATER.toString()))
            .andExpect(jsonPath("$.updateTimeStamp").value(sameInstant(DEFAULT_UPDATE_TIME_STAMP)))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNomination() throws Exception {
        // Get the nomination
        restNominationMockMvc.perform(get("/api/nominations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNomination() throws Exception {
        // Initialize the database
        nominationRepository.saveAndFlush(nomination);

        int databaseSizeBeforeUpdate = nominationRepository.findAll().size();

        // Update the nomination
        Nomination updatedNomination = nominationRepository.findById(nomination.getId()).get();
        // Disconnect from session so that the updates on updatedNomination are not directly saved in db
        em.detach(updatedNomination);
        updatedNomination
            .gasDate(UPDATED_GAS_DATE)
            .requestedRcptQty(UPDATED_REQUESTED_RCPT_QTY)
            .reqestedDlvryQty(UPDATED_REQESTED_DLVRY_QTY)
            .scheduledRcptQty(UPDATED_SCHEDULED_RCPT_QTY)
            .scheduledDlvryQty(UPDATED_SCHEDULED_DLVRY_QTY)
            .requestStatus(UPDATED_REQUEST_STATUS)
            .updater(UPDATED_UPDATER)
            .updateTimeStamp(UPDATED_UPDATE_TIME_STAMP)
            .businessUnit(UPDATED_BUSINESS_UNIT);
        NominationDTO nominationDTO = nominationMapper.toDto(updatedNomination);

        restNominationMockMvc.perform(put("/api/nominations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationDTO)))
            .andExpect(status().isOk());

        // Validate the Nomination in the database
        List<Nomination> nominationList = nominationRepository.findAll();
        assertThat(nominationList).hasSize(databaseSizeBeforeUpdate);
        Nomination testNomination = nominationList.get(nominationList.size() - 1);
        assertThat(testNomination.getGasDate()).isEqualTo(UPDATED_GAS_DATE);
        assertThat(testNomination.getRequestedRcptQty()).isEqualTo(UPDATED_REQUESTED_RCPT_QTY);
        assertThat(testNomination.getReqestedDlvryQty()).isEqualTo(UPDATED_REQESTED_DLVRY_QTY);
        assertThat(testNomination.getScheduledRcptQty()).isEqualTo(UPDATED_SCHEDULED_RCPT_QTY);
        assertThat(testNomination.getScheduledDlvryQty()).isEqualTo(UPDATED_SCHEDULED_DLVRY_QTY);
        assertThat(testNomination.getRequestStatus()).isEqualTo(UPDATED_REQUEST_STATUS);
        assertThat(testNomination.getUpdater()).isEqualTo(UPDATED_UPDATER);
        assertThat(testNomination.getUpdateTimeStamp()).isEqualTo(UPDATED_UPDATE_TIME_STAMP);
        assertThat(testNomination.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingNomination() throws Exception {
        int databaseSizeBeforeUpdate = nominationRepository.findAll().size();

        // Create the Nomination
        NominationDTO nominationDTO = nominationMapper.toDto(nomination);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNominationMockMvc.perform(put("/api/nominations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Nomination in the database
        List<Nomination> nominationList = nominationRepository.findAll();
        assertThat(nominationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNomination() throws Exception {
        // Initialize the database
        nominationRepository.saveAndFlush(nomination);

        int databaseSizeBeforeDelete = nominationRepository.findAll().size();

        // Get the nomination
        restNominationMockMvc.perform(delete("/api/nominations/{id}", nomination.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Nomination> nominationList = nominationRepository.findAll();
        assertThat(nominationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nomination.class);
        Nomination nomination1 = new Nomination();
        nomination1.setId(1L);
        Nomination nomination2 = new Nomination();
        nomination2.setId(nomination1.getId());
        assertThat(nomination1).isEqualTo(nomination2);
        nomination2.setId(2L);
        assertThat(nomination1).isNotEqualTo(nomination2);
        nomination1.setId(null);
        assertThat(nomination1).isNotEqualTo(nomination2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NominationDTO.class);
        NominationDTO nominationDTO1 = new NominationDTO();
        nominationDTO1.setId(1L);
        NominationDTO nominationDTO2 = new NominationDTO();
        assertThat(nominationDTO1).isNotEqualTo(nominationDTO2);
        nominationDTO2.setId(nominationDTO1.getId());
        assertThat(nominationDTO1).isEqualTo(nominationDTO2);
        nominationDTO2.setId(2L);
        assertThat(nominationDTO1).isNotEqualTo(nominationDTO2);
        nominationDTO1.setId(null);
        assertThat(nominationDTO1).isNotEqualTo(nominationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nominationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nominationMapper.fromId(null)).isNull();
    }
}
