package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.RateSchedValdDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RateSchedVald.
 */
public interface RateSchedValdService {

    /**
     * Save a rateSchedVald.
     *
     * @param rateSchedValdDTO the entity to save
     * @return the persisted entity
     */
    RateSchedValdDTO save(RateSchedValdDTO rateSchedValdDTO);

    /**
     * Get all the rateSchedValds.
     *
     * @return the list of entities
     */
    List<RateSchedValdDTO> findAll();


    /**
     * Get the "id" rateSchedVald.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RateSchedValdDTO> findOne(Long id);

    /**
     * Delete the "id" rateSchedVald.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
