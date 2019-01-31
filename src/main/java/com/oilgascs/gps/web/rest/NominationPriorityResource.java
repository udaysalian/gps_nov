package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.NominationPriorityService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.NominationPriorityDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NominationPriority.
 */
@RestController
@RequestMapping("/api")
public class NominationPriorityResource {

    private final Logger log = LoggerFactory.getLogger(NominationPriorityResource.class);

    private static final String ENTITY_NAME = "nominationPriority";

    private final NominationPriorityService nominationPriorityService;

    public NominationPriorityResource(NominationPriorityService nominationPriorityService) {
        this.nominationPriorityService = nominationPriorityService;
    }

    /**
     * POST  /nomination-priorities : Create a new nominationPriority.
     *
     * @param nominationPriorityDTO the nominationPriorityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nominationPriorityDTO, or with status 400 (Bad Request) if the nominationPriority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nomination-priorities")
    @Timed
    public ResponseEntity<NominationPriorityDTO> createNominationPriority(@Valid @RequestBody NominationPriorityDTO nominationPriorityDTO) throws URISyntaxException {
        log.debug("REST request to save NominationPriority : {}", nominationPriorityDTO);
        if (nominationPriorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new nominationPriority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NominationPriorityDTO result = nominationPriorityService.save(nominationPriorityDTO);
        return ResponseEntity.created(new URI("/api/nomination-priorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nomination-priorities : Updates an existing nominationPriority.
     *
     * @param nominationPriorityDTO the nominationPriorityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nominationPriorityDTO,
     * or with status 400 (Bad Request) if the nominationPriorityDTO is not valid,
     * or with status 500 (Internal Server Error) if the nominationPriorityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nomination-priorities")
    @Timed
    public ResponseEntity<NominationPriorityDTO> updateNominationPriority(@Valid @RequestBody NominationPriorityDTO nominationPriorityDTO) throws URISyntaxException {
        log.debug("REST request to update NominationPriority : {}", nominationPriorityDTO);
        if (nominationPriorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NominationPriorityDTO result = nominationPriorityService.save(nominationPriorityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nominationPriorityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nomination-priorities : get all the nominationPriorities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of nominationPriorities in body
     */
    @GetMapping("/nomination-priorities")
    @Timed
    public List<NominationPriorityDTO> getAllNominationPriorities() {
        log.debug("REST request to get all NominationPriorities");
        return nominationPriorityService.findAll();
    }

    /**
     * GET  /nomination-priorities/:id : get the "id" nominationPriority.
     *
     * @param id the id of the nominationPriorityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nominationPriorityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nomination-priorities/{id}")
    @Timed
    public ResponseEntity<NominationPriorityDTO> getNominationPriority(@PathVariable Long id) {
        log.debug("REST request to get NominationPriority : {}", id);
        Optional<NominationPriorityDTO> nominationPriorityDTO = nominationPriorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nominationPriorityDTO);
    }

    /**
     * DELETE  /nomination-priorities/:id : delete the "id" nominationPriority.
     *
     * @param id the id of the nominationPriorityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nomination-priorities/{id}")
    @Timed
    public ResponseEntity<Void> deleteNominationPriority(@PathVariable Long id) {
        log.debug("REST request to delete NominationPriority : {}", id);
        nominationPriorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
