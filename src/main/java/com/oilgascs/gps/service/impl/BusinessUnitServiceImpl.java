package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.BusinessUnitService;
import com.oilgascs.gps.domain.BusinessUnit;
import com.oilgascs.gps.repository.BusinessUnitRepository;
import com.oilgascs.gps.service.dto.BusinessUnitDTO;
import com.oilgascs.gps.service.mapper.BusinessUnitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing BusinessUnit.
 */
@Service
@Transactional
public class BusinessUnitServiceImpl implements BusinessUnitService {

    private final Logger log = LoggerFactory.getLogger(BusinessUnitServiceImpl.class);

    private final BusinessUnitRepository businessUnitRepository;

    private final BusinessUnitMapper businessUnitMapper;

    public BusinessUnitServiceImpl(BusinessUnitRepository businessUnitRepository, BusinessUnitMapper businessUnitMapper) {
        this.businessUnitRepository = businessUnitRepository;
        this.businessUnitMapper = businessUnitMapper;
    }

    /**
     * Save a businessUnit.
     *
     * @param businessUnitDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BusinessUnitDTO save(BusinessUnitDTO businessUnitDTO) {
        log.debug("Request to save BusinessUnit : {}", businessUnitDTO);
        BusinessUnit businessUnit = businessUnitMapper.toEntity(businessUnitDTO);
        businessUnit = businessUnitRepository.save(businessUnit);
        return businessUnitMapper.toDto(businessUnit);
    }

    /**
     * Get all the businessUnits.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BusinessUnitDTO> findAll() {
        log.debug("Request to get all BusinessUnits");
        return businessUnitRepository.findAll().stream()
            .map(businessUnitMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one businessUnit by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BusinessUnitDTO> findOne(Long id) {
        log.debug("Request to get BusinessUnit : {}", id);
        return businessUnitRepository.findById(id)
            .map(businessUnitMapper::toDto);
    }

    /**
     * Delete the businessUnit by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BusinessUnit : {}", id);
        businessUnitRepository.deleteById(id);
    }
}
