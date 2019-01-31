package com.oilgascs.gps.repository;


import com.oilgascs.gps.domain.Nomination;
import com.oilgascs.gps.repository.NominationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the Nomination entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NetraNominationRepository extends NominationRepository{



    @Query("select nominations from Nomination nominations where nominations.contract.id  in :contractIdList")
    List<Nomination> findAllNomsByContractIdList(@Param("contractIdList") List<Long> contractIdList);

}


