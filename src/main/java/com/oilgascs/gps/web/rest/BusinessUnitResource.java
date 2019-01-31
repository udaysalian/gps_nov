package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.BusinessUnitService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.BusinessUnitDTO;
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
 * REST controller for managing BusinessUnit.
 */
@RestController
@RequestMapping("/api")
public class BusinessUnitResource {

    private final Logger log = LoggerFactory.getLogger(BusinessUnitResource.class);

    private static final String ENTITY_NAME = "businessUnit";

    private final BusinessUnitService businessUnitService;

    public BusinessUnitResource(BusinessUnitService businessUnitService) {
        this.businessUnitService = businessUnitService;
    }

    /**
     * POST  /business-units : Create a new businessUnit.
     *
     * @param businessUnitDTO the businessUnitDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new businessUnitDTO, or with status 400 (Bad Request) if the businessUnit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/business-units")
    @Timed
    public ResponseEntity<BusinessUnitDTO> createBusinessUnit(@RequestBody BusinessUnitDTO businessUnitDTO) throws URISyntaxException {
        log.debug("REST request to save BusinessUnit : {}", businessUnitDTO);
        if (businessUnitDTO.getId() != null) {
            throw new BadRequestAlertException("A new businessUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessUnitDTO result = businessUnitService.save(businessUnitDTO);
        return ResponseEntity.created(new URI("/api/business-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /business-units : Updates an existing businessUnit.
     *
     * @param businessUnitDTO the businessUnitDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated businessUnitDTO,
     * or with status 400 (Bad Request) if the businessUnitDTO is not valid,
     * or with status 500 (Internal Server Error) if the businessUnitDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/business-units")
    @Timed
    public ResponseEntity<BusinessUnitDTO> updateBusinessUnit(@RequestBody BusinessUnitDTO businessUnitDTO) throws URISyntaxException {
        log.debug("REST request to update BusinessUnit : {}", businessUnitDTO);
        if (businessUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BusinessUnitDTO result = businessUnitService.save(businessUnitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, businessUnitDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /business-units : get all the businessUnits.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of businessUnits in body
     */
    @GetMapping("/business-units")
    @Timed
    public List<BusinessUnitDTO> getAllBusinessUnits() {
        log.debug("REST request to get all BusinessUnits");
        return businessUnitService.findAll();
    }

    /**
     * GET  /business-units/:id : get the "id" businessUnit.
     *
     * @param id the id of the businessUnitDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the businessUnitDTO, or with status 404 (Not Found)
     */
    @GetMapping("/business-units/{id}")
    @Timed
    public ResponseEntity<BusinessUnitDTO> getBusinessUnit(@PathVariable Long id) {
        log.debug("REST request to get BusinessUnit : {}", id);
        Optional<BusinessUnitDTO> businessUnitDTO = businessUnitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(businessUnitDTO);
    }

    /**
     * DELETE  /business-units/:id : delete the "id" businessUnit.
     *
     * @param id the id of the businessUnitDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/business-units/{id}")
    @Timed
    public ResponseEntity<Void> deleteBusinessUnit(@PathVariable Long id) {
        log.debug("REST request to delete BusinessUnit : {}", id);
        businessUnitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
