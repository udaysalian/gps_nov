package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.service.RateSchedService;
import com.oilgascs.gps.domain.RateSched;
import com.oilgascs.gps.repository.RateSchedRepository;
import com.oilgascs.gps.service.dto.RateSchedDTO;
import com.oilgascs.gps.service.mapper.RateSchedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing RateSched.
 */
@Service
@Transactional
public class RateSchedServiceImpl implements RateSchedService {

    private final Logger log = LoggerFactory.getLogger(RateSchedServiceImpl.class);

    private final RateSchedRepository rateSchedRepository;

    private final RateSchedMapper rateSchedMapper;

    public RateSchedServiceImpl(RateSchedRepository rateSchedRepository, RateSchedMapper rateSchedMapper) {
        this.rateSchedRepository = rateSchedRepository;
        this.rateSchedMapper = rateSchedMapper;
    }

    /**
     * Save a rateSched.
     *
     * @param rateSchedDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RateSchedDTO save(RateSchedDTO rateSchedDTO) {
        log.debug("Request to save RateSched : {}", rateSchedDTO);
        RateSched rateSched = rateSchedMapper.toEntity(rateSchedDTO);
        rateSched = rateSchedRepository.save(rateSched);
        return rateSchedMapper.toDto(rateSched);
    }

    /**
     * Get all the rateScheds.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RateSchedDTO> findAll() {
        log.debug("Request to get all RateScheds");
        return rateSchedRepository.findAll().stream()
            .map(rateSchedMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one rateSched by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RateSchedDTO> findOne(Long id) {
        log.debug("Request to get RateSched : {}", id);
        return rateSchedRepository.findById(id)
            .map(rateSchedMapper::toDto);
    }

    /**
     * Delete the rateSched by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RateSched : {}", id);
        rateSchedRepository.deleteById(id);
    }
}
