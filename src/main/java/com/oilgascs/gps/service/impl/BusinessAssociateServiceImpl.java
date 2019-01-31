package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.BusinessAssociateService;
import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.repository.BusinessAssociateRepository;
import com.oilgascs.gps.service.dto.BusinessAssociateDTO;
import com.oilgascs.gps.service.mapper.BusinessAssociateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing BusinessAssociate.
 */
@Service
@Transactional
public class BusinessAssociateServiceImpl implements BusinessAssociateService {

    private final Logger log = LoggerFactory.getLogger(BusinessAssociateServiceImpl.class);

    private final BusinessAssociateRepository businessAssociateRepository;

    private final BusinessAssociateMapper businessAssociateMapper;

    public BusinessAssociateServiceImpl(BusinessAssociateRepository businessAssociateRepository, BusinessAssociateMapper businessAssociateMapper) {
        this.businessAssociateRepository = businessAssociateRepository;
        this.businessAssociateMapper = businessAssociateMapper;
    }

    /**
     * Save a businessAssociate.
     *
     * @param businessAssociateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BusinessAssociateDTO save(BusinessAssociateDTO businessAssociateDTO) {
        log.debug("Request to save BusinessAssociate : {}", businessAssociateDTO);
        BusinessAssociate businessAssociate = businessAssociateMapper.toEntity(businessAssociateDTO);
        businessAssociate = businessAssociateRepository.save(businessAssociate);
        return businessAssociateMapper.toDto(businessAssociate);
    }

    /**
     * Get all the businessAssociates.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BusinessAssociateDTO> findAll() {
        log.debug("Request to get all BusinessAssociates");
        return businessAssociateRepository.findAll().stream()
            .map(businessAssociateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one businessAssociate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BusinessAssociateDTO> findOne(Long id) {
        log.debug("Request to get BusinessAssociate : {}", id);
        return businessAssociateRepository.findById(id)
            .map(businessAssociateMapper::toDto);
    }

    /**
     * Delete the businessAssociate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BusinessAssociate : {}", id);
        businessAssociateRepository.deleteById(id);
    }
}
