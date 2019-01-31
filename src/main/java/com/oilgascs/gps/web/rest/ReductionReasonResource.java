package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.ReductionReasonService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.ReductionReasonDTO;
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
 * REST controller for managing ReductionReason.
 */
@RestController
@RequestMapping("/api")
public class ReductionReasonResource {

    private final Logger log = LoggerFactory.getLogger(ReductionReasonResource.class);

    private static final String ENTITY_NAME = "reductionReason";

    private final ReductionReasonService reductionReasonService;

    public ReductionReasonResource(ReductionReasonService reductionReasonService) {
        this.reductionReasonService = reductionReasonService;
    }

    /**
     * POST  /reduction-reasons : Create a new reductionReason.
     *
     * @param reductionReasonDTO the reductionReasonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reductionReasonDTO, or with status 400 (Bad Request) if the reductionReason has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/reduction-reasons")
    @Timed
    public ResponseEntity<ReductionReasonDTO> createReductionReason(@Valid @RequestBody ReductionReasonDTO reductionReasonDTO) throws URISyntaxException {
        log.debug("REST request to save ReductionReason : {}", reductionReasonDTO);
        if (reductionReasonDTO.getId() != null) {
            throw new BadRequestAlertException("A new reductionReason cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReductionReasonDTO result = reductionReasonService.save(reductionReasonDTO);
        return ResponseEntity.created(new URI("/api/reduction-reasons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reduction-reasons : Updates an existing reductionReason.
     *
     * @param reductionReasonDTO the reductionReasonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reductionReasonDTO,
     * or with status 400 (Bad Request) if the reductionReasonDTO is not valid,
     * or with status 500 (Internal Server Error) if the reductionReasonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/reduction-reasons")
    @Timed
    public ResponseEntity<ReductionReasonDTO> updateReductionReason(@Valid @RequestBody ReductionReasonDTO reductionReasonDTO) throws URISyntaxException {
        log.debug("REST request to update ReductionReason : {}", reductionReasonDTO);
        if (reductionReasonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReductionReasonDTO result = reductionReasonService.save(reductionReasonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reductionReasonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reduction-reasons : get all the reductionReasons.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of reductionReasons in body
     */
    @GetMapping("/reduction-reasons")
    @Timed
    public List<ReductionReasonDTO> getAllReductionReasons() {
        log.debug("REST request to get all ReductionReasons");
        return reductionReasonService.findAll();
    }

    /**
     * GET  /reduction-reasons/:id : get the "id" reductionReason.
     *
     * @param id the id of the reductionReasonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reductionReasonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/reduction-reasons/{id}")
    @Timed
    public ResponseEntity<ReductionReasonDTO> getReductionReason(@PathVariable Long id) {
        log.debug("REST request to get ReductionReason : {}", id);
        Optional<ReductionReasonDTO> reductionReasonDTO = reductionReasonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reductionReasonDTO);
    }

    /**
     * DELETE  /reduction-reasons/:id : delete the "id" reductionReason.
     *
     * @param id the id of the reductionReasonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/reduction-reasons/{id}")
    @Timed
    public ResponseEntity<Void> deleteReductionReason(@PathVariable Long id) {
        log.debug("REST request to delete ReductionReason : {}", id);
        reductionReasonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
