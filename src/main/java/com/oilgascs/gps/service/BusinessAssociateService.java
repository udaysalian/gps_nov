package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.BusinessAssociateDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BusinessAssociate.
 */
public interface BusinessAssociateService {

    /**
     * Save a businessAssociate.
     *
     * @param businessAssociateDTO the entity to save
     * @return the persisted entity
     */
    BusinessAssociateDTO save(BusinessAssociateDTO businessAssociateDTO);

    /**
     * Get all the businessAssociates.
     *
     * @return the list of entities
     */
    List<BusinessAssociateDTO> findAll();


    /**
     * Get the "id" businessAssociate.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BusinessAssociateDTO> findOne(Long id);

    /**
     * Delete the "id" businessAssociate.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
