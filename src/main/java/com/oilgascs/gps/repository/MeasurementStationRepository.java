package com.oilgascs.gps.repository;

import com.oilgascs.gps.domain.MeasurementStation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MeasurementStation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MeasurementStationRepository extends JpaRepository<MeasurementStation, Long> {

}
