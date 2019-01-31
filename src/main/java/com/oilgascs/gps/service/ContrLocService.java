package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.ContrLocDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ContrLoc.
 */
public interface ContrLocService {

    /**
     * Save a contrLoc.
     *
     * @param contrLocDTO the entity to save
     * @return the persisted entity
     */
    ContrLocDTO save(ContrLocDTO contrLocDTO);

    /**
     * Get all the contrLocs.
     *
     * @return the list of entities
     */
    List<ContrLocDTO> findAll();


    /**
     * Get the "id" contrLoc.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ContrLocDTO> findOne(Long id);

    /**
     * Delete the "id" contrLoc.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
