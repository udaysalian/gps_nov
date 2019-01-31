package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.BusinessAssociateContactService;
import com.oilgascs.gps.domain.BusinessAssociateContact;
import com.oilgascs.gps.repository.BusinessAssociateContactRepository;
import com.oilgascs.gps.service.dto.BusinessAssociateContactDTO;
import com.oilgascs.gps.service.mapper.BusinessAssociateContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing BusinessAssociateContact.
 */
@Service
@Transactional
public class BusinessAssociateContactServiceImpl implements BusinessAssociateContactService {

    private final Logger log = LoggerFactory.getLogger(BusinessAssociateContactServiceImpl.class);

    private final BusinessAssociateContactRepository businessAssociateContactRepository;

    private final BusinessAssociateContactMapper businessAssociateContactMapper;

    public BusinessAssociateContactServiceImpl(BusinessAssociateContactRepository businessAssociateContactRepository, BusinessAssociateContactMapper businessAssociateContactMapper) {
        this.businessAssociateContactRepository = businessAssociateContactRepository;
        this.businessAssociateContactMapper = businessAssociateContactMapper;
    }

    /**
     * Save a businessAssociateContact.
     *
     * @param businessAssociateContactDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BusinessAssociateContactDTO save(BusinessAssociateContactDTO businessAssociateContactDTO) {
        log.debug("Request to save BusinessAssociateContact : {}", businessAssociateContactDTO);
        BusinessAssociateContact businessAssociateContact = businessAssociateContactMapper.toEntity(businessAssociateContactDTO);
        businessAssociateContact = businessAssociateContactRepository.save(businessAssociateContact);
        return businessAssociateContactMapper.toDto(businessAssociateContact);
    }

    /**
     * Get all the businessAssociateContacts.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BusinessAssociateContactDTO> findAll() {
        log.debug("Request to get all BusinessAssociateContacts");
        return businessAssociateContactRepository.findAll().stream()
            .map(businessAssociateContactMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one businessAssociateContact by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BusinessAssociateContactDTO> findOne(Long id) {
        log.debug("Request to get BusinessAssociateContact : {}", id);
        return businessAssociateContactRepository.findById(id)
            .map(businessAssociateContactMapper::toDto);
    }

    /**
     * Delete the businessAssociateContact by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BusinessAssociateContact : {}", id);
        businessAssociateContactRepository.deleteById(id);
    }
}
