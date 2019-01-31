package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.ContractDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Contract.
 */
public interface ContractService {

    /**
     * Save a contract.
     *
     * @param contractDTO the entity to save
     * @return the persisted entity
     */
    ContractDTO save(ContractDTO contractDTO);

    /**
     * Get all the contracts.
     *
     * @return the list of entities
     */
    List<ContractDTO> findAll();


    /**
     * Get the "id" contract.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ContractDTO> findOne(Long id);

    /**
     * Delete the "id" contract.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
