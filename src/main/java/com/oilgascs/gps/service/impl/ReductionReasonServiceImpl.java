package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.ReductionReasonService;
import com.oilgascs.gps.domain.ReductionReason;
import com.oilgascs.gps.repository.ReductionReasonRepository;
import com.oilgascs.gps.service.dto.ReductionReasonDTO;
import com.oilgascs.gps.service.mapper.ReductionReasonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing ReductionReason.
 */
@Service
@Transactional
public class ReductionReasonServiceImpl implements ReductionReasonService {

    private final Logger log = LoggerFactory.getLogger(ReductionReasonServiceImpl.class);

    private final ReductionReasonRepository reductionReasonRepository;

    private final ReductionReasonMapper reductionReasonMapper;

    public ReductionReasonServiceImpl(ReductionReasonRepository reductionReasonRepository, ReductionReasonMapper reductionReasonMapper) {
        this.reductionReasonRepository = reductionReasonRepository;
        this.reductionReasonMapper = reductionReasonMapper;
    }

    /**
     * Save a reductionReason.
     *
     * @param reductionReasonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReductionReasonDTO save(ReductionReasonDTO reductionReasonDTO) {
        log.debug("Request to save ReductionReason : {}", reductionReasonDTO);
        ReductionReason reductionReason = reductionReasonMapper.toEntity(reductionReasonDTO);
        reductionReason = reductionReasonRepository.save(reductionReason);
        return reductionReasonMapper.toDto(reductionReason);
    }

    /**
     * Get all the reductionReasons.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReductionReasonDTO> findAll() {
        log.debug("Request to get all ReductionReasons");
        return reductionReasonRepository.findAll().stream()
            .map(reductionReasonMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one reductionReason by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReductionReasonDTO> findOne(Long id) {
        log.debug("Request to get ReductionReason : {}", id);
        return reductionReasonRepository.findById(id)
            .map(reductionReasonMapper::toDto);
    }

    /**
     * Delete the reductionReason by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReductionReason : {}", id);
        reductionReasonRepository.deleteById(id);
    }
}
