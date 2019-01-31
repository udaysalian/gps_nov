package com.oilgascs.gps.repository;

import com.oilgascs.gps.domain.RateSched;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RateSched entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RateSchedRepository extends JpaRepository<RateSched, Long> {

}
