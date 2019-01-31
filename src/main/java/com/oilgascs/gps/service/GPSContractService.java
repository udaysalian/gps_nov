package com.oilgascs.gps.service;

import com.oilgascs.gps.service.dto.ContractDTO;


import java.util.List;

public interface GPSContractService extends  ContractService{
    public List<ContractDTO> findAllContractsForTheCurrentUser();
}
