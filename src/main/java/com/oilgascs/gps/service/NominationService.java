package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.NominationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Nomination.
 */
public interface NominationService {

    /**
     * Save a nomination.
     *
     * @param nominationDTO the entity to save
     * @return the persisted entity
     */
    NominationDTO save(NominationDTO nominationDTO);

    /**
     * Get all the nominations.
     *
     * @return the list of entities
     */
    List<NominationDTO> findAll();


    /**
     * Get the "id" nomination.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NominationDTO> findOne(Long id);

    /**
     * Delete the "id" nomination.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
