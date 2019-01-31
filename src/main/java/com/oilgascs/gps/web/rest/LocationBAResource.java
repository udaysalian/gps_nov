package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.LocationBAService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.LocationBADTO;
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
 * REST controller for managing LocationBA.
 */
@RestController
@RequestMapping("/api")
public class LocationBAResource {

    private final Logger log = LoggerFactory.getLogger(LocationBAResource.class);

    private static final String ENTITY_NAME = "locationBA";

    private final LocationBAService locationBAService;

    public LocationBAResource(LocationBAService locationBAService) {
        this.locationBAService = locationBAService;
    }

    /**
     * POST  /location-bas : Create a new locationBA.
     *
     * @param locationBADTO the locationBADTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new locationBADTO, or with status 400 (Bad Request) if the locationBA has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/location-bas")
    @Timed
    public ResponseEntity<LocationBADTO> createLocationBA(@Valid @RequestBody LocationBADTO locationBADTO) throws URISyntaxException {
        log.debug("REST request to save LocationBA : {}", locationBADTO);
        if (locationBADTO.getId() != null) {
            throw new BadRequestAlertException("A new locationBA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LocationBADTO result = locationBAService.save(locationBADTO);
        return ResponseEntity.created(new URI("/api/location-bas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /location-bas : Updates an existing locationBA.
     *
     * @param locationBADTO the locationBADTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated locationBADTO,
     * or with status 400 (Bad Request) if the locationBADTO is not valid,
     * or with status 500 (Internal Server Error) if the locationBADTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/location-bas")
    @Timed
    public ResponseEntity<LocationBADTO> updateLocationBA(@Valid @RequestBody LocationBADTO locationBADTO) throws URISyntaxException {
        log.debug("REST request to update LocationBA : {}", locationBADTO);
        if (locationBADTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LocationBADTO result = locationBAService.save(locationBADTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, locationBADTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /location-bas : get all the locationBAS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of locationBAS in body
     */
    @GetMapping("/location-bas")
    @Timed
    public List<LocationBADTO> getAllLocationBAS() {
        log.debug("REST request to get all LocationBAS");
        return locationBAService.findAll();
    }

    /**
     * GET  /location-bas/:id : get the "id" locationBA.
     *
     * @param id the id of the locationBADTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the locationBADTO, or with status 404 (Not Found)
     */
    @GetMapping("/location-bas/{id}")
    @Timed
    public ResponseEntity<LocationBADTO> getLocationBA(@PathVariable Long id) {
        log.debug("REST request to get LocationBA : {}", id);
        Optional<LocationBADTO> locationBADTO = locationBAService.findOne(id);
        return ResponseUtil.wrapOrNotFound(locationBADTO);
    }

    /**
     * DELETE  /location-bas/:id : delete the "id" locationBA.
     *
     * @param id the id of the locationBADTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/location-bas/{id}")
    @Timed
    public ResponseEntity<Void> deleteLocationBA(@PathVariable Long id) {
        log.debug("REST request to delete LocationBA : {}", id);
        locationBAService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
