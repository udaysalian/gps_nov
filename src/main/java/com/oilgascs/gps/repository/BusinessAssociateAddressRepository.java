package com.oilgascs.gps.repository;

import com.oilgascs.gps.domain.BusinessAssociateAddress;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BusinessAssociateAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessAssociateAddressRepository extends JpaRepository<BusinessAssociateAddress, Long> {

}
