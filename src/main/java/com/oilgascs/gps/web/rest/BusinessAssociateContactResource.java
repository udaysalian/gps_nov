package com.oilgascs.gps.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.oilgascs.gps.service.BusinessAssociateContactService;
import com.oilgascs.gps.web.rest.errors.BadRequestAlertException;
import com.oilgascs.gps.web.rest.util.HeaderUtil;
import com.oilgascs.gps.service.dto.BusinessAssociateContactDTO;
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
 * REST controller for managing BusinessAssociateContact.
 */
@RestController
@RequestMapping("/api")
public class BusinessAssociateContactResource {

    private final Logger log = LoggerFactory.getLogger(BusinessAssociateContactResource.class);

    private static final String ENTITY_NAME = "businessAssociateContact";

    private final BusinessAssociateContactService businessAssociateContactService;

    public BusinessAssociateContactResource(BusinessAssociateContactService businessAssociateContactService) {
        this.businessAssociateContactService = businessAssociateContactService;
    }

    /**
     * POST  /business-associate-contacts : Create a new businessAssociateContact.
     *
     * @param businessAssociateContactDTO the businessAssociateContactDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new businessAssociateContactDTO, or with status 400 (Bad Request) if the businessAssociateContact has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/business-associate-contacts")
    @Timed
    public ResponseEntity<BusinessAssociateContactDTO> createBusinessAssociateContact(@RequestBody BusinessAssociateContactDTO businessAssociateContactDTO) throws URISyntaxException {
        log.debug("REST request to save BusinessAssociateContact : {}", businessAssociateContactDTO);
        if (businessAssociateContactDTO.getId() != null) {
            throw new BadRequestAlertException("A new businessAssociateContact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BusinessAssociateContactDTO result = businessAssociateContactService.save(businessAssociateContactDTO);
        return ResponseEntity.created(new URI("/api/business-associate-contacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /business-associate-contacts : Updates an existing businessAssociateContact.
     *
     * @param businessAssociateContactDTO the businessAssociateContactDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated businessAssociateContactDTO,
     * or with status 400 (Bad Request) if the businessAssociateContactDTO is not valid,
     * or with status 500 (Internal Server Error) if the businessAssociateContactDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/business-associate-contacts")
    @Timed
    public ResponseEntity<BusinessAssociateContactDTO> updateBusinessAssociateContact(@RequestBody BusinessAssociateContactDTO businessAssociateContactDTO) throws URISyntaxException {
        log.debug("REST request to update BusinessAssociateContact : {}", businessAssociateContactDTO);
        if (businessAssociateContactDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BusinessAssociateContactDTO result = businessAssociateContactService.save(businessAssociateContactDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, businessAssociateContactDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /business-associate-contacts : get all the businessAssociateContacts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of businessAssociateContacts in body
     */
    @GetMapping("/business-associate-contacts")
    @Timed
    public List<BusinessAssociateContactDTO> getAllBusinessAssociateContacts() {
        log.debug("REST request to get all BusinessAssociateContacts");
        return businessAssociateContactService.findAll();
    }

    /**
     * GET  /business-associate-contacts/:id : get the "id" businessAssociateContact.
     *
     * @param id the id of the businessAssociateContactDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the businessAssociateContactDTO, or with status 404 (Not Found)
     */
    @GetMapping("/business-associate-contacts/{id}")
    @Timed
    public ResponseEntity<BusinessAssociateContactDTO> getBusinessAssociateContact(@PathVariable Long id) {
        log.debug("REST request to get BusinessAssociateContact : {}", id);
        Optional<BusinessAssociateContactDTO> businessAssociateContactDTO = businessAssociateContactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(businessAssociateContactDTO);
    }

    /**
     * DELETE  /business-associate-contacts/:id : delete the "id" businessAssociateContact.
     *
     * @param id the id of the businessAssociateContactDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/business-associate-contacts/{id}")
    @Timed
    public ResponseEntity<Void> deleteBusinessAssociateContact(@PathVariable Long id) {
        log.debug("REST request to delete BusinessAssociateContact : {}", id);
        businessAssociateContactService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
