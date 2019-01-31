package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.domain.Contact;
import com.oilgascs.gps.domain.Contract;
import com.oilgascs.gps.repository.BusinessAssociateContactRepository;
import com.oilgascs.gps.repository.ContractRepository;
import com.oilgascs.gps.repository.NetraContactRepository;
import com.oilgascs.gps.security.AuthoritiesConstants;
import com.oilgascs.gps.security.SecurityUtils;
import com.oilgascs.gps.service.GPSContractService;
import com.oilgascs.gps.service.dto.ContractDTO;
import com.oilgascs.gps.service.mapper.ContractMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class GPSContractServiceImpl extends ContractServiceImpl implements GPSContractService{

    private final Logger log = LoggerFactory.getLogger(GPSNominationServiceImpl.class);
    private ContractRepository _contractRepository;
    private BusinessAssociateContactRepository _baContactRepository;
    private NetraContactRepository _contactRepository;
    private final ContractMapper _contractMapper;



    public GPSContractServiceImpl(NetraContactRepository contactRepository, ContractRepository contractRepository, BusinessAssociateContactRepository baContactRepository, ContractMapper contractMapper) {
        super(contractRepository, contractMapper);
        _contactRepository = contactRepository;
        _contractRepository = contractRepository;
        _baContactRepository = baContactRepository;

        _contractMapper= contractMapper;
    }

    //add method to retrieve noms authorized for the current user
    @Override
    @Transactional(readOnly = true)
    public List<ContractDTO> findAll() {
        log.debug("Request to get all Nominations");

        if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN))
            return _contractRepository.findAll().stream()
                .map(_contractMapper::toDto)
                .collect(Collectors.toCollection(LinkedList::new));
        else
            return findAllContractsForTheCurrentUser();

    }


    @Override
    public List<ContractDTO> findAllContractsForTheCurrentUser() {

        //find the user contact
        Contact userContact = _contactRepository.findByUserIsCurrentUser();
        //for this contact find its employed by BA
        BusinessAssociate ba = userContact.getEmployedBy();

        Set<Contract> baContracts = ba.getContracts();


        return baContracts.stream()
            .map(_contractMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
