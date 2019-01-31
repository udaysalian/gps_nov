package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.LocationBAService;
import com.oilgascs.gps.domain.LocationBA;
import com.oilgascs.gps.repository.LocationBARepository;
import com.oilgascs.gps.service.dto.LocationBADTO;
import com.oilgascs.gps.service.mapper.LocationBAMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing LocationBA.
 */
@Service
@Transactional
public class LocationBAServiceImpl implements LocationBAService {

    private final Logger log = LoggerFactory.getLogger(LocationBAServiceImpl.class);

    private final LocationBARepository locationBARepository;

    private final LocationBAMapper locationBAMapper;

    public LocationBAServiceImpl(LocationBARepository locationBARepository, LocationBAMapper locationBAMapper) {
        this.locationBARepository = locationBARepository;
        this.locationBAMapper = locationBAMapper;
    }

    /**
     * Save a locationBA.
     *
     * @param locationBADTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LocationBADTO save(LocationBADTO locationBADTO) {
        log.debug("Request to save LocationBA : {}", locationBADTO);
        LocationBA locationBA = locationBAMapper.toEntity(locationBADTO);
        locationBA = locationBARepository.save(locationBA);
        return locationBAMapper.toDto(locationBA);
    }

    /**
     * Get all the locationBAS.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LocationBADTO> findAll() {
        log.debug("Request to get all LocationBAS");
        return locationBARepository.findAll().stream()
            .map(locationBAMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one locationBA by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LocationBADTO> findOne(Long id) {
        log.debug("Request to get LocationBA : {}", id);
        return locationBARepository.findById(id)
            .map(locationBAMapper::toDto);
    }

    /**
     * Delete the locationBA by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LocationBA : {}", id);
        locationBARepository.deleteById(id);
    }
}
