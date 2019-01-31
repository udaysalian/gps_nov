package com.oilgascs.gps.repository;

import com.oilgascs.gps.domain.BusinessAssociate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BusinessAssociate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessAssociateRepository extends JpaRepository<BusinessAssociate, Long> {

}
