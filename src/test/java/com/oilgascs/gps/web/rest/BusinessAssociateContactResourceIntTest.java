package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.BusinessAssociateContact;
import com.oilgascs.gps.repository.BusinessAssociateContactRepository;
import com.oilgascs.gps.service.BusinessAssociateContactService;
import com.oilgascs.gps.service.dto.BusinessAssociateContactDTO;
import com.oilgascs.gps.service.mapper.BusinessAssociateContactMapper;
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
import java.time.ZoneId;
import java.util.List;


import static com.oilgascs.gps.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BusinessAssociateContactResource REST controller.
 *
 * @see BusinessAssociateContactResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class BusinessAssociateContactResourceIntTest {

    private static final LocalDate DEFAULT_BEGIN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BEGIN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private BusinessAssociateContactRepository businessAssociateContactRepository;

    @Autowired
    private BusinessAssociateContactMapper businessAssociateContactMapper;
    
    @Autowired
    private BusinessAssociateContactService businessAssociateContactService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBusinessAssociateContactMockMvc;

    private BusinessAssociateContact businessAssociateContact;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BusinessAssociateContactResource businessAssociateContactResource = new BusinessAssociateContactResource(businessAssociateContactService);
        this.restBusinessAssociateContactMockMvc = MockMvcBuilders.standaloneSetup(businessAssociateContactResource)
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
    public static BusinessAssociateContact createEntity(EntityManager em) {
        BusinessAssociateContact businessAssociateContact = new BusinessAssociateContact()
            .beginDate(DEFAULT_BEGIN_DATE)
            .endDate(DEFAULT_END_DATE);
        return businessAssociateContact;
    }

    @Before
    public void initTest() {
        businessAssociateContact = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusinessAssociateContact() throws Exception {
        int databaseSizeBeforeCreate = businessAssociateContactRepository.findAll().size();

        // Create the BusinessAssociateContact
        BusinessAssociateContactDTO businessAssociateContactDTO = businessAssociateContactMapper.toDto(businessAssociateContact);
        restBusinessAssociateContactMockMvc.perform(post("/api/business-associate-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateContactDTO)))
            .andExpect(status().isCreated());

        // Validate the BusinessAssociateContact in the database
        List<BusinessAssociateContact> businessAssociateContactList = businessAssociateContactRepository.findAll();
        assertThat(businessAssociateContactList).hasSize(databaseSizeBeforeCreate + 1);
        BusinessAssociateContact testBusinessAssociateContact = businessAssociateContactList.get(businessAssociateContactList.size() - 1);
        assertThat(testBusinessAssociateContact.getBeginDate()).isEqualTo(DEFAULT_BEGIN_DATE);
        assertThat(testBusinessAssociateContact.getEndDate()).isEqualTo(DEFAULT_END_DATE);
    }

    @Test
    @Transactional
    public void createBusinessAssociateContactWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = businessAssociateContactRepository.findAll().size();

        // Create the BusinessAssociateContact with an existing ID
        businessAssociateContact.setId(1L);
        BusinessAssociateContactDTO businessAssociateContactDTO = businessAssociateContactMapper.toDto(businessAssociateContact);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusinessAssociateContactMockMvc.perform(post("/api/business-associate-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessAssociateContact in the database
        List<BusinessAssociateContact> businessAssociateContactList = businessAssociateContactRepository.findAll();
        assertThat(businessAssociateContactList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBusinessAssociateContacts() throws Exception {
        // Initialize the database
        businessAssociateContactRepository.saveAndFlush(businessAssociateContact);

        // Get all the businessAssociateContactList
        restBusinessAssociateContactMockMvc.perform(get("/api/business-associate-contacts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(businessAssociateContact.getId().intValue())))
            .andExpect(jsonPath("$.[*].beginDate").value(hasItem(DEFAULT_BEGIN_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getBusinessAssociateContact() throws Exception {
        // Initialize the database
        businessAssociateContactRepository.saveAndFlush(businessAssociateContact);

        // Get the businessAssociateContact
        restBusinessAssociateContactMockMvc.perform(get("/api/business-associate-contacts/{id}", businessAssociateContact.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(businessAssociateContact.getId().intValue()))
            .andExpect(jsonPath("$.beginDate").value(DEFAULT_BEGIN_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBusinessAssociateContact() throws Exception {
        // Get the businessAssociateContact
        restBusinessAssociateContactMockMvc.perform(get("/api/business-associate-contacts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusinessAssociateContact() throws Exception {
        // Initialize the database
        businessAssociateContactRepository.saveAndFlush(businessAssociateContact);

        int databaseSizeBeforeUpdate = businessAssociateContactRepository.findAll().size();

        // Update the businessAssociateContact
        BusinessAssociateContact updatedBusinessAssociateContact = businessAssociateContactRepository.findById(businessAssociateContact.getId()).get();
        // Disconnect from session so that the updates on updatedBusinessAssociateContact are not directly saved in db
        em.detach(updatedBusinessAssociateContact);
        updatedBusinessAssociateContact
            .beginDate(UPDATED_BEGIN_DATE)
            .endDate(UPDATED_END_DATE);
        BusinessAssociateContactDTO businessAssociateContactDTO = businessAssociateContactMapper.toDto(updatedBusinessAssociateContact);

        restBusinessAssociateContactMockMvc.perform(put("/api/business-associate-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateContactDTO)))
            .andExpect(status().isOk());

        // Validate the BusinessAssociateContact in the database
        List<BusinessAssociateContact> businessAssociateContactList = businessAssociateContactRepository.findAll();
        assertThat(businessAssociateContactList).hasSize(databaseSizeBeforeUpdate);
        BusinessAssociateContact testBusinessAssociateContact = businessAssociateContactList.get(businessAssociateContactList.size() - 1);
        assertThat(testBusinessAssociateContact.getBeginDate()).isEqualTo(UPDATED_BEGIN_DATE);
        assertThat(testBusinessAssociateContact.getEndDate()).isEqualTo(UPDATED_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingBusinessAssociateContact() throws Exception {
        int databaseSizeBeforeUpdate = businessAssociateContactRepository.findAll().size();

        // Create the BusinessAssociateContact
        BusinessAssociateContactDTO businessAssociateContactDTO = businessAssociateContactMapper.toDto(businessAssociateContact);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBusinessAssociateContactMockMvc.perform(put("/api/business-associate-contacts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateContactDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessAssociateContact in the database
        List<BusinessAssociateContact> businessAssociateContactList = businessAssociateContactRepository.findAll();
        assertThat(businessAssociateContactList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBusinessAssociateContact() throws Exception {
        // Initialize the database
        businessAssociateContactRepository.saveAndFlush(businessAssociateContact);

        int databaseSizeBeforeDelete = businessAssociateContactRepository.findAll().size();

        // Get the businessAssociateContact
        restBusinessAssociateContactMockMvc.perform(delete("/api/business-associate-contacts/{id}", businessAssociateContact.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BusinessAssociateContact> businessAssociateContactList = businessAssociateContactRepository.findAll();
        assertThat(businessAssociateContactList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessAssociateContact.class);
        BusinessAssociateContact businessAssociateContact1 = new BusinessAssociateContact();
        businessAssociateContact1.setId(1L);
        BusinessAssociateContact businessAssociateContact2 = new BusinessAssociateContact();
        businessAssociateContact2.setId(businessAssociateContact1.getId());
        assertThat(businessAssociateContact1).isEqualTo(businessAssociateContact2);
        businessAssociateContact2.setId(2L);
        assertThat(businessAssociateContact1).isNotEqualTo(businessAssociateContact2);
        businessAssociateContact1.setId(null);
        assertThat(businessAssociateContact1).isNotEqualTo(businessAssociateContact2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessAssociateContactDTO.class);
        BusinessAssociateContactDTO businessAssociateContactDTO1 = new BusinessAssociateContactDTO();
        businessAssociateContactDTO1.setId(1L);
        BusinessAssociateContactDTO businessAssociateContactDTO2 = new BusinessAssociateContactDTO();
        assertThat(businessAssociateContactDTO1).isNotEqualTo(businessAssociateContactDTO2);
        businessAssociateContactDTO2.setId(businessAssociateContactDTO1.getId());
        assertThat(businessAssociateContactDTO1).isEqualTo(businessAssociateContactDTO2);
        businessAssociateContactDTO2.setId(2L);
        assertThat(businessAssociateContactDTO1).isNotEqualTo(businessAssociateContactDTO2);
        businessAssociateContactDTO1.setId(null);
        assertThat(businessAssociateContactDTO1).isNotEqualTo(businessAssociateContactDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(businessAssociateContactMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(businessAssociateContactMapper.fromId(null)).isNull();
    }
}
