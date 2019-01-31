package com.oilgascs.gps.repository;

import com.oilgascs.gps.domain.BusinessAssociateContact;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BusinessAssociateContact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessAssociateContactRepository extends JpaRepository<BusinessAssociateContact, Long> {

}
