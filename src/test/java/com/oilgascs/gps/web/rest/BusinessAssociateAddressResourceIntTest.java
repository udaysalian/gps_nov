package com.oilgascs.gps.web.rest;

import com.oilgascs.gps.NetraApp;

import com.oilgascs.gps.domain.BusinessAssociateAddress;
import com.oilgascs.gps.repository.BusinessAssociateAddressRepository;
import com.oilgascs.gps.service.BusinessAssociateAddressService;
import com.oilgascs.gps.service.dto.BusinessAssociateAddressDTO;
import com.oilgascs.gps.service.mapper.BusinessAssociateAddressMapper;
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
 * Test class for the BusinessAssociateAddressResource REST controller.
 *
 * @see BusinessAssociateAddressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NetraApp.class)
public class BusinessAssociateAddressResourceIntTest {

    private static final String DEFAULT_ADD_LINE_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADD_LINE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_NBR = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_NBR = "BBBBBBBBBB";

    private static final String DEFAULT_ADD_LINE_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADD_LINE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBBBBBBB";

    @Autowired
    private BusinessAssociateAddressRepository businessAssociateAddressRepository;

    @Autowired
    private BusinessAssociateAddressMapper businessAssociateAddressMapper;
    
    @Autowired
    private BusinessAssociateAddressService businessAssociateAddressService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBusinessAssociateAddressMockMvc;

    private BusinessAssociateAddress businessAssociateAddress;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BusinessAssociateAddressResource businessAssociateAddressResource = new BusinessAssociateAddressResource(businessAssociateAddressService);
        this.restBusinessAssociateAddressMockMvc = MockMvcBuilders.standaloneSetup(businessAssociateAddressResource)
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
    public static BusinessAssociateAddress createEntity(EntityManager em) {
        BusinessAssociateAddress businessAssociateAddress = new BusinessAssociateAddress()
            .addLine1(DEFAULT_ADD_LINE_1)
            .addressNbr(DEFAULT_ADDRESS_NBR)
            .addLine2(DEFAULT_ADD_LINE_2)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .zipCode(DEFAULT_ZIP_CODE);
        return businessAssociateAddress;
    }

