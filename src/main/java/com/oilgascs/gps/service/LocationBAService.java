package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.LocationBADTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing LocationBA.
 */
public interface LocationBAService {

    /**
     * Save a locationBA.
     *
     * @param locationBADTO the entity to save
     * @return the persisted entity
     */
    LocationBADTO save(LocationBADTO locationBADTO);

    /**
     * Get all the locationBAS.
     *
     * @return the list of entities
     */
    List<LocationBADTO> findAll();


    /**
     * Get the "id" locationBA.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LocationBADTO> findOne(Long id);

    /**
     * Delete the "id" locationBA.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
