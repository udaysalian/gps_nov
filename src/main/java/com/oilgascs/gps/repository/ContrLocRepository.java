package com.oilgascs.gps.repository;

import com.oilgascs.gps.domain.ContrLoc;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ContrLoc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContrLocRepository extends JpaRepository<ContrLoc, Long> {

}
