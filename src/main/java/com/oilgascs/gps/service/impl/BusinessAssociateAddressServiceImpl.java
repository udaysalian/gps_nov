package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.BusinessAssociateAddressService;
import com.oilgascs.gps.domain.BusinessAssociateAddress;
import com.oilgascs.gps.repository.BusinessAssociateAddressRepository;
import com.oilgascs.gps.service.dto.BusinessAssociateAddressDTO;
import com.oilgascs.gps.service.mapper.BusinessAssociateAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing BusinessAssociateAddress.
 */
@Service
@Transactional
public class BusinessAssociateAddressServiceImpl implements BusinessAssociateAddressService {

    private final Logger log = LoggerFactory.getLogger(BusinessAssociateAddressServiceImpl.class);

    private final BusinessAssociateAddressRepository businessAssociateAddressRepository;

    private final BusinessAssociateAddressMapper businessAssociateAddressMapper;

    public BusinessAssociateAddressServiceImpl(BusinessAssociateAddressRepository businessAssociateAddressRepository, BusinessAssociateAddressMapper businessAssociateAddressMapper) {
        this.businessAssociateAddressRepository = businessAssociateAddressRepository;
        this.businessAssociateAddressMapper = businessAssociateAddressMapper;
    }

    /**
     * Save a businessAssociateAddress.
     *
     * @param businessAssociateAddressDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BusinessAssociateAddressDTO save(BusinessAssociateAddressDTO businessAssociateAddressDTO) {
        log.debug("Request to save BusinessAssociateAddress : {}", businessAssociateAddressDTO);
        BusinessAssociateAddress businessAssociateAddress = businessAssociateAddressMapper.toEntity(businessAssociateAddressDTO);
        businessAssociateAddress = businessAssociateAddressRepository.save(businessAssociateAddress);
        return businessAssociateAddressMapper.toDto(businessAssociateAddress);
    }

    /**
     * Get all the businessAssociateAddresses.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BusinessAssociateAddressDTO> findAll() {
        log.debug("Request to get all BusinessAssociateAddresses");
        return businessAssociateAddressRepository.findAll().stream()
            .map(businessAssociateAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one businessAssociateAddress by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BusinessAssociateAddressDTO> findOne(Long id) {
        log.debug("Request to get BusinessAssociateAddress : {}", id);
        return businessAssociateAddressRepository.findById(id)
            .map(businessAssociateAddressMapper::toDto);
    }

    /**
     * Delete the businessAssociateAddress by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BusinessAssociateAddress : {}", id);
        businessAssociateAddressRepository.deleteById(id);
    }
}
