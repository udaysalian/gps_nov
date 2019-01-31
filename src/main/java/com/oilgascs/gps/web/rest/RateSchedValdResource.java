package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.RateSchedValdService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.RateSchedValdDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RateSchedVald.
 */
@RestController
@RequestMapping("/api")
public class RateSchedValdResource {

    private final Logger log = LoggerFactory.getLogger(RateSchedValdResource.class);

    private static final String ENTITY_NAME = "rateSchedVald";

    private final RateSchedValdService rateSchedValdService;

    public RateSchedValdResource(RateSchedValdService rateSchedValdService) {
        this.rateSchedValdService = rateSchedValdService;
    }

    /**
     * POST  /rate-sched-valds : Create a new rateSchedVald.
     *
     * @param rateSchedValdDTO the rateSchedValdDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rateSchedValdDTO, or with status 400 (Bad Request) if the rateSchedVald has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rate-sched-valds")
    @Timed
    public ResponseEntity<RateSchedValdDTO> createRateSchedVald(@RequestBody RateSchedValdDTO rateSchedValdDTO) throws URISyntaxException {
        log.debug("REST request to save RateSchedVald : {}", rateSchedValdDTO);
        if (rateSchedValdDTO.getId() != null) {
            throw new BadRequestAlertException("A new rateSchedVald cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RateSchedValdDTO result = rateSchedValdService.save(rateSchedValdDTO);
        return ResponseEntity.created(new URI("/api/rate-sched-valds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rate-sched-valds : Updates an existing rateSchedVald.
     *
     * @param rateSchedValdDTO the rateSchedValdDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rateSchedValdDTO,
     * or with status 400 (Bad Request) if the rateSchedValdDTO is not valid,
     * or with status 500 (Internal Server Error) if the rateSchedValdDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rate-sched-valds")
    @Timed
    public ResponseEntity<RateSchedValdDTO> updateRateSchedVald(@RequestBody RateSchedValdDTO rateSchedValdDTO) throws URISyntaxException {
        log.debug("REST request to update RateSchedVald : {}", rateSchedValdDTO);
        if (rateSchedValdDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RateSchedValdDTO result = rateSchedValdService.save(rateSchedValdDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rateSchedValdDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rate-sched-valds : get all the rateSchedValds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rateSchedValds in body
     */
    @GetMapping("/rate-sched-valds")
    @Timed
    public List<RateSchedValdDTO> getAllRateSchedValds() {
        log.debug("REST request to get all RateSchedValds");
        return rateSchedValdService.findAll();
    }

    /**
     * GET  /rate-sched-valds/:id : get the "id" rateSchedVald.
     *
     * @param id the id of the rateSchedValdDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rateSchedValdDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rate-sched-valds/{id}")
    @Timed
    public ResponseEntity<RateSchedValdDTO> getRateSchedVald(@PathVariable Long id) {
        log.debug("REST request to get RateSchedVald : {}", id);
        Optional<RateSchedValdDTO> rateSchedValdDTO = rateSchedValdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rateSchedValdDTO);
    }

    /**
     * DELETE  /rate-sched-valds/:id : delete the "id" rateSchedVald.
     *
     * @param id the id of the rateSchedValdDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rate-sched-valds/{id}")
    @Timed
    public ResponseEntity<Void> deleteRateSchedVald(@PathVariable Long id) {
        log.debug("REST request to delete RateSchedVald : {}", id);
        rateSchedValdService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
