package com.oilgascs.gps.repository;

import com.oilgascs.gps.domain.RateSchedVald;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RateSchedVald entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RateSchedValdRepository extends JpaRepository<RateSchedVald, Long> {

}
