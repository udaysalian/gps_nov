package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.NominationPriorityService;
import com.oilgascs.gps.domain.NominationPriority;
import com.oilgascs.gps.repository.NominationPriorityRepository;
import com.oilgascs.gps.service.dto.NominationPriorityDTO;
import com.oilgascs.gps.service.mapper.NominationPriorityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing NominationPriority.
 */
@Service
@Transactional
public class NominationPriorityServiceImpl implements NominationPriorityService {

    private final Logger log = LoggerFactory.getLogger(NominationPriorityServiceImpl.class);

    private final NominationPriorityRepository nominationPriorityRepository;

    private final NominationPriorityMapper nominationPriorityMapper;

    public NominationPriorityServiceImpl(NominationPriorityRepository nominationPriorityRepository, NominationPriorityMapper nominationPriorityMapper) {
        this.nominationPriorityRepository = nominationPriorityRepository;
        this.nominationPriorityMapper = nominationPriorityMapper;
    }

    /**
     * Save a nominationPriority.
     *
     * @param nominationPriorityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NominationPriorityDTO save(NominationPriorityDTO nominationPriorityDTO) {
        log.debug("Request to save NominationPriority : {}", nominationPriorityDTO);
        NominationPriority nominationPriority = nominationPriorityMapper.toEntity(nominationPriorityDTO);
        nominationPriority = nominationPriorityRepository.save(nominationPriority);
        return nominationPriorityMapper.toDto(nominationPriority);
    }

    /**
     * Get all the nominationPriorities.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NominationPriorityDTO> findAll() {
        log.debug("Request to get all NominationPriorities");
        return nominationPriorityRepository.findAll().stream()
            .map(nominationPriorityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one nominationPriority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NominationPriorityDTO> findOne(Long id) {
        log.debug("Request to get NominationPriority : {}", id);
        return nominationPriorityRepository.findById(id)
            .map(nominationPriorityMapper::toDto);
    }

    /**
     * Delete the nominationPriority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NominationPriority : {}", id);
        nominationPriorityRepository.deleteById(id);
    }
}
