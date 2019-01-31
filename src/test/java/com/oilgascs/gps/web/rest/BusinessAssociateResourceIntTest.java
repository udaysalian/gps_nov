package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.repository.BusinessAssociateRepository;
import com.oilgascs.gps.service.BusinessAssociateService;
import com.oilgascs.gps.service.dto.BusinessAssociateDTO;
import com.oilgascs.gps.service.mapper.BusinessAssociateMapper;
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
 * Test class for the BusinessAssociateResource REST controller.
 *
 * @see BusinessAssociateResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class BusinessAssociateResourceIntTest {

    private static final String DEFAULT_BA_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BA_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BA_ABBR = "AAAAAAAAAA";
    private static final String UPDATED_BA_ABBR = "BBBBBBBBBB";

    private static final String DEFAULT_BA_NBR = "AAAAAAAAAA";
    private static final String UPDATED_BA_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_BA_DUNS_NBR = "AAAAAAAAAA";
    private static final String UPDATED_BA_DUNS_NBR = "BBBBBBBBBB";

    @Autowired
    private BusinessAssociateRepository businessAssociateRepository;

    @Autowired
    private BusinessAssociateMapper businessAssociateMapper;
    
    @Autowired
    private BusinessAssociateService businessAssociateService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBusinessAssociateMockMvc;

    private BusinessAssociate businessAssociate;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BusinessAssociateResource businessAssociateResource = new BusinessAssociateResource(businessAssociateService);
        this.restBusinessAssociateMockMvc = MockMvcBuilders.standaloneSetup(businessAssociateResource)
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
    public static BusinessAssociate createEntity(EntityManager em) {
        BusinessAssociate businessAssociate = new BusinessAssociate()
            .baName(DEFAULT_BA_NAME)
            .baAbbr(DEFAULT_BA_ABBR)
            .baNbr(DEFAULT_BA_NBR)
            .baDunsNbr(DEFAULT_BA_DUNS_NBR);
        return businessAssociate;
    }

    @Before
    public void initTest() {
        businessAssociate = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusinessAssociate() throws Exception {
        int databaseSizeBeforeCreate = businessAssociateRepository.findAll().size();

        // Create the BusinessAssociate
        BusinessAssociateDTO businessAssociateDTO = businessAssociateMapper.toDto(businessAssociate);
        restBusinessAssociateMockMvc.perform(post("/api/business-associates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateDTO)))
            .andExpect(status().isCreated());

        // Validate the BusinessAssociate in the database
        List<BusinessAssociate> businessAssociateList = businessAssociateRepository.findAll();
        assertThat(businessAssociateList).hasSize(databaseSizeBeforeCreate + 1);
        BusinessAssociate testBusinessAssociate = businessAssociateList.get(businessAssociateList.size() - 1);
        assertThat(testBusinessAssociate.getBaName()).isEqualTo(DEFAULT_BA_NAME);
        assertThat(testBusinessAssociate.getBaAbbr()).isEqualTo(DEFAULT_BA_ABBR);
        assertThat(testBusinessAssociate.getBaNbr()).isEqualTo(DEFAULT_BA_NBR);
        assertThat(testBusinessAssociate.getBaDunsNbr()).isEqualTo(DEFAULT_BA_DUNS_NBR);
    }

    @Test
    @Transactional
    public void createBusinessAssociateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = businessAssociateRepository.findAll().size();

        // Create the BusinessAssociate with an existing ID
        businessAssociate.setId(1L);
        BusinessAssociateDTO businessAssociateDTO = businessAssociateMapper.toDto(businessAssociate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusinessAssociateMockMvc.perform(post("/api/business-associates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessAssociate in the database
        List<BusinessAssociate> businessAssociateList = businessAssociateRepository.findAll();
        assertThat(businessAssociateList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBaNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = businessAssociateRepository.findAll().size();
        // set the field null
        businessAssociate.setBaName(null);

        // Create the BusinessAssociate, which fails.
        BusinessAssociateDTO businessAssociateDTO = businessAssociateMapper.toDto(businessAssociate);

        restBusinessAssociateMockMvc.perform(post("/api/business-associates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateDTO)))
            .andExpect(status().isBadRequest());

        List<BusinessAssociate> businessAssociateList = businessAssociateRepository.findAll();
        assertThat(businessAssociateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBaAbbrIsRequired() throws Exception {
        int databaseSizeBeforeTest = businessAssociateRepository.findAll().size();
        // set the field null
        businessAssociate.setBaAbbr(null);

        // Create the BusinessAssociate, which fails.
        BusinessAssociateDTO businessAssociateDTO = businessAssociateMapper.toDto(businessAssociate);

        restBusinessAssociateMockMvc.perform(post("/api/business-associates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateDTO)))
            .andExpect(status().isBadRequest());

        List<BusinessAssociate> businessAssociateList = businessAssociateRepository.findAll();
        assertThat(businessAssociateList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBusinessAssociates() throws Exception {
        // Initialize the database
        businessAssociateRepository.saveAndFlush(businessAssociate);

        // Get all the businessAssociateList
        restBusinessAssociateMockMvc.perform(get("/api/business-associates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(businessAssociate.getId().intValue())))
            .andExpect(jsonPath("$.[*].baName").value(hasItem(DEFAULT_BA_NAME.toString())))
            .andExpect(jsonPath("$.[*].baAbbr").value(hasItem(DEFAULT_BA_ABBR.toString())))
            .andExpect(jsonPath("$.[*].baNbr").value(hasItem(DEFAULT_BA_NBR.toString())))
            .andExpect(jsonPath("$.[*].baDunsNbr").value(hasItem(DEFAULT_BA_DUNS_NBR.toString())));
    }
    
    @Test
    @Transactional
    public void getBusinessAssociate() throws Exception {
        // Initialize the database
        businessAssociateRepository.saveAndFlush(businessAssociate);

        // Get the businessAssociate
        restBusinessAssociateMockMvc.perform(get("/api/business-associates/{id}", businessAssociate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(businessAssociate.getId().intValue()))
            .andExpect(jsonPath("$.baName").value(DEFAULT_BA_NAME.toString()))
            .andExpect(jsonPath("$.baAbbr").value(DEFAULT_BA_ABBR.toString()))
            .andExpect(jsonPath("$.baNbr").value(DEFAULT_BA_NBR.toString()))
            .andExpect(jsonPath("$.baDunsNbr").value(DEFAULT_BA_DUNS_NBR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBusinessAssociate() throws Exception {
        // Get the businessAssociate
        restBusinessAssociateMockMvc.perform(get("/api/business-associates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusinessAssociate() throws Exception {
        // Initialize the database
        businessAssociateRepository.saveAndFlush(businessAssociate);

        int databaseSizeBeforeUpdate = businessAssociateRepository.findAll().size();

        // Update the businessAssociate
        BusinessAssociate updatedBusinessAssociate = businessAssociateRepository.findById(businessAssociate.getId()).get();
        // Disconnect from session so that the updates on updatedBusinessAssociate are not directly saved in db
        em.detach(updatedBusinessAssociate);
        updatedBusinessAssociate
            .baName(UPDATED_BA_NAME)
            .baAbbr(UPDATED_BA_ABBR)
            .baNbr(UPDATED_BA_NBR)
            .baDunsNbr(UPDATED_BA_DUNS_NBR);
        BusinessAssociateDTO businessAssociateDTO = businessAssociateMapper.toDto(updatedBusinessAssociate);

        restBusinessAssociateMockMvc.perform(put("/api/business-associates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateDTO)))
            .andExpect(status().isOk());

        // Validate the BusinessAssociate in the database
        List<BusinessAssociate> businessAssociateList = businessAssociateRepository.findAll();
        assertThat(businessAssociateList).hasSize(databaseSizeBeforeUpdate);
        BusinessAssociate testBusinessAssociate = businessAssociateList.get(businessAssociateList.size() - 1);
        assertThat(testBusinessAssociate.getBaName()).isEqualTo(UPDATED_BA_NAME);
        assertThat(testBusinessAssociate.getBaAbbr()).isEqualTo(UPDATED_BA_ABBR);
        assertThat(testBusinessAssociate.getBaNbr()).isEqualTo(UPDATED_BA_NBR);
        assertThat(testBusinessAssociate.getBaDunsNbr()).isEqualTo(UPDATED_BA_DUNS_NBR);
    }

    @Test
    @Transactional
    public void updateNonExistingBusinessAssociate() throws Exception {
        int databaseSizeBeforeUpdate = businessAssociateRepository.findAll().size();

        // Create the BusinessAssociate
        BusinessAssociateDTO businessAssociateDTO = businessAssociateMapper.toDto(businessAssociate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBusinessAssociateMockMvc.perform(put("/api/business-associates")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessAssociate in the database
        List<BusinessAssociate> businessAssociateList = businessAssociateRepository.findAll();
        assertThat(businessAssociateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBusinessAssociate() throws Exception {
        // Initialize the database
        businessAssociateRepository.saveAndFlush(businessAssociate);

        int databaseSizeBeforeDelete = businessAssociateRepository.findAll().size();

        // Get the businessAssociate
        restBusinessAssociateMockMvc.perform(delete("/api/business-associates/{id}", businessAssociate.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BusinessAssociate> businessAssociateList = businessAssociateRepository.findAll();
        assertThat(businessAssociateList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessAssociate.class);
        BusinessAssociate businessAssociate1 = new BusinessAssociate();
        businessAssociate1.setId(1L);
        BusinessAssociate businessAssociate2 = new BusinessAssociate();
        businessAssociate2.setId(businessAssociate1.getId());
        assertThat(businessAssociate1).isEqualTo(businessAssociate2);
        businessAssociate2.setId(2L);
        assertThat(businessAssociate1).isNotEqualTo(businessAssociate2);
        businessAssociate1.setId(null);
        assertThat(businessAssociate1).isNotEqualTo(businessAssociate2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessAssociateDTO.class);
        BusinessAssociateDTO businessAssociateDTO1 = new BusinessAssociateDTO();
        businessAssociateDTO1.setId(1L);
        BusinessAssociateDTO businessAssociateDTO2 = new BusinessAssociateDTO();
        assertThat(businessAssociateDTO1).isNotEqualTo(businessAssociateDTO2);
        businessAssociateDTO2.setId(businessAssociateDTO1.getId());
        assertThat(businessAssociateDTO1).isEqualTo(businessAssociateDTO2);
        businessAssociateDTO2.setId(2L);
        assertThat(businessAssociateDTO1).isNotEqualTo(businessAssociateDTO2);
        businessAssociateDTO1.setId(null);
        assertThat(businessAssociateDTO1).isNotEqualTo(businessAssociateDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(businessAssociateMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(businessAssociateMapper.fromId(null)).isNull();
    }
}
