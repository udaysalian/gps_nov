package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.RateSchedDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RateSched.
 */
public interface RateSchedService {

    /**
     * Save a rateSched.
     *
     * @param rateSchedDTO the entity to save
     * @return the persisted entity
     */
    RateSchedDTO save(RateSchedDTO rateSchedDTO);

    /**
     * Get all the rateScheds.
     *
     * @return the list of entities
     */
    List<RateSchedDTO> findAll();


    /**
     * Get the "id" rateSched.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RateSchedDTO> findOne(Long id);

    /**
     * Delete the "id" rateSched.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
