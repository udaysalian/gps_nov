package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.BusinessAssociateService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.BusinessAssociateDTO;
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
 * REST controller for managing BusinessAssociate.
 */
@RestController
@RequestMapping("/api")
public class BusinessAssociateResource {

    private final Logger log = LoggerFactory.getLogger(BusinessAssociateResource.class);

    private static final String ENTITY_NAME = "businessAssociate";

    private final BusinessAssociateService businessAssociateService;

    public BusinessAssociateResource(BusinessAssociateService businessAssociateService) {
        this.businessAssociateService = businessAssociateService;
    }

    /**
     * POST  /business-associates : Create a new businessAssociate.
     *
     * @param businessAssociateDTO the businessAssociateDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new businessAssociateDTO, or with status 400 (Bad Request) if the businessAssociate has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/business-associates")
    @Timed
    public ResponseEntity<BusinessAssociateDTO> createBusinessAssociate(@Valid @RequestBody BusinessAssociateDTO businessAssociateDTO) throws URISyntaxException {
        log.debug("REST request to save BusinessAssociate : {}", businessAssociateDTO);
        if (businessAssociateDTO.getId() != null) {
            throw new BadRequestAlertException("A new businessAssociate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessAssociateDTO result = businessAssociateService.save(businessAssociateDTO);
        return ResponseEntity.created(new URI("/api/business-associates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /business-associates : Updates an existing businessAssociate.
     *
     * @param businessAssociateDTO the businessAssociateDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated businessAssociateDTO,
     * or with status 400 (Bad Request) if the businessAssociateDTO is not valid,
     * or with status 500 (Internal Server Error) if the businessAssociateDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/business-associates")
    @Timed
    public ResponseEntity<BusinessAssociateDTO> updateBusinessAssociate(@Valid @RequestBody BusinessAssociateDTO businessAssociateDTO) throws URISyntaxException {
        log.debug("REST request to update BusinessAssociate : {}", businessAssociateDTO);
        if (businessAssociateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BusinessAssociateDTO result = businessAssociateService.save(businessAssociateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, businessAssociateDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /business-associates : get all the businessAssociates.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of businessAssociates in body
     */
    @GetMapping("/business-associates")
    @Timed
    public List<BusinessAssociateDTO> getAllBusinessAssociates() {
        log.debug("REST request to get all BusinessAssociates");
        return businessAssociateService.findAll();
    }

    /**
     * GET  /business-associates/:id : get the "id" businessAssociate.
     *
     * @param id the id of the businessAssociateDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the businessAssociateDTO, or with status 404 (Not Found)
     */
    @GetMapping("/business-associates/{id}")
    @Timed
    public ResponseEntity<BusinessAssociateDTO> getBusinessAssociate(@PathVariable Long id) {
        log.debug("REST request to get BusinessAssociate : {}", id);
        Optional<BusinessAssociateDTO> businessAssociateDTO = businessAssociateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(businessAssociateDTO);
    }

    /**
     * DELETE  /business-associates/:id : delete the "id" businessAssociate.
     *
     * @param id the id of the businessAssociateDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/business-associates/{id}")
    @Timed
    public ResponseEntity<Void> deleteBusinessAssociate(@PathVariable Long id) {
        log.debug("REST request to delete BusinessAssociate : {}", id);
        businessAssociateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