    @Before
    public void initTest() {
        businessAssociateAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusinessAssociateAddress() throws Exception {
        int databaseSizeBeforeCreate = businessAssociateAddressRepository.findAll().size();

        // Create the BusinessAssociateAddress
        BusinessAssociateAddressDTO businessAssociateAddressDTO = businessAssociateAddressMapper.toDto(businessAssociateAddress);
        restBusinessAssociateAddressMockMvc.perform(post("/api/business-associate-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the BusinessAssociateAddress in the database
        List<BusinessAssociateAddress> businessAssociateAddressList = businessAssociateAddressRepository.findAll();
        assertThat(businessAssociateAddressList).hasSize(databaseSizeBeforeCreate + 1);
        BusinessAssociateAddress testBusinessAssociateAddress = businessAssociateAddressList.get(businessAssociateAddressList.size() - 1);
        assertThat(testBusinessAssociateAddress.getAddLine1()).isEqualTo(DEFAULT_ADD_LINE_1);
        assertThat(testBusinessAssociateAddress.getAddressNbr()).isEqualTo(DEFAULT_ADDRESS_NBR);
        assertThat(testBusinessAssociateAddress.getAddLine2()).isEqualTo(DEFAULT_ADD_LINE_2);
        assertThat(testBusinessAssociateAddress.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testBusinessAssociateAddress.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testBusinessAssociateAddress.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
    }

    @Test
    @Transactional
    public void createBusinessAssociateAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = businessAssociateAddressRepository.findAll().size();

        // Create the BusinessAssociateAddress with an existing ID
        businessAssociateAddress.setId(1L);
        BusinessAssociateAddressDTO businessAssociateAddressDTO = businessAssociateAddressMapper.toDto(businessAssociateAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusinessAssociateAddressMockMvc.perform(post("/api/business-associate-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessAssociateAddress in the database
        List<BusinessAssociateAddress> businessAssociateAddressList = businessAssociateAddressRepository.findAll();
        assertThat(businessAssociateAddressList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBusinessAssociateAddresses() throws Exception {
        // Initialize the database
        businessAssociateAddressRepository.saveAndFlush(businessAssociateAddress);

        // Get all the businessAssociateAddressList
        restBusinessAssociateAddressMockMvc.perform(get("/api/business-associate-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(businessAssociateAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].addLine1").value(hasItem(DEFAULT_ADD_LINE_1.toString())))
            .andExpect(jsonPath("$.[*].addressNbr").value(hasItem(DEFAULT_ADDRESS_NBR.toString())))
            .andExpect(jsonPath("$.[*].addLine2").value(hasItem(DEFAULT_ADD_LINE_2.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE.toString())));
    }
    
    @Test
    @Transactional
    public void getBusinessAssociateAddress() throws Exception {
        // Initialize the database
        businessAssociateAddressRepository.saveAndFlush(businessAssociateAddress);

        // Get the businessAssociateAddress
        restBusinessAssociateAddressMockMvc.perform(get("/api/business-associate-addresses/{id}", businessAssociateAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(businessAssociateAddress.getId().intValue()))
            .andExpect(jsonPath("$.addLine1").value(DEFAULT_ADD_LINE_1.toString()))
            .andExpect(jsonPath("$.addressNbr").value(DEFAULT_ADDRESS_NBR.toString()))
            .andExpect(jsonPath("$.addLine2").value(DEFAULT_ADD_LINE_2.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBusinessAssociateAddress() throws Exception {
        // Get the businessAssociateAddress
        restBusinessAssociateAddressMockMvc.perform(get("/api/business-associate-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusinessAssociateAddress() throws Exception {
        // Initialize the database
        businessAssociateAddressRepository.saveAndFlush(businessAssociateAddress);

        int databaseSizeBeforeUpdate = businessAssociateAddressRepository.findAll().size();

        // Update the businessAssociateAddress
        BusinessAssociateAddress updatedBusinessAssociateAddress = businessAssociateAddressRepository.findById(businessAssociateAddress.getId()).get();
        // Disconnect from session so that the updates on updatedBusinessAssociateAddress are not directly saved in db
        em.detach(updatedBusinessAssociateAddress);
        updatedBusinessAssociateAddress
            .addLine1(UPDATED_ADD_LINE_1)
            .addressNbr(UPDATED_ADDRESS_NBR)
            .addLine2(UPDATED_ADD_LINE_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .zipCode(UPDATED_ZIP_CODE);
        BusinessAssociateAddressDTO businessAssociateAddressDTO = businessAssociateAddressMapper.toDto(updatedBusinessAssociateAddress);

        restBusinessAssociateAddressMockMvc.perform(put("/api/business-associate-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateAddressDTO)))
            .andExpect(status().isOk());

        // Validate the BusinessAssociateAddress in the database
        List<BusinessAssociateAddress> businessAssociateAddressList = businessAssociateAddressRepository.findAll();
        assertThat(businessAssociateAddressList).hasSize(databaseSizeBeforeUpdate);
        BusinessAssociateAddress testBusinessAssociateAddress = businessAssociateAddressList.get(businessAssociateAddressList.size() - 1);
        assertThat(testBusinessAssociateAddress.getAddLine1()).isEqualTo(UPDATED_ADD_LINE_1);
        assertThat(testBusinessAssociateAddress.getAddressNbr()).isEqualTo(UPDATED_ADDRESS_NBR);
        assertThat(testBusinessAssociateAddress.getAddLine2()).isEqualTo(UPDATED_ADD_LINE_2);
        assertThat(testBusinessAssociateAddress.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testBusinessAssociateAddress.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testBusinessAssociateAddress.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingBusinessAssociateAddress() throws Exception {
        int databaseSizeBeforeUpdate = businessAssociateAddressRepository.findAll().size();

        // Create the BusinessAssociateAddress
        BusinessAssociateAddressDTO businessAssociateAddressDTO = businessAssociateAddressMapper.toDto(businessAssociateAddress);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBusinessAssociateAddressMockMvc.perform(put("/api/business-associate-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessAssociateAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessAssociateAddress in the database
        List<BusinessAssociateAddress> businessAssociateAddressList = businessAssociateAddressRepository.findAll();
        assertThat(businessAssociateAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBusinessAssociateAddress() throws Exception {
        // Initialize the database
        businessAssociateAddressRepository.saveAndFlush(businessAssociateAddress);

        int databaseSizeBeforeDelete = businessAssociateAddressRepository.findAll().size();

        // Get the businessAssociateAddress
        restBusinessAssociateAddressMockMvc.perform(delete("/api/business-associate-addresses/{id}", businessAssociateAddress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BusinessAssociateAddress> businessAssociateAddressList = businessAssociateAddressRepository.findAll();
        assertThat(businessAssociateAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessAssociateAddress.class);
        BusinessAssociateAddress businessAssociateAddress1 = new BusinessAssociateAddress();
        businessAssociateAddress1.setId(1L);
        BusinessAssociateAddress businessAssociateAddress2 = new BusinessAssociateAddress();
        businessAssociateAddress2.setId(businessAssociateAddress1.getId());
        assertThat(businessAssociateAddress1).isEqualTo(businessAssociateAddress2);
        businessAssociateAddress2.setId(2L);
        assertThat(businessAssociateAddress1).isNotEqualTo(businessAssociateAddress2);
        businessAssociateAddress1.setId(null);
        assertThat(businessAssociateAddress1).isNotEqualTo(businessAssociateAddress2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessAssociateAddressDTO.class);
        BusinessAssociateAddressDTO businessAssociateAddressDTO1 = new BusinessAssociateAddressDTO();
        businessAssociateAddressDTO1.setId(1L);
        BusinessAssociateAddressDTO businessAssociateAddressDTO2 = new BusinessAssociateAddressDTO();
        assertThat(businessAssociateAddressDTO1).isNotEqualTo(businessAssociateAddressDTO2);
        businessAssociateAddressDTO2.setId(businessAssociateAddressDTO1.getId());
        assertThat(businessAssociateAddressDTO1).isEqualTo(businessAssociateAddressDTO2);
        businessAssociateAddressDTO2.setId(2L);
        assertThat(businessAssociateAddressDTO1).isNotEqualTo(businessAssociateAddressDTO2);
        businessAssociateAddressDTO1.setId(null);
        assertThat(businessAssociateAddressDTO1).isNotEqualTo(businessAssociateAddressDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(businessAssociateAddressMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(businessAssociateAddressMapper.fromId(null)).isNull();
    }
}
