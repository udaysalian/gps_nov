package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.BusinessAssociateAddressService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.BusinessAssociateAddressDTO;
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
 * REST controller for managing BusinessAssociateAddress.
 */
@RestController
@RequestMapping("/api")
public class BusinessAssociateAddressResource {

    private final Logger log = LoggerFactory.getLogger(BusinessAssociateAddressResource.class);

    private static final String ENTITY_NAME = "businessAssociateAddress";

    private final BusinessAssociateAddressService businessAssociateAddressService;

    public BusinessAssociateAddressResource(BusinessAssociateAddressService businessAssociateAddressService) {
        this.businessAssociateAddressService = businessAssociateAddressService;
    }

    /**
     * POST  /business-associate-addresses : Create a new businessAssociateAddress.
     *
     * @param businessAssociateAddressDTO the businessAssociateAddressDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new businessAssociateAddressDTO, or with status 400 (Bad Request) if the businessAssociateAddress has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/business-associate-addresses")
    @Timed
    public ResponseEntity<BusinessAssociateAddressDTO> createBusinessAssociateAddress(@Valid @RequestBody BusinessAssociateAddressDTO businessAssociateAddressDTO) throws URISyntaxException {
        log.debug("REST request to save BusinessAssociateAddress : {}", businessAssociateAddressDTO);
        if (businessAssociateAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new businessAssociateAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessAssociateAddressDTO result = businessAssociateAddressService.save(businessAssociateAddressDTO);
        return ResponseEntity.created(new URI("/api/business-associate-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /business-associate-addresses : Updates an existing businessAssociateAddress.
     *
     * @param businessAssociateAddressDTO the businessAssociateAddressDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated businessAssociateAddressDTO,
     * or with status 400 (Bad Request) if the businessAssociateAddressDTO is not valid,
     * or with status 500 (Internal Server Error) if the businessAssociateAddressDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/business-associate-addresses")
    @Timed
    public ResponseEntity<BusinessAssociateAddressDTO> updateBusinessAssociateAddress(@Valid @RequestBody BusinessAssociateAddressDTO businessAssociateAddressDTO) throws URISyntaxException {
        log.debug("REST request to update BusinessAssociateAddress : {}", businessAssociateAddressDTO);
        if (businessAssociateAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BusinessAssociateAddressDTO result = businessAssociateAddressService.save(businessAssociateAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, businessAssociateAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /business-associate-addresses : get all the businessAssociateAddresses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of businessAssociateAddresses in body
     */
    @GetMapping("/business-associate-addresses")
    @Timed
    public List<BusinessAssociateAddressDTO> getAllBusinessAssociateAddresses() {
        log.debug("REST request to get all BusinessAssociateAddresses");
        return businessAssociateAddressService.findAll();
    }

    /**
     * GET  /business-associate-addresses/:id : get the "id" businessAssociateAddress.
     *
     * @param id the id of the businessAssociateAddressDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the businessAssociateAddressDTO, or with status 404 (Not Found)
     */
    @GetMapping("/business-associate-addresses/{id}")
    @Timed
    public ResponseEntity<BusinessAssociateAddressDTO> getBusinessAssociateAddress(@PathVariable Long id) {
        log.debug("REST request to get BusinessAssociateAddress : {}", id);
        Optional<BusinessAssociateAddressDTO> businessAssociateAddressDTO = businessAssociateAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(businessAssociateAddressDTO);
    }

    /**
     * DELETE  /business-associate-addresses/:id : delete the "id" businessAssociateAddress.
     *
     * @param id the id of the businessAssociateAddressDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/business-associate-addresses/{id}")
    @Timed
    public ResponseEntity<Void> deleteBusinessAssociateAddress(@PathVariable Long id) {
        log.debug("REST request to delete BusinessAssociateAddress : {}", id);
        businessAssociateAddressService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
