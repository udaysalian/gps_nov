package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.NominationDTO;

import java.util.List;

public interface GPSNominationService extends NominationService {

    public List<NominationDTO> findAllNomsForTheCurrentUser();
}
