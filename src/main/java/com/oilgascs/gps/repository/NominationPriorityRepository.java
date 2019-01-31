package com.oilgascs.gps.repository;

import com.oilgascs.gps.domain.NominationPriority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NominationPriority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NominationPriorityRepository extends JpaRepository<NominationPriority, Long> {

}
