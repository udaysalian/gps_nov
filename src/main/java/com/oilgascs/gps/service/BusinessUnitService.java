package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.BusinessUnitDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BusinessUnit.
 */
public interface BusinessUnitService {

    /**
     * Save a businessUnit.
     *
     * @param businessUnitDTO the entity to save
     * @return the persisted entity
     */
    BusinessUnitDTO save(BusinessUnitDTO businessUnitDTO);

    /**
     * Get all the businessUnits.
     *
     * @return the list of entities
     */
    List<BusinessUnitDTO> findAll();


    /**
     * Get the "id" businessUnit.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BusinessUnitDTO> findOne(Long id);

    /**
     * Delete the "id" businessUnit.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
