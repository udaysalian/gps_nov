package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.NominationService;
import com.oilgascs.gps.domain.Nomination;
import com.oilgascs.gps.repository.NominationRepository;
import com.oilgascs.gps.service.dto.NominationDTO;
import com.oilgascs.gps.service.mapper.NominationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Nomination.
 */
@Service
@Transactional
public class NominationServiceImpl implements NominationService {

    private final Logger log = LoggerFactory.getLogger(NominationServiceImpl.class);

    private final NominationRepository nominationRepository;

    private final NominationMapper nominationMapper;

    public NominationServiceImpl(NominationRepository nominationRepository, NominationMapper nominationMapper) {
        this.nominationRepository = nominationRepository;
        this.nominationMapper = nominationMapper;
    }

    /**
     * Save a nomination.
     *
     * @param nominationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NominationDTO save(NominationDTO nominationDTO) {
        log.debug("Request to save Nomination : {}", nominationDTO);
        Nomination nomination = nominationMapper.toEntity(nominationDTO);
        nomination = nominationRepository.save(nomination);
        return nominationMapper.toDto(nomination);
    }

    /**
     * Get all the nominations.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NominationDTO> findAll() {
        log.debug("Request to get all Nominations");
        return nominationRepository.findAll().stream()
            .map(nominationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one nomination by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NominationDTO> findOne(Long id) {
        log.debug("Request to get Nomination : {}", id);
        return nominationRepository.findById(id)
            .map(nominationMapper::toDto);
    }

    /**
     * Delete the nomination by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Nomination : {}", id);
        nominationRepository.deleteById(id);
    }
}
