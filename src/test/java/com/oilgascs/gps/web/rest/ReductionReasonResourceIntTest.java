package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.ReductionReason;
import com.oilgascs.gps.repository.ReductionReasonRepository;
import com.oilgascs.gps.service.ReductionReasonService;
import com.oilgascs.gps.service.dto.ReductionReasonDTO;
import com.oilgascs.gps.service.mapper.ReductionReasonMapper;
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
import java.util.List;


import static com.oilgascs.gps.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ReductionReasonResource REST controller.
 *
 * @see ReductionReasonResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class ReductionReasonResourceIntTest {

    private static final String DEFAULT_REASON = "AAAAAAAAAA";
    private static final String UPDATED_REASON = "BBBBBBBBBB";

    private static final String DEFAULT_PROPRIETERY_REASON_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROPRIETERY_REASON_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    @Autowired
    private ReductionReasonRepository reductionReasonRepository;

    @Autowired
    private ReductionReasonMapper reductionReasonMapper;
    
    @Autowired
    private ReductionReasonService reductionReasonService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReductionReasonMockMvc;

    private ReductionReason reductionReason;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReductionReasonResource reductionReasonResource = new ReductionReasonResource(reductionReasonService);
        this.restReductionReasonMockMvc = MockMvcBuilders.standaloneSetup(reductionReasonResource)
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
    public static ReductionReason createEntity(EntityManager em) {
        ReductionReason reductionReason = new ReductionReason()
            .reason(DEFAULT_REASON)
            .proprieteryReasonCode(DEFAULT_PROPRIETERY_REASON_CODE)
            .businessUnit(DEFAULT_BUSINESS_UNIT);
        return reductionReason;
    }

    @Before
    public void initTest() {
        reductionReason = createEntity(em);
    }

    @Test
    @Transactional
    public void createReductionReason() throws Exception {
        int databaseSizeBeforeCreate = reductionReasonRepository.findAll().size();

        // Create the ReductionReason
        ReductionReasonDTO reductionReasonDTO = reductionReasonMapper.toDto(reductionReason);
        restReductionReasonMockMvc.perform(post("/api/reduction-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reductionReasonDTO)))
            .andExpect(status().isCreated());

        // Validate the ReductionReason in the database
        List<ReductionReason> reductionReasonList = reductionReasonRepository.findAll();
        assertThat(reductionReasonList).hasSize(databaseSizeBeforeCreate + 1);
        ReductionReason testReductionReason = reductionReasonList.get(reductionReasonList.size() - 1);
        assertThat(testReductionReason.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testReductionReason.getProprieteryReasonCode()).isEqualTo(DEFAULT_PROPRIETERY_REASON_CODE);
        assertThat(testReductionReason.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void createReductionReasonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reductionReasonRepository.findAll().size();

        // Create the ReductionReason with an existing ID
        reductionReason.setId(1L);
        ReductionReasonDTO reductionReasonDTO = reductionReasonMapper.toDto(reductionReason);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReductionReasonMockMvc.perform(post("/api/reduction-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reductionReasonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReductionReason in the database
        List<ReductionReason> reductionReasonList = reductionReasonRepository.findAll();
        assertThat(reductionReasonList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBusinessUnitIsRequired() throws Exception {
        int databaseSizeBeforeTest = reductionReasonRepository.findAll().size();
        // set the field null
        reductionReason.setBusinessUnit(null);

        // Create the ReductionReason, which fails.
        ReductionReasonDTO reductionReasonDTO = reductionReasonMapper.toDto(reductionReason);

        restReductionReasonMockMvc.perform(post("/api/reduction-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reductionReasonDTO)))
            .andExpect(status().isBadRequest());

        List<ReductionReason> reductionReasonList = reductionReasonRepository.findAll();
        assertThat(reductionReasonList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllReductionReasons() throws Exception {
        // Initialize the database
        reductionReasonRepository.saveAndFlush(reductionReason);

        // Get all the reductionReasonList
        restReductionReasonMockMvc.perform(get("/api/reduction-reasons?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reductionReason.getId().intValue())))
            .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON.toString())))
            .andExpect(jsonPath("$.[*].proprieteryReasonCode").value(hasItem(DEFAULT_PROPRIETERY_REASON_CODE.toString())))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT.toString())));
    }
    
    @Test
    @Transactional
    public void getReductionReason() throws Exception {
        // Initialize the database
        reductionReasonRepository.saveAndFlush(reductionReason);

        // Get the reductionReason
        restReductionReasonMockMvc.perform(get("/api/reduction-reasons/{id}", reductionReason.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reductionReason.getId().intValue()))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON.toString()))
            .andExpect(jsonPath("$.proprieteryReasonCode").value(DEFAULT_PROPRIETERY_REASON_CODE.toString()))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReductionReason() throws Exception {
        // Get the reductionReason
        restReductionReasonMockMvc.perform(get("/api/reduction-reasons/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReductionReason() throws Exception {
        // Initialize the database
        reductionReasonRepository.saveAndFlush(reductionReason);

        int databaseSizeBeforeUpdate = reductionReasonRepository.findAll().size();

        // Update the reductionReason
        ReductionReason updatedReductionReason = reductionReasonRepository.findById(reductionReason.getId()).get();
        // Disconnect from session so that the updates on updatedReductionReason are not directly saved in db
        em.detach(updatedReductionReason);
        updatedReductionReason
            .reason(UPDATED_REASON)
            .proprieteryReasonCode(UPDATED_PROPRIETERY_REASON_CODE)
            .businessUnit(UPDATED_BUSINESS_UNIT);
        ReductionReasonDTO reductionReasonDTO = reductionReasonMapper.toDto(updatedReductionReason);

        restReductionReasonMockMvc.perform(put("/api/reduction-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reductionReasonDTO)))
            .andExpect(status().isOk());

        // Validate the ReductionReason in the database
        List<ReductionReason> reductionReasonList = reductionReasonRepository.findAll();
        assertThat(reductionReasonList).hasSize(databaseSizeBeforeUpdate);
        ReductionReason testReductionReason = reductionReasonList.get(reductionReasonList.size() - 1);
        assertThat(testReductionReason.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testReductionReason.getProprieteryReasonCode()).isEqualTo(UPDATED_PROPRIETERY_REASON_CODE);
        assertThat(testReductionReason.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
    }

    @Test
    @Transactional
    public void updateNonExistingReductionReason() throws Exception {
        int databaseSizeBeforeUpdate = reductionReasonRepository.findAll().size();

        // Create the ReductionReason
        ReductionReasonDTO reductionReasonDTO = reductionReasonMapper.toDto(reductionReason);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReductionReasonMockMvc.perform(put("/api/reduction-reasons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reductionReasonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReductionReason in the database
        List<ReductionReason> reductionReasonList = reductionReasonRepository.findAll();
        assertThat(reductionReasonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReductionReason() throws Exception {
        // Initialize the database
        reductionReasonRepository.saveAndFlush(reductionReason);

        int databaseSizeBeforeDelete = reductionReasonRepository.findAll().size();

        // Get the reductionReason
        restReductionReasonMockMvc.perform(delete("/api/reduction-reasons/{id}", reductionReason.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ReductionReason> reductionReasonList = reductionReasonRepository.findAll();
        assertThat(reductionReasonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReductionReason.class);
        ReductionReason reductionReason1 = new ReductionReason();
        reductionReason1.setId(1L);
        ReductionReason reductionReason2 = new ReductionReason();
        reductionReason2.setId(reductionReason1.getId());
        assertThat(reductionReason1).isEqualTo(reductionReason2);
        reductionReason2.setId(2L);
        assertThat(reductionReason1).isNotEqualTo(reductionReason2);
        reductionReason1.setId(null);
        assertThat(reductionReason1).isNotEqualTo(reductionReason2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReductionReasonDTO.class);
        ReductionReasonDTO reductionReasonDTO1 = new ReductionReasonDTO();
        reductionReasonDTO1.setId(1L);
        ReductionReasonDTO reductionReasonDTO2 = new ReductionReasonDTO();
        assertThat(reductionReasonDTO1).isNotEqualTo(reductionReasonDTO2);
        reductionReasonDTO2.setId(reductionReasonDTO1.getId());
        assertThat(reductionReasonDTO1).isEqualTo(reductionReasonDTO2);
        reductionReasonDTO2.setId(2L);
        assertThat(reductionReasonDTO1).isNotEqualTo(reductionReasonDTO2);
        reductionReasonDTO1.setId(null);
        assertThat(reductionReasonDTO1).isNotEqualTo(reductionReasonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(reductionReasonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(reductionReasonMapper.fromId(null)).isNull();
    }
}
