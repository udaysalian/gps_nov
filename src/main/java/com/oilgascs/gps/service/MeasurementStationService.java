package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.MeasurementStationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MeasurementStation.
 */
public interface MeasurementStationService {

    /**
     * Save a measurementStation.
     *
     * @param measurementStationDTO the entity to save
     * @return the persisted entity
     */
    MeasurementStationDTO save(MeasurementStationDTO measurementStationDTO);

    /**
     * Get all the measurementStations.
     *
     * @return the list of entities
     */
    List<MeasurementStationDTO> findAll();


    /**
     * Get the "id" measurementStation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MeasurementStationDTO> findOne(Long id);

    /**
     * Delete the "id" measurementStation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
