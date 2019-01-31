package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.MeasurementStationService;
import com.oilgascs.gps.domain.MeasurementStation;
import com.oilgascs.gps.repository.MeasurementStationRepository;
import com.oilgascs.gps.service.dto.MeasurementStationDTO;
import com.oilgascs.gps.service.mapper.MeasurementStationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing MeasurementStation.
 */
@Service
@Transactional
public class MeasurementStationServiceImpl implements MeasurementStationService {

    private final Logger log = LoggerFactory.getLogger(MeasurementStationServiceImpl.class);

    private final MeasurementStationRepository measurementStationRepository;

    private final MeasurementStationMapper measurementStationMapper;

    public MeasurementStationServiceImpl(MeasurementStationRepository measurementStationRepository, MeasurementStationMapper measurementStationMapper) {
        this.measurementStationRepository = measurementStationRepository;
        this.measurementStationMapper = measurementStationMapper;
    }

    /**
     * Save a measurementStation.
     *
     * @param measurementStationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MeasurementStationDTO save(MeasurementStationDTO measurementStationDTO) {
        log.debug("Request to save MeasurementStation : {}", measurementStationDTO);
        MeasurementStation measurementStation = measurementStationMapper.toEntity(measurementStationDTO);
        measurementStation = measurementStationRepository.save(measurementStation);
        return measurementStationMapper.toDto(measurementStation);
    }

    /**
     * Get all the measurementStations.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MeasurementStationDTO> findAll() {
        log.debug("Request to get all MeasurementStations");
        return measurementStationRepository.findAll().stream()
            .map(measurementStationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one measurementStation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MeasurementStationDTO> findOne(Long id) {
        log.debug("Request to get MeasurementStation : {}", id);
        return measurementStationRepository.findById(id)
            .map(measurementStationMapper::toDto);
    }

    /**
     * Delete the measurementStation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MeasurementStation : {}", id);
        measurementStationRepository.deleteById(id);
    }
}
