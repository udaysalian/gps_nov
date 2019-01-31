package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.BusinessAssociateAddressDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing BusinessAssociateAddress.
 */
public interface BusinessAssociateAddressService {

    /**
     * Save a businessAssociateAddress.
     *
     * @param businessAssociateAddressDTO the entity to save
     * @return the persisted entity
     */
    BusinessAssociateAddressDTO save(BusinessAssociateAddressDTO businessAssociateAddressDTO);

    /**
     * Get all the businessAssociateAddresses.
     *
     * @return the list of entities
     */
    List<BusinessAssociateAddressDTO> findAll();


    /**
     * Get the "id" businessAssociateAddress.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BusinessAssociateAddressDTO> findOne(Long id);

    /**
     * Delete the "id" businessAssociateAddress.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
