package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.NominationPriorityDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing NominationPriority.
 */
public interface NominationPriorityService {

    /**
     * Save a nominationPriority.
     *
     * @param nominationPriorityDTO the entity to save
     * @return the persisted entity
     */
    NominationPriorityDTO save(NominationPriorityDTO nominationPriorityDTO);

    /**
     * Get all the nominationPriorities.
     *
     * @return the list of entities
     */
    List<NominationPriorityDTO> findAll();


    /**
     * Get the "id" nominationPriority.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NominationPriorityDTO> findOne(Long id);

    /**
     * Delete the "id" nominationPriority.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
