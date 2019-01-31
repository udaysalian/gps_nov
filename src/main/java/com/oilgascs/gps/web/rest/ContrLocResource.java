package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.ContrLocService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.ContrLocDTO;
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
 * REST controller for managing ContrLoc.
 */
@RestController
@RequestMapping("/api")
public class ContrLocResource {

    private final Logger log = LoggerFactory.getLogger(ContrLocResource.class);

    private static final String ENTITY_NAME = "contrLoc";

    private final ContrLocService contrLocService;

    public ContrLocResource(ContrLocService contrLocService) {
        this.contrLocService = contrLocService;
    }

    /**
     * POST  /contr-locs : Create a new contrLoc.
     *
     * @param contrLocDTO the contrLocDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contrLocDTO, or with status 400 (Bad Request) if the contrLoc has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contr-locs")
    @Timed
    public ResponseEntity<ContrLocDTO> createContrLoc(@Valid @RequestBody ContrLocDTO contrLocDTO) throws URISyntaxException {
        log.debug("REST request to save ContrLoc : {}", contrLocDTO);
        if (contrLocDTO.getId() != null) {
            throw new BadRequestAlertException("A new contrLoc cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContrLocDTO result = contrLocService.save(contrLocDTO);
        return ResponseEntity.created(new URI("/api/contr-locs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contr-locs : Updates an existing contrLoc.
     *
     * @param contrLocDTO the contrLocDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contrLocDTO,
     * or with status 400 (Bad Request) if the contrLocDTO is not valid,
     * or with status 500 (Internal Server Error) if the contrLocDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contr-locs")
    @Timed
    public ResponseEntity<ContrLocDTO> updateContrLoc(@Valid @RequestBody ContrLocDTO contrLocDTO) throws URISyntaxException {
        log.debug("REST request to update ContrLoc : {}", contrLocDTO);
        if (contrLocDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContrLocDTO result = contrLocService.save(contrLocDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contrLocDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contr-locs : get all the contrLocs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contrLocs in body
     */
    @GetMapping("/contr-locs")
    @Timed
    public List<ContrLocDTO> getAllContrLocs() {
        log.debug("REST request to get all ContrLocs");
        return contrLocService.findAll();
    }

    /**
     * GET  /contr-locs/:id : get the "id" contrLoc.
     *
     * @param id the id of the contrLocDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contrLocDTO, or with status 404 (Not Found)
     */
    @GetMapping("/contr-locs/{id}")
    @Timed
    public ResponseEntity<ContrLocDTO> getContrLoc(@PathVariable Long id) {
        log.debug("REST request to get ContrLoc : {}", id);
        Optional<ContrLocDTO> contrLocDTO = contrLocService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contrLocDTO);
    }

    /**
     * DELETE  /contr-locs/:id : delete the "id" contrLoc.
     *
     * @param id the id of the contrLocDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contr-locs/{id}")
    @Timed
    public ResponseEntity<Void> deleteContrLoc(@PathVariable Long id) {
        log.debug("REST request to delete ContrLoc : {}", id);
        contrLocService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
