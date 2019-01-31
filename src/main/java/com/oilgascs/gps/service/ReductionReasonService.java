package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.ReductionReasonDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing ReductionReason.
 */
public interface ReductionReasonService {

    /**
     * Save a reductionReason.
     *
     * @param reductionReasonDTO the entity to save
     * @return the persisted entity
     */
    ReductionReasonDTO save(ReductionReasonDTO reductionReasonDTO);

    /**
     * Get all the reductionReasons.
     *
     * @return the list of entities
     */
    List<ReductionReasonDTO> findAll();


    /**
     * Get the "id" reductionReason.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReductionReasonDTO> findOne(Long id);

    /**
     * Delete the "id" reductionReason.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
