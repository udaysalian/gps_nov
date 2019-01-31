package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.BusinessAssociateContactDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BusinessAssociateContact.
 */
public interface BusinessAssociateContactService {

    /**
     * Save a businessAssociateContact.
     *
     * @param businessAssociateContactDTO the entity to save
     * @return the persisted entity
     */
    BusinessAssociateContactDTO save(BusinessAssociateContactDTO businessAssociateContactDTO);

    /**
     * Get all the businessAssociateContacts.
     *
     * @return the list of entities
     */
    List<BusinessAssociateContactDTO> findAll();


    /**
     * Get the "id" businessAssociateContact.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BusinessAssociateContactDTO> findOne(Long id);

    /**
     * Delete the "id" businessAssociateContact.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
