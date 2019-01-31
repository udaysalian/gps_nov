package com.oilgascs.gps.service.impl;

import com.oilgascs.gps.repository.NetraContactRepository;
import com.oilgascs.gps.repository.NetraNominationRepository;
import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.domain.BusinessUnit;
import com.oilgascs.gps.domain.Contact;
import com.oilgascs.gps.domain.Contract;
import com.oilgascs.gps.domain.Nomination;
import com.oilgascs.gps.repository.BusinessAssociateContactRepository;
import com.oilgascs.gps.security.AuthoritiesConstants;
import com.oilgascs.gps.security.SecurityUtils;
import com.oilgascs.gps.service.GPSNominationService;
import com.oilgascs.gps.service.dto.NominationDTO;
import com.oilgascs.gps.service.mapper.NominationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class GPSNominationServiceImpl extends NominationServiceImpl implements GPSNominationService{

    private final Logger log = LoggerFactory.getLogger(GPSNominationServiceImpl.class);
    private NetraContactRepository _contactRepository;
    private BusinessAssociateContactRepository _baContactRepository;
    private NetraNominationRepository _nomNominationRepository;
    private final NominationMapper _nominationMapper;



    public GPSNominationServiceImpl(NetraNominationRepository nominationRepository, NetraContactRepository contactRepository, BusinessAssociateContactRepository baContactRepository, NominationMapper nominationMapper) {
        super(nominationRepository, nominationMapper);
        _contactRepository = contactRepository;
        _baContactRepository = baContactRepository;
        _nomNominationRepository = nominationRepository;
        _nominationMapper= nominationMapper;
    }

    //add method to retrieve noms authorized for the current user
    @Override
    @Transactional(readOnly = true)
    public List<NominationDTO> findAll() {
        log.debug("Request to get all Nominations");

        if(SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN))
             return _nomNominationRepository.findAll().stream()
                  .map(_nominationMapper::toDto)
               .collect(Collectors.toCollection(LinkedList::new));
        else
             return findAllNomsForTheCurrentUser();

    }


    @Override
    public List<NominationDTO> findAllNomsForTheCurrentUser() {

        //find the user contact
        Contact userContact = _contactRepository.findByUserIsCurrentUser();
        //for this contact find its employed by BA
        BusinessAssociate ba = userContact.getEmployedBy();

        Set<Contract> baContracts = ba.getContracts();

        UserDetails user1 = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.oilgascs.gps.security.GPSUserDetails user = (com.oilgascs.gps.security.GPSUserDetails)user1;
        // set the session bu if not set to 
      /*  if(user.getBu() == null) {
	        BusinessUnit newBU = new BusinessUnit();
	        newBU.setId((long)1);
	        newBU.setBusinessUnit("B1");
	        user.setBu(newBU);
        }*/
        List<Long> contractIdList = new ArrayList<>();
        baContracts.forEach(contract->{
        //	if(user.getBu().getBusinessUnit().equals(contract.getBusinessUnit()))
        		contractIdList.add(contract.getId());
        	});

        List<Nomination> noms = _nomNominationRepository.findAllNomsByContractIdList(contractIdList);
        return noms.stream()
        .map(_nominationMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
    }
}
