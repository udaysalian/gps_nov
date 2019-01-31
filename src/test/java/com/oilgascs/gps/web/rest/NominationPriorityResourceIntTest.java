package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.NominationPriority;
import com.oilgascs.gps.repository.NominationPriorityRepository;
import com.oilgascs.gps.service.NominationPriorityService;
import com.oilgascs.gps.service.dto.NominationPriorityDTO;
import com.oilgascs.gps.service.mapper.NominationPriorityMapper;
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
 * Test class for the NominationPriorityResource REST controller.
 *
 * @see NominationPriorityResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class NominationPriorityResourceIntTest {

    private static final LocalDate DEFAULT_GAS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_GAS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PRTY_TP = "AAAAAAAAAA";
    private static final String UPDATED_PRTY_TP = "BBBBBBBBBB";

    private static final Integer DEFAULT_OLD_QTY = 0;
    private static final Integer UPDATED_OLD_QTY = 1;

    private static final Integer DEFAULT_NEW_QTY = 0;
    private static final Integer UPDATED_NEW_QTY = 1;

    private static final String DEFAULT_SUB_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SUB_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DIR_OF_FLOW = "AAAAAAAAAA";
    private static final String UPDATED_DIR_OF_FLOW = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATE_TIME_STAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIME_STAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    @Autowired
    private NominationPriorityRepository nominationPriorityRepository;

    @Autowired
    private NominationPriorityMapper nominationPriorityMapper;
    
    @Autowired
    private NominationPriorityService nominationPriorityService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNominationPriorityMockMvc;

    private NominationPriority nominationPriority;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NominationPriorityResource nominationPriorityResource = new NominationPriorityResource(nominationPriorityService);
        this.restNominationPriorityMockMvc = MockMvcBuilders.standaloneSetup(nominationPriorityResource)
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
    public static NominationPriority createEntity(EntityManager em) {
        NominationPriority nominationPriority = new NominationPriority()
            .gasDate(DEFAULT_GAS_DATE)
            .prtyTp(DEFAULT_PRTY_TP)
            .oldQty(DEFAULT_OLD_QTY)
            .newQty(DEFAULT_NEW_QTY)
            .subType(DEFAULT_SUB_TYPE)
            .dirOfFlow(DEFAULT_DIR_OF_FLOW)
            .updater(DEFAULT_UPDATER)
            .updateTimeStamp(DEFAULT_UPDATE_TIME_STAMP)
            .businessUnit(DEFAULT_BUSINESS_UNIT);
        return nominationPriority;
    }

    @Before
    public void initTest() {
        nominationPriority = createEntity(em);
    }

    @Test
    @Transactional
    public void createNominationPriority() throws Exception {
        int databaseSizeBeforeCreate = nominationPriorityRepository.findAll().size();

        // Create the NominationPriority
        NominationPriorityDTO nominationPriorityDTO = nominationPriorityMapper.toDto(nominationPriority);
        restNominationPriorityMockMvc.perform(post("/api/nomination-priorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationPriorityDTO)))
            .andExpect(status().isCreated());

        // Validate the NominationPriority in the database
        List<NominationPriority> nominationPriorityList = nominationPriorityRepository.findAll();
        assertThat(nominationPriorityList).hasSize(databaseSizeBeforeCreate + 1);
        NominationPriority testNominationPriority = nominationPriorityList.get(nominationPriorityList.size() - 1);
        assertThat(testNominationPriority.getGasDate()).isEqualTo(DEFAULT_GAS_DATE);
        assertThat(testNominationPriority.getPrtyTp()).isEqualTo(DEFAULT_PRTY_TP);
        assertThat(testNominationPriority.getOldQty()).isEqualTo(DEFAULT_OLD_QTY);
        assertThat(testNominationPriority.getNewQty()).isEqualTo(DEFAULT_NEW_QTY);
        assertThat(testNominationPriority.getSubType()).isEqualTo(DEFAULT_SUB_TYPE);
        assertThat(testNominationPriority.getDirOfFlow()).isEqualTo(DEFAULT_DIR_OF_FLOW);
        assertThat(testNominationPriority.getUpdater()).isEqualTo(DEFAULT_UPDATER);
        assertThat(testNominationPriority.getUpdateTimeStamp()).isEqualTo(DEFAULT_UPDATE_TIME_STAMP);
        assertThat(testNominationPriority.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void createNominationPriorityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nominationPriorityRepository.findAll().size();

        // Create the NominationPriority with an existing ID
        nominationPriority.setId(1L);
        NominationPriorityDTO nominationPriorityDTO = nominationPriorityMapper.toDto(nominationPriority);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNominationPriorityMockMvc.perform(post("/api/nomination-priorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationPriorityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NominationPriority in the database
        List<NominationPriority> nominationPriorityList = nominationPriorityRepository.findAll();
        assertThat(nominationPriorityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkGasDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = nominationPriorityRepository.findAll().size();
        // set the field null
        nominationPriority.setGasDate(null);

        // Create the NominationPriority, which fails.
        NominationPriorityDTO nominationPriorityDTO = nominationPriorityMapper.toDto(nominationPriority);

        restNominationPriorityMockMvc.perform(post("/api/nomination-priorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationPriorityDTO)))
            .andExpect(status().isBadRequest());

        List<NominationPriority> nominationPriorityList = nominationPriorityRepository.findAll();
        assertThat(nominationPriorityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrtyTpIsRequired() throws Exception {
        int databaseSizeBeforeTest = nominationPriorityRepository.findAll().size();
        // set the field null
        nominationPriority.setPrtyTp(null);

        // Create the NominationPriority, which fails.
        NominationPriorityDTO nominationPriorityDTO = nominationPriorityMapper.toDto(nominationPriority);

        restNominationPriorityMockMvc.perform(post("/api/nomination-priorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationPriorityDTO)))
            .andExpect(status().isBadRequest());

        List<NominationPriority> nominationPriorityList = nominationPriorityRepository.findAll();
        assertThat(nominationPriorityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNominationPriorities() throws Exception {
        // Initialize the database
        nominationPriorityRepository.saveAndFlush(nominationPriority);

        // Get all the nominationPriorityList
        restNominationPriorityMockMvc.perform(get("/api/nomination-priorities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nominationPriority.getId().intValue())))
            .andExpect(jsonPath("$.[*].gasDate").value(hasItem(DEFAULT_GAS_DATE.toString())))
            .andExpect(jsonPath("$.[*].prtyTp").value(hasItem(DEFAULT_PRTY_TP.toString())))
            .andExpect(jsonPath("$.[*].oldQty").value(hasItem(DEFAULT_OLD_QTY)))
            .andExpect(jsonPath("$.[*].newQty").value(hasItem(DEFAULT_NEW_QTY)))
            .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE.toString())))
            .andExpect(jsonPath("$.[*].dirOfFlow").value(hasItem(DEFAULT_DIR_OF_FLOW.toString())))
            .andExpect(jsonPath("$.[*].updater").value(hasItem(DEFAULT_UPDATER.toString())))
            .andExpect(jsonPath("$.[*].updateTimeStamp").value(hasItem(sameInstant(DEFAULT_UPDATE_TIME_STAMP))))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT.toString())));
    }
    
    @Test
    @Transactional
    public void getNominationPriority() throws Exception {
        // Initialize the database
        nominationPriorityRepository.saveAndFlush(nominationPriority);

        // Get the nominationPriority
        restNominationPriorityMockMvc.perform(get("/api/nomination-priorities/{id}", nominationPriority.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nominationPriority.getId().intValue()))
            .andExpect(jsonPath("$.gasDate").value(DEFAULT_GAS_DATE.toString()))
            .andExpect(jsonPath("$.prtyTp").value(DEFAULT_PRTY_TP.toString()))
            .andExpect(jsonPath("$.oldQty").value(DEFAULT_OLD_QTY))
            .andExpect(jsonPath("$.newQty").value(DEFAULT_NEW_QTY))
            .andExpect(jsonPath("$.subType").value(DEFAULT_SUB_TYPE.toString()))
            .andExpect(jsonPath("$.dirOfFlow").value(DEFAULT_DIR_OF_FLOW.toString()))
            .andExpect(jsonPath("$.updater").value(DEFAULT_UPDATER.toString()))
            .andExpect(jsonPath("$.updateTimeStamp").value(sameInstant(DEFAULT_UPDATE_TIME_STAMP)))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNominationPriority() throws Exception {
        // Get the nominationPriority
        restNominationPriorityMockMvc.perform(get("/api/nomination-priorities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNominationPriority() throws Exception {
        // Initialize the database
        nominationPriorityRepository.saveAndFlush(nominationPriority);

        int databaseSizeBeforeUpdate = nominationPriorityRepository.findAll().size();

        // Update the nominationPriority
        NominationPriority updatedNominationPriority = nominationPriorityRepository.findById(nominationPriority.getId()).get();
        // Disconnect from session so that the updates on updatedNominationPriority are not directly saved in db
        em.detach(updatedNominationPriority);
        updatedNominationPriority
            .gasDate(UPDATED_GAS_DATE)
            .prtyTp(UPDATED_PRTY_TP)
            .oldQty(UPDATED_OLD_QTY)
            .newQty(UPDATED_NEW_QTY)
            .subType(UPDATED_SUB_TYPE)
            .dirOfFlow(UPDATED_DIR_OF_FLOW)
            .updater(UPDATED_UPDATER)
            .updateTimeStamp(UPDATED_UPDATE_TIME_STAMP)
            .businessUnit(UPDATED_BUSINESS_UNIT);
        NominationPriorityDTO nominationPriorityDTO = nominationPriorityMapper.toDto(updatedNominationPriority);

        restNominationPriorityMockMvc.perform(put("/api/nomination-priorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationPriorityDTO)))
            .andExpect(status().isOk());

        // Validate the NominationPriority in the database
        List<NominationPriority> nominationPriorityList = nominationPriorityRepository.findAll();
        assertThat(nominationPriorityList).hasSize(databaseSizeBeforeUpdate);
        NominationPriority testNominationPriority = nominationPriorityList.get(nominationPriorityList.size() - 1);
        assertThat(testNominationPriority.getGasDate()).isEqualTo(UPDATED_GAS_DATE);
        assertThat(testNominationPriority.getPrtyTp()).isEqualTo(UPDATED_PRTY_TP);
        assertThat(testNominationPriority.getOldQty()).isEqualTo(UPDATED_OLD_QTY);
        assertThat(testNominationPriority.getNewQty()).isEqualTo(UPDATED_NEW_QTY);
        assertThat(testNominationPriority.getSubType()).isEqualTo(UPDATED_SUB_TYPE);
        assertThat(testNominationPriority.getDirOfFlow()).isEqualTo(UPDATED_DIR_OF_FLOW);
        assertThat(testNominationPriority.getUpdater()).isEqualTo(UPDATED_UPDATER);
        assertThat(testNominationPriority.getUpdateTimeStamp()).isEqualTo(UPDATED_UPDATE_TIME_STAMP);
        assertThat(testNominationPriority.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingNominationPriority() throws Exception {
        int databaseSizeBeforeUpdate = nominationPriorityRepository.findAll().size();

        // Create the NominationPriority
        NominationPriorityDTO nominationPriorityDTO = nominationPriorityMapper.toDto(nominationPriority);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNominationPriorityMockMvc.perform(put("/api/nomination-priorities")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nominationPriorityDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NominationPriority in the database
        List<NominationPriority> nominationPriorityList = nominationPriorityRepository.findAll();
        assertThat(nominationPriorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNominationPriority() throws Exception {
        // Initialize the database
        nominationPriorityRepository.saveAndFlush(nominationPriority);

        int databaseSizeBeforeDelete = nominationPriorityRepository.findAll().size();

        // Get the nominationPriority
        restNominationPriorityMockMvc.perform(delete("/api/nomination-priorities/{id}", nominationPriority.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NominationPriority> nominationPriorityList = nominationPriorityRepository.findAll();
        assertThat(nominationPriorityList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NominationPriority.class);
        NominationPriority nominationPriority1 = new NominationPriority();
        nominationPriority1.setId(1L);
        NominationPriority nominationPriority2 = new NominationPriority();
        nominationPriority2.setId(nominationPriority1.getId());
        assertThat(nominationPriority1).isEqualTo(nominationPriority2);
        nominationPriority2.setId(2L);
        assertThat(nominationPriority1).isNotEqualTo(nominationPriority2);
        nominationPriority1.setId(null);
        assertThat(nominationPriority1).isNotEqualTo(nominationPriority2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NominationPriorityDTO.class);
        NominationPriorityDTO nominationPriorityDTO1 = new NominationPriorityDTO();
        nominationPriorityDTO1.setId(1L);
        NominationPriorityDTO nominationPriorityDTO2 = new NominationPriorityDTO();
        assertThat(nominationPriorityDTO1).isNotEqualTo(nominationPriorityDTO2);
        nominationPriorityDTO2.setId(nominationPriorityDTO1.getId());
        assertThat(nominationPriorityDTO1).isEqualTo(nominationPriorityDTO2);
        nominationPriorityDTO2.setId(2L);
        assertThat(nominationPriorityDTO1).isNotEqualTo(nominationPriorityDTO2);
        nominationPriorityDTO1.setId(null);
        assertThat(nominationPriorityDTO1).isNotEqualTo(nominationPriorityDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nominationPriorityMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nominationPriorityMapper.fromId(null)).isNull();
    }
}
