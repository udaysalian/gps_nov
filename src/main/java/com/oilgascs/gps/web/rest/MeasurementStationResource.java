package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.MeasurementStationService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.MeasurementStationDTO;
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
 * REST controller for managing MeasurementStation.
 */
@RestController
@RequestMapping("/api")
public class MeasurementStationResource {

    private final Logger log = LoggerFactory.getLogger(MeasurementStationResource.class);

    private static final String ENTITY_NAME = "measurementStation";

    private final MeasurementStationService measurementStationService;

    public MeasurementStationResource(MeasurementStationService measurementStationService) {
        this.measurementStationService = measurementStationService;
    }

    /**
     * POST  /measurement-stations : Create a new measurementStation.
     *
     * @param measurementStationDTO the measurementStationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new measurementStationDTO, or with status 400 (Bad Request) if the measurementStation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/measurement-stations")
    @Timed
    public ResponseEntity<MeasurementStationDTO> createMeasurementStation(@Valid @RequestBody MeasurementStationDTO measurementStationDTO) throws URISyntaxException {
        log.debug("REST request to save MeasurementStation : {}", measurementStationDTO);
        if (measurementStationDTO.getId() != null) {
            throw new BadRequestAlertException("A new measurementStation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MeasurementStationDTO result = measurementStationService.save(measurementStationDTO);
        return ResponseEntity.created(new URI("/api/measurement-stations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /measurement-stations : Updates an existing measurementStation.
     *
     * @param measurementStationDTO the measurementStationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated measurementStationDTO,
     * or with status 400 (Bad Request) if the measurementStationDTO is not valid,
     * or with status 500 (Internal Server Error) if the measurementStationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/measurement-stations")
    @Timed
    public ResponseEntity<MeasurementStationDTO> updateMeasurementStation(@Valid @RequestBody MeasurementStationDTO measurementStationDTO) throws URISyntaxException {
        log.debug("REST request to update MeasurementStation : {}", measurementStationDTO);
        if (measurementStationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeasurementStationDTO result = measurementStationService.save(measurementStationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, measurementStationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /measurement-stations : get all the measurementStations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of measurementStations in body
     */
    @GetMapping("/measurement-stations")
    @Timed
    public List<MeasurementStationDTO> getAllMeasurementStations() {
        log.debug("REST request to get all MeasurementStations");
        return measurementStationService.findAll();
    }

    /**
     * GET  /measurement-stations/:id : get the "id" measurementStation.
     *
     * @param id the id of the measurementStationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the measurementStationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/measurement-stations/{id}")
    @Timed
    public ResponseEntity<MeasurementStationDTO> getMeasurementStation(@PathVariable Long id) {
        log.debug("REST request to get MeasurementStation : {}", id);
        Optional<MeasurementStationDTO> measurementStationDTO = measurementStationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(measurementStationDTO);
    }

    /**
     * DELETE  /measurement-stations/:id : delete the "id" measurementStation.
     *
     * @param id the id of the measurementStationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/measurement-stations/{id}")
    @Timed
    public ResponseEntity<Void> deleteMeasurementStation(@PathVariable Long id) {
        log.debug("REST request to delete MeasurementStation : {}", id);
        measurementStationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
