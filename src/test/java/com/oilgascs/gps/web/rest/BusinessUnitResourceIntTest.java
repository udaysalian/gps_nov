package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.BusinessUnit;
import com.oilgascs.gps.repository.BusinessUnitRepository;
import com.oilgascs.gps.service.BusinessUnitService;
import com.oilgascs.gps.service.dto.BusinessUnitDTO;
import com.oilgascs.gps.service.mapper.BusinessUnitMapper;
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
import org.springframework.util.Base64Utils;

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
 * Test class for the BusinessUnitResource REST controller.
 *
 * @see BusinessUnitResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class BusinessUnitResourceIntTest {

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_ASSOCIATE_NBR = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_ASSOCIATE_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_EDI_PIPE_ID = "AAAAAAAAAA";
    private static final String UPDATED_EDI_PIPE_ID = "BBBBBBBBBB";

    private static final byte[] DEFAULT_COMPANY_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_COMPANY_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_COMPANY_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_COMPANY_LOGO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_UPDATER = "AAAAAAAAAA";
    private static final String UPDATED_UPDATER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATE_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATE_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Autowired
    private BusinessUnitMapper businessUnitMapper;
    
    @Autowired
    private BusinessUnitService businessUnitService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBusinessUnitMockMvc;

    private BusinessUnit businessUnit;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BusinessUnitResource businessUnitResource = new BusinessUnitResource(businessUnitService);
        this.restBusinessUnitMockMvc = MockMvcBuilders.standaloneSetup(businessUnitResource)
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
    public static BusinessUnit createEntity(EntityManager em) {
        BusinessUnit businessUnit = new BusinessUnit()
            .businessUnit(DEFAULT_BUSINESS_UNIT)
            .businessAssociateNbr(DEFAULT_BUSINESS_ASSOCIATE_NBR)
            .ediPipeId(DEFAULT_EDI_PIPE_ID)
            .companyLogo(DEFAULT_COMPANY_LOGO)
            .companyLogoContentType(DEFAULT_COMPANY_LOGO_CONTENT_TYPE)
            .updater(DEFAULT_UPDATER)
            .updateTimestamp(DEFAULT_UPDATE_TIMESTAMP);
        return businessUnit;
    }

    @Before
    public void initTest() {
        businessUnit = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusinessUnit() throws Exception {
        int databaseSizeBeforeCreate = businessUnitRepository.findAll().size();

        // Create the BusinessUnit
        BusinessUnitDTO businessUnitDTO = businessUnitMapper.toDto(businessUnit);
        restBusinessUnitMockMvc.perform(post("/api/business-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessUnitDTO)))
            .andExpect(status().isCreated());

        // Validate the BusinessUnit in the database
        List<BusinessUnit> businessUnitList = businessUnitRepository.findAll();
        assertThat(businessUnitList).hasSize(databaseSizeBeforeCreate + 1);
        BusinessUnit testBusinessUnit = businessUnitList.get(businessUnitList.size() - 1);
        assertThat(testBusinessUnit.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
        assertThat(testBusinessUnit.getBusinessAssociateNbr()).isEqualTo(DEFAULT_BUSINESS_ASSOCIATE_NBR);
        assertThat(testBusinessUnit.getEdiPipeId()).isEqualTo(DEFAULT_EDI_PIPE_ID);
        assertThat(testBusinessUnit.getCompanyLogo()).isEqualTo(DEFAULT_COMPANY_LOGO);
        assertThat(testBusinessUnit.getCompanyLogoContentType()).isEqualTo(DEFAULT_COMPANY_LOGO_CONTENT_TYPE);
        assertThat(testBusinessUnit.getUpdater()).isEqualTo(DEFAULT_UPDATER);
        assertThat(testBusinessUnit.getUpdateTimestamp()).isEqualTo(DEFAULT_UPDATE_TIMESTAMP);
    }

    @Test
    @Transactional
    public void createBusinessUnitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = businessUnitRepository.findAll().size();

        // Create the BusinessUnit with an existing ID
        businessUnit.setId(1L);
        BusinessUnitDTO businessUnitDTO = businessUnitMapper.toDto(businessUnit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusinessUnitMockMvc.perform(post("/api/business-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessUnitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessUnit in the database
        List<BusinessUnit> businessUnitList = businessUnitRepository.findAll();
        assertThat(businessUnitList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBusinessUnits() throws Exception {
        // Initialize the database
        businessUnitRepository.saveAndFlush(businessUnit);

        // Get all the businessUnitList
        restBusinessUnitMockMvc.perform(get("/api/business-units?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(businessUnit.getId().intValue())))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT.toString())))
            .andExpect(jsonPath("$.[*].businessAssociateNbr").value(hasItem(DEFAULT_BUSINESS_ASSOCIATE_NBR.toString())))
            .andExpect(jsonPath("$.[*].ediPipeId").value(hasItem(DEFAULT_EDI_PIPE_ID.toString())))
            .andExpect(jsonPath("$.[*].companyLogoContentType").value(hasItem(DEFAULT_COMPANY_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].companyLogo").value(hasItem(Base64Utils.encodeToString(DEFAULT_COMPANY_LOGO))))
            .andExpect(jsonPath("$.[*].updater").value(hasItem(DEFAULT_UPDATER.toString())))
            .andExpect(jsonPath("$.[*].updateTimestamp").value(hasItem(sameInstant(DEFAULT_UPDATE_TIMESTAMP))));
    }
    
    @Test
    @Transactional
    public void getBusinessUnit() throws Exception {
        // Initialize the database
        businessUnitRepository.saveAndFlush(businessUnit);

        // Get the businessUnit
        restBusinessUnitMockMvc.perform(get("/api/business-units/{id}", businessUnit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(businessUnit.getId().intValue()))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT.toString()))
            .andExpect(jsonPath("$.businessAssociateNbr").value(DEFAULT_BUSINESS_ASSOCIATE_NBR.toString()))
            .andExpect(jsonPath("$.ediPipeId").value(DEFAULT_EDI_PIPE_ID.toString()))
            .andExpect(jsonPath("$.companyLogoContentType").value(DEFAULT_COMPANY_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.companyLogo").value(Base64Utils.encodeToString(DEFAULT_COMPANY_LOGO)))
            .andExpect(jsonPath("$.updater").value(DEFAULT_UPDATER.toString()))
            .andExpect(jsonPath("$.updateTimestamp").value(sameInstant(DEFAULT_UPDATE_TIMESTAMP)));
    }

    @Test
    @Transactional
    public void getNonExistingBusinessUnit() throws Exception {
        // Get the businessUnit
        restBusinessUnitMockMvc.perform(get("/api/business-units/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusinessUnit() throws Exception {
        // Initialize the database
        businessUnitRepository.saveAndFlush(businessUnit);

        int databaseSizeBeforeUpdate = businessUnitRepository.findAll().size();

        // Update the businessUnit
        BusinessUnit updatedBusinessUnit = businessUnitRepository.findById(businessUnit.getId()).get();
        // Disconnect from session so that the updates on updatedBusinessUnit are not directly saved in db
        em.detach(updatedBusinessUnit);
        updatedBusinessUnit
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .businessAssociateNbr(UPDATED_BUSINESS_ASSOCIATE_NBR)
            .ediPipeId(UPDATED_EDI_PIPE_ID)
            .companyLogo(UPDATED_COMPANY_LOGO)
            .companyLogoContentType(UPDATED_COMPANY_LOGO_CONTENT_TYPE)
            .updater(UPDATED_UPDATER)
            .updateTimestamp(UPDATED_UPDATE_TIMESTAMP);
        BusinessUnitDTO businessUnitDTO = businessUnitMapper.toDto(updatedBusinessUnit);

        restBusinessUnitMockMvc.perform(put("/api/business-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessUnitDTO)))
            .andExpect(status().isOk());

        // Validate the BusinessUnit in the database
        List<BusinessUnit> businessUnitList = businessUnitRepository.findAll();
        assertThat(businessUnitList).hasSize(databaseSizeBeforeUpdate);
        BusinessUnit testBusinessUnit = businessUnitList.get(businessUnitList.size() - 1);
        assertThat(testBusinessUnit.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
        assertThat(testBusinessUnit.getBusinessAssociateNbr()).isEqualTo(UPDATED_BUSINESS_ASSOCIATE_NBR);
        assertThat(testBusinessUnit.getEdiPipeId()).isEqualTo(UPDATED_EDI_PIPE_ID);
        assertThat(testBusinessUnit.getCompanyLogo()).isEqualTo(UPDATED_COMPANY_LOGO);
        assertThat(testBusinessUnit.getCompanyLogoContentType()).isEqualTo(UPDATED_COMPANY_LOGO_CONTENT_TYPE);
        assertThat(testBusinessUnit.getUpdater()).isEqualTo(UPDATED_UPDATER);
        assertThat(testBusinessUnit.getUpdateTimestamp()).isEqualTo(UPDATED_UPDATE_TIMESTAMP);
    }

    @Test
    @Transactional
    public void updateNonExistingBusinessUnit() throws Exception {
        int databaseSizeBeforeUpdate = businessUnitRepository.findAll().size();

        // Create the BusinessUnit
        BusinessUnitDTO businessUnitDTO = businessUnitMapper.toDto(businessUnit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBusinessUnitMockMvc.perform(put("/api/business-units")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessUnitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessUnit in the database
        List<BusinessUnit> businessUnitList = businessUnitRepository.findAll();
        assertThat(businessUnitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBusinessUnit() throws Exception {
        // Initialize the database
        businessUnitRepository.saveAndFlush(businessUnit);

        int databaseSizeBeforeDelete = businessUnitRepository.findAll().size();

        // Get the businessUnit
        restBusinessUnitMockMvc.perform(delete("/api/business-units/{id}", businessUnit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BusinessUnit> businessUnitList = businessUnitRepository.findAll();
        assertThat(businessUnitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessUnit.class);
        BusinessUnit businessUnit1 = new BusinessUnit();
        businessUnit1.setId(1L);
        BusinessUnit businessUnit2 = new BusinessUnit();
        businessUnit2.setId(businessUnit1.getId());
        assertThat(businessUnit1).isEqualTo(businessUnit2);
        businessUnit2.setId(2L);
        assertThat(businessUnit1).isNotEqualTo(businessUnit2);
        businessUnit1.setId(null);
        assertThat(businessUnit1).isNotEqualTo(businessUnit2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessUnitDTO.class);
        BusinessUnitDTO businessUnitDTO1 = new BusinessUnitDTO();
        businessUnitDTO1.setId(1L);
        BusinessUnitDTO businessUnitDTO2 = new BusinessUnitDTO();
        assertThat(businessUnitDTO1).isNotEqualTo(businessUnitDTO2);
        businessUnitDTO2.setId(businessUnitDTO1.getId());
        assertThat(businessUnitDTO1).isEqualTo(businessUnitDTO2);
        businessUnitDTO2.setId(2L);
        assertThat(businessUnitDTO1).isNotEqualTo(businessUnitDTO2);
        businessUnitDTO1.setId(null);
        assertThat(businessUnitDTO1).isNotEqualTo(businessUnitDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(businessUnitMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(businessUnitMapper.fromId(null)).isNull();
    }
}
