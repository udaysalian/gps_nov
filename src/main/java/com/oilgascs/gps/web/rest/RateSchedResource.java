package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.RateSchedService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.RateSchedDTO;
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
 * REST controller for managing RateSched.
 */
@RestController
@RequestMapping("/api")
public class RateSchedResource {

    private final Logger log = LoggerFactory.getLogger(RateSchedResource.class);

    private static final String ENTITY_NAME = "rateSched";

    private final RateSchedService rateSchedService;

    public RateSchedResource(RateSchedService rateSchedService) {
        this.rateSchedService = rateSchedService;
    }

    /**
     * POST  /rate-scheds : Create a new rateSched.
     *
     * @param rateSchedDTO the rateSchedDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rateSchedDTO, or with status 400 (Bad Request) if the rateSched has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rate-scheds")
    @Timed
    public ResponseEntity<RateSchedDTO> createRateSched(@Valid @RequestBody RateSchedDTO rateSchedDTO) throws URISyntaxException {
        log.debug("REST request to save RateSched : {}", rateSchedDTO);
        if (rateSchedDTO.getId() != null) {
            throw new BadRequestAlertException("A new rateSched cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RateSchedDTO result = rateSchedService.save(rateSchedDTO);
        return ResponseEntity.created(new URI("/api/rate-scheds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rate-scheds : Updates an existing rateSched.
     *
     * @param rateSchedDTO the rateSchedDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rateSchedDTO,
     * or with status 400 (Bad Request) if the rateSchedDTO is not valid,
     * or with status 500 (Internal Server Error) if the rateSchedDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rate-scheds")
    @Timed
    public ResponseEntity<RateSchedDTO> updateRateSched(@Valid @RequestBody RateSchedDTO rateSchedDTO) throws URISyntaxException {
        log.debug("REST request to update RateSched : {}", rateSchedDTO);
        if (rateSchedDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RateSchedDTO result = rateSchedService.save(rateSchedDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rateSchedDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rate-scheds : get all the rateScheds.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rateScheds in body
     */
    @GetMapping("/rate-scheds")
    @Timed
    public List<RateSchedDTO> getAllRateScheds() {
        log.debug("REST request to get all RateScheds");
        return rateSchedService.findAll();
    }

    /**
     * GET  /rate-scheds/:id : get the "id" rateSched.
     *
     * @param id the id of the rateSchedDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rateSchedDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rate-scheds/{id}")
    @Timed
    public ResponseEntity<RateSchedDTO> getRateSched(@PathVariable Long id) {
        log.debug("REST request to get RateSched : {}", id);
        Optional<RateSchedDTO> rateSchedDTO = rateSchedService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rateSchedDTO);
    }

    /**
     * DELETE  /rate-scheds/:id : delete the "id" rateSched.
     *
     * @param id the id of the rateSchedDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rate-scheds/{id}")
    @Timed
    public ResponseEntity<Void> deleteRateSched(@PathVariable Long id) {
        log.debug("REST request to delete RateSched : {}", id);
        rateSchedService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
