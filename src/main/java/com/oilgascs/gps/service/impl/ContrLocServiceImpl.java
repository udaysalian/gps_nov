package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.ContrLocService;
import com.oilgascs.gps.domain.ContrLoc;
import com.oilgascs.gps.repository.ContrLocRepository;
import com.oilgascs.gps.service.dto.ContrLocDTO;
import com.oilgascs.gps.service.mapper.ContrLocMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing ContrLoc.
 */
@Service
@Transactional
public class ContrLocServiceImpl implements ContrLocService {

    private final Logger log = LoggerFactory.getLogger(ContrLocServiceImpl.class);

    private final ContrLocRepository contrLocRepository;

    private final ContrLocMapper contrLocMapper;

    public ContrLocServiceImpl(ContrLocRepository contrLocRepository, ContrLocMapper contrLocMapper) {
        this.contrLocRepository = contrLocRepository;
        this.contrLocMapper = contrLocMapper;
    }

    /**
     * Save a contrLoc.
     *
     * @param contrLocDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ContrLocDTO save(ContrLocDTO contrLocDTO) {
        log.debug("Request to save ContrLoc : {}", contrLocDTO);
        ContrLoc contrLoc = contrLocMapper.toEntity(contrLocDTO);
        contrLoc = contrLocRepository.save(contrLoc);
        return contrLocMapper.toDto(contrLoc);
    }

    /**
     * Get all the contrLocs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContrLocDTO> findAll() {
        log.debug("Request to get all ContrLocs");
        return contrLocRepository.findAll().stream()
            .map(contrLocMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one contrLoc by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContrLocDTO> findOne(Long id) {
        log.debug("Request to get ContrLoc : {}", id);
        return contrLocRepository.findById(id)
            .map(contrLocMapper::toDto);
    }

    /**
     * Delete the contrLoc by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContrLoc : {}", id);
        contrLocRepository.deleteById(id);
    }
}
