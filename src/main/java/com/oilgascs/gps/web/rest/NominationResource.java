package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.dto.NominationDTO;
import com.oilgascs.gps.service.impl.GPSNominationServiceImpl;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
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
 * REST controller for managing Nomination.
 */
@RestController
@RequestMapping("/api")
public class NominationResource {

    private final Logger log = LoggerFactory.getLogger(NominationResource.class);

    private static final String ENTITY_NAME = "nomination";

    private final GPSNominationServiceImpl nominationService;

    public NominationResource(GPSNominationServiceImpl nominationService) {
        this.nominationService = nominationService;
    }

    /**
     * POST  /nominations : Create a new nomination.
     *
     * @param nominationDTO the nominationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nominationDTO, or with status 400 (Bad Request) if the nomination has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nominations")
    @Timed
    public ResponseEntity<NominationDTO> createNomination(@Valid @RequestBody NominationDTO nominationDTO) throws URISyntaxException {
        log.debug("REST request to save Nomination : {}", nominationDTO);
        if (nominationDTO.getId() != null) {
            throw new BadRequestAlertException("A new nomination cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NominationDTO result = nominationService.save(nominationDTO);
        return ResponseEntity.created(new URI("/api/nominations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nominations : Updates an existing nomination.
     *
     * @param nominationDTO the nominationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nominationDTO,
     * or with status 400 (Bad Request) if the nominationDTO is not valid,
     * or with status 500 (Internal Server Error) if the nominationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nominations")
    @Timed
    public ResponseEntity<NominationDTO> updateNomination(@Valid @RequestBody NominationDTO nominationDTO) throws URISyntaxException {
        log.debug("REST request to update Nomination : {}", nominationDTO);
        if (nominationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NominationDTO result = nominationService.save(nominationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nominationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nominations : get all the nominations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of nominations in body
     */
    @GetMapping("/nominations")
    @Timed
    public List<NominationDTO> getAllNominations() {
        log.debug("REST request to get all Nominations");
        return nominationService.findAll();
    }

    /**
     * GET  /nominations/:id : get the "id" nomination.
     *
     * @param id the id of the nominationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nominationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nominations/{id}")
    @Timed
    public ResponseEntity<NominationDTO> getNomination(@PathVariable Long id) {
        log.debug("REST request to get Nomination : {}", id);
        Optional<NominationDTO> nominationDTO = nominationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nominationDTO);
    }

    /**
     * DELETE  /nominations/:id : delete the "id" nomination.
     *
     * @param id the id of the nominationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nominations/{id}")
    @Timed
    public ResponseEntity<Void> deleteNomination(@PathVariable Long id) {
        log.debug("REST request to delete Nomination : {}", id);
        nominationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
