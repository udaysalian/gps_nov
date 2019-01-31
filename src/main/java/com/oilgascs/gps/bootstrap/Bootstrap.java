package com.oilgascs.gps.bootstrap;

import com.oilgascs.gps.config.ApplicationProperties;
import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.domain.enumeration.NomRequestStatus;
import com.oilgascs.gps.repository.*;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*
 * Bootstraps the application with test data
 */
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private NominationRepository nominationRepository;
    private ContractRepository contractRepository;
    private BusinessAssociateRepository businessAssociateRepository;
    private BusinessAssociateContactRepository baContactRepository;
    private UserRepository userRepository;
    private BusinessUnitRepository businessUnitRepository;
    private ContactRepository contactRepository;
    private ContrLocRepository contrLocRepository;
    private ActivityRepository activityRepository;
    private MeasurementStationRepository measurementStationRepository;
    private RateSchedRepository rateSchedRepository;
    private RateSchedValdRepository rateSchedValdRepository;
    private AuthorityRepository authorityRepository;

    private PasswordEncoder passwordEncoder;
    private BusinessAssociateAddressRepository baAddressRepository;
    private ApplicationProperties applicationProperties;

    public Bootstrap(ApplicationProperties applicationProperties, PasswordEncoder passwordEncoder, UserRepository userRepository, AuthorityRepository authorityRepository, NominationRepository nominationRepository, ContractRepository contractRepository, BusinessAssociateRepository businessAssociateRepository, BusinessUnitRepository businessUnitRepository, ContactRepository contactRepository, ContrLocRepository contrLocRepository, ActivityRepository activityRepository, MeasurementStationRepository measurementStationRepository, RateSchedRepository rateSchedRepository, RateSchedValdRepository rateSchedValdRepository, BusinessAssociateAddressRepository businessAssociateAddressRepository, BusinessAssociateContactRepository businessAssociateContactRepository) {
        this.applicationProperties = applicationProperties;
        this.nominationRepository = nominationRepository;
        this.contractRepository = contractRepository;
        this.businessAssociateRepository = businessAssociateRepository;
        this.businessUnitRepository = businessUnitRepository;
        this.contactRepository = contactRepository;
        this.contrLocRepository = contrLocRepository;
        this.activityRepository = activityRepository;
        this.measurementStationRepository = measurementStationRepository;
        this.rateSchedRepository = rateSchedRepository;
        this.rateSchedValdRepository = rateSchedValdRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.baAddressRepository = businessAssociateAddressRepository;
        this.baContactRepository = businessAssociateContactRepository;
    }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


    	boolean flag = false;
    	
      //  if(!applicationProperties.getInitData().getEnabled())
        if(!applicationProperties.isLoadData())
            return;

        List<RawNominationRecord> rawNoms = loadNomRecords();
        List<RawContractRecord> rawContractRecords = loadContractRecords();
        List<RawBusinessAssociate> rawBARecords = loadBusinessAssociate();
        List<RawBusinessAssociateContactAddress> rawBAContactAddress = loadBusinessAssociateContactAddress();


        Map<String, Activity> activityHashMap = new HashMap<>();
        Map<String, Contract> contractHashMap = new HashMap<>();
        Map<String, RateSched> rtSchedHashMap = new HashMap<>();
        Map<String, Nomination> nominationHashMap = new HashMap<>();
        Map<String, BusinessUnit> businessUnitHashMap = new HashMap<>();
        Map<String, BusinessAssociate> businessAssociateHashMap = new HashMap<>();
        Map<String, BusinessAssociateAddress> businessAssociateAddressHashMap = new HashMap<>();
        Map<String, BusinessAssociateContact> businessAssociateContactHashMap = new HashMap<>();
        Map<String, Contact> contactMap = new HashMap<>();
        Map<String, MeasurementStation> measurementStationHashMap = new HashMap<>();

        Map<String, User> userMap = new HashMap<>();
        Map<String, Authority> userAuthorityMap = new HashMap<>();

        List<User> users= this.userRepository.findAll();

     buildUsersAndAuthorities(   userMap, userAuthorityMap);
        this.authorityRepository.saveAll(userAuthorityMap.values());
        this.userRepository.saveAll(userMap.values());


        buildMapForBA(rawBARecords, businessAssociateHashMap);


        buildMapforBAContactAddress( rawBAContactAddress,businessAssociateHashMap,businessAssociateContactHashMap, businessAssociateAddressHashMap,contactMap);
        buildMapForContract(rawContractRecords, contractHashMap, businessAssociateHashMap, rtSchedHashMap);
        buildMapForNominationAndActivity(rawNoms, nominationHashMap, activityHashMap,
            measurementStationHashMap,contractHashMap);


        // we now have the test data - save it to the database
        this.businessAssociateRepository.saveAll(businessAssociateHashMap.values());
        this.businessUnitRepository.saveAll(businessUnitHashMap.values());
        this.contactRepository.saveAll(contactMap.values());
        this.baContactRepository.saveAll(businessAssociateContactHashMap.values());
        this.baAddressRepository.saveAll(businessAssociateAddressHashMap.values());
       
        this.measurementStationRepository.saveAll(measurementStationHashMap.values());
        this.activityRepository.saveAll(activityHashMap.values());
      
        this.rateSchedRepository.saveAll(rtSchedHashMap.values());
        this.contractRepository.saveAll(contractHashMap.values());
        this.nominationRepository.saveAll(nominationHashMap.values());
    }



    public void  buildMapForBA( List<RawBusinessAssociate> baRecs, Map<String,
        BusinessAssociate> businessAssociateMap) {
        for (RawBusinessAssociate baRec : baRecs) {
            String key = baRec.baAbbr;
            BusinessAssociate ba = new BusinessAssociate();
            ba.setBaAbbr(key);
            ba.setBaDunsNbr(baRec.baDunsNbr);
            ba.setBaName(baRec.baName);
            ba.setBaNbr(baRec.baNbr);

            businessAssociateMap.putIfAbsent(key, ba);
        }

    }


    public User createnewUser(String loginName, String password, String firstname, String lastName, String role){

        User newUser1 = new User();
        //6;ba1_one;test;BA1 FIRST;BA1 LAST;ba1_one@email.com;;true;es;system;system
        newUser1.setLogin(loginName);

        newUser1.setPassword(passwordEncoder.encode(loginName));
        newUser1.setFirstName(firstname);
        newUser1.setLastName(lastName);
        newUser1.setActivated(true);
        Set<Authority> authorities = new HashSet<>();
        Authority newAuth1 = new Authority();
        newAuth1.setName("ROLE_USER");
        authorities.add(newAuth1);
        newUser1.setAuthorities(authorities );
        return newUser1;
    }

    public void buildUsersAndAuthorities(  Map<String, User> userMap,  Map<String, Authority> userAuthorityMap) {
        ArrayList<User> list = new ArrayList<>();


       list.add(createnewUser("ba1_first", "test","BA1_FIRST","BA1_FIRST_LAST" , "ROLE_USER"));
       list.add(createnewUser("ba1_second", "test","BA1_SECOND","BA1_SECOND_LAST" , "ROLE_USER"));
        list.add(createnewUser("ba1_third", "test","BA1_THIRD","BA1_THIRD_LAST" , "ROLE_USER"));


        list.add(createnewUser("ba2_first", "test","ba2_FIRST","ba2_FIRST_LAST" , "ROLE_USER"));
        list.add(createnewUser("ba2_second", "test","ba2_SECOND","ba2_SECOND_LAST" , "ROLE_USER"));
        list.add(createnewUser("ba2_third", "test","ba2_THIRD","ba2_THIRD_LAST" , "ROLE_USER"));


        list.add(createnewUser("ba3_first", "test","ba3_FIRST","ba3_FIRST_LAST" , "ROLE_USER"));
        list.add(createnewUser("ba3_second", "test","ba3_SECOND","ba3_SECOND_LAST" , "ROLE_USER"));
        list.add(createnewUser("ba3_third", "test","ba3_THIRD","ba3_THIRD_LAST" , "ROLE_USER"));



        list.add(createnewUser("ba4_first", "test","ba4_FIRST","ba4_FIRST_LAST" , "ROLE_USER"));
        list.add(createnewUser("ba4_second", "test","ba4_SECOND","ba4_SECOND_LAST" , "ROLE_USER"));
        list.add(createnewUser("ba4_third", "test","ba4_THIRD","ba4_THIRD_LAST" , "ROLE_USER"));

        //6;ba1_one;test;BA1 FIRST;BA1 LAST;ba1_one@email.com;;true;es;system;system

        for (User user:list
             ) {
            userMap.put(user.toString(),user);
            for (Iterator<Authority> it = user.getAuthorities().iterator(); it.hasNext(); ) {
                Authority authority = it.next();
                userAuthorityMap.put(authority.toString(), authority);
            }
        }

    }
    public void  buildMapforBAContactAddress( List<RawBusinessAssociateContactAddress> bacontactAddresses, Map<String,
        BusinessAssociate> businessAssociateMap,Map<String, BusinessAssociateContact> businessAssociateContactMap, Map<String,
        BusinessAssociateAddress> businessAssociateAddressMap, Map<String,
        Contact> contactMap) {
        for (RawBusinessAssociateContactAddress baContactAddress : bacontactAddresses) {
            String key = baContactAddress.baAbbr;
            BusinessAssociate ba = businessAssociateMap.get(key);
            BusinessAssociateContact baContact = new BusinessAssociateContact();
           baContact.setBusinessAssociate(ba);
            Optional<User> login = userRepository.findOneByLogin(baContactAddress.login);
           Contact contact = getContact( contactMap, baContactAddress.contactFirstName, baContactAddress.contactLastName, ba, login);
           baContact.setContact(contact);

            BusinessAssociateAddress baAddress = new BusinessAssociateAddress();
            baAddress.setBusinessAssociate(ba);
            baAddress.setAddLine1(baContactAddress.street);
            baAddress.setCity(baContactAddress.city);
            baAddress.setZipCode(baContactAddress.zipCode);

           String baContactKey = baContact.getContact().getLoginId().getLogin() + baContact.getBusinessAssociate().getBaAbbr();
           String baAddressKey = baAddress.getAddLine1() + baAddress.getCity() + baAddress.getZipCode() + baAddress.getBusinessAssociate().getBaAbbr();

            businessAssociateContactMap.putIfAbsent(baContactKey, baContact);
            businessAssociateAddressMap.putIfAbsent(baAddressKey, baAddress);
        }

    }

    public String getNomKey(RawNominationRecord nom) {

        String key = nom.gasDate.toString() + nom.contractId + nom.activityNbr + nom.transactionType + nom.businessUnit;

        return key;
    }

    public String getContractKey(String contractId, String businessUnit ) {

        String keyVa = new String(contractId + businessUnit);

        return keyVa;
    }

    public String getActivityKey(RawNominationRecord nom) {

        String key = nom.contractId + nom.activityNbr + nom.transactionType+nom.businessUnit;
        return key;
    }
    public Nomination createNewNomination( RawNominationRecord nomRec){
        Nomination nom = new Nomination();
        nom.setBusinessUnit(nomRec.businessUnit);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");


        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(nomRec.gasDate, formatter);
        nom.setGasDate(localDate);
        nom.setActivity( new Activity());
        nom.setReqestedDlvryQty(nomRec.reqestedDlvryQty);
        nom.setRequestedRcptQty(nomRec.requestedRcptQty);
        nom.setRequestStatus(NomRequestStatus.NOMINATED);

        return nom;

    }

    public Contact getContact(Map<String,Contact > contactMap, String contactFirstName, String contactLastName, BusinessAssociate employedBa, Optional<User> login) {
       try {


           String key = contactFirstName + contactLastName + employedBa.getBaAbbr() + login.get().getLogin();

           if (contactMap.get(key) != null)
               return contactMap.get(key);
           else {
               Contact newcontact = new Contact();
               newcontact.setFirstName(contactFirstName);
               newcontact.setLastName(contactLastName);
               newcontact.setLoginId(login.get());
               newcontact.setEmployedBy(employedBa);

               contactMap.put(key, newcontact);
               return contactMap.get(key);
           }
       } catch (NoSuchElementException ex) {

           System.out.println("No contact login found");
           return null;
       }
    }
    public MeasurementStation getMeasurementStation( Map<String, MeasurementStation> measurementStationMap, String businessunit, String name){
       String measKey = businessunit + name;
        if (measurementStationMap.get(measKey) != null)
            return measurementStationMap.get(measKey);
        else {
            MeasurementStation newStation = new MeasurementStation();
            newStation.setLocationNbr(name);
            newStation.setBusinessUnit(businessunit);
            measurementStationMap.put(measKey,newStation);
            return measurementStationMap.get(measKey);
        }
    }


    public Activity createActivity( RawNominationRecord nomRec){
        Activity act = new Activity();
        act.setBusinessUnit(nomRec.businessUnit);
        act.setActivityNbr(nomRec.activityNbr);
        act.setContrId(nomRec.contractId);
        return act;

    }

    public Contract getContract( Map<String, Contract> contractMap, String contrId, String businessunit){
        String key = getContractKey(contrId, businessunit);
        if (contractMap.get(key) != null)
            return contractMap.get(key);
        else {
            Contract contr = new Contract();
            contr.setContrId(contrId);
            contr.setBusinessUnit(businessunit);
            contractMap.put(key,contr);
            return contractMap.get(key);
        }
    }


    public RateSched getRtSched( Map<String, RateSched> rtSchedMap, String rtSchedId, String rtSchedDesc, String businessunit){
        String key =  rtSchedId + rtSchedDesc + businessunit;
        if (rtSchedMap.get(key) != null)
            return rtSchedMap.get(key);
        else {
            RateSched rtSched = new RateSched();
          rtSched.setRateScheduleCD(rtSchedId);
          rtSched.setBusinessUnit(businessunit);

            rtSchedMap.put(key,rtSched);
            return rtSchedMap.get(key);
        }
    }


    private void buildMapForNominationAndActivity(List<RawNominationRecord> nominationRecords, Map<String,
        Nomination> nomRecToNomination, Map<String, Activity> nomrecToActivityMap,
                                                  Map<String, MeasurementStation> measurementStationMap,
                                                  Map<String, Contract> nomToContractMap  ) {
        for (RawNominationRecord nomRecord : nominationRecords) {
            String key = getNomKey(nomRecord);
            Nomination nom = createNewNomination(nomRecord);

            nomRecToNomination.putIfAbsent(key, nom);
            String activityKey = getActivityKey(nomRecord);
            Activity newActivity =  createActivity(nomRecord);
            nomrecToActivityMap.putIfAbsent(activityKey, newActivity);
            MeasurementStation rcpLocation = getMeasurementStation(measurementStationMap,nomRecord.businessUnit, nomRecord.rcpLocation);
            newActivity.setReceiptLocation(rcpLocation);
            MeasurementStation dlvyLocation = getMeasurementStation(measurementStationMap, nomRecord.businessUnit, nomRecord.deliveryLocation);
            newActivity.setDeliveryLocation(dlvyLocation);
            nom.setActivity(newActivity);
            Contract contract = getContract(nomToContractMap,nomRecord.contractId, nomRecord.businessUnit);
            nom.setContract(contract);


        }
    }

    private List<RawNominationRecord> loadNomRecords() {
        CsvLoader csvLoader = new CsvLoader();
        return csvLoader.loadObjectList(RawNominationRecord.class, "./nominationRecords.csv");
    }

    private List<RawContractRecord> loadContractRecords() {
        CsvLoader csvLoader = new CsvLoader();
        return csvLoader.loadObjectList(RawContractRecord.class, "./contracts.csv");
    }
    private List<RawBusinessAssociate> loadBusinessAssociate() {
        CsvLoader csvLoader = new CsvLoader();
        return csvLoader.loadObjectList(RawBusinessAssociate.class, "./business_associate.csv");
    }
    private List<RawBusinessAssociateContactAddress> loadBusinessAssociateContactAddress() {
        CsvLoader csvLoader = new CsvLoader();
        return csvLoader.loadObjectList(RawBusinessAssociateContactAddress.class, "./bacontactaddresses.csv");
    }
    private void buildMapForContract(List<RawContractRecord> contractRecords,
                                     Map<String, Contract> nomToContractMap , Map<String, BusinessAssociate> businessAssociateMap, Map<String, RateSched> rtSchedMap) {
        for (RawContractRecord contractRecord : contractRecords) {
            String key = getContractKey(contractRecord.business_unit, contractRecord.contr_id);

            BusinessAssociate ba = businessAssociateMap.get(contractRecord.ba_abbr);

            Contract newContr = getContract(nomToContractMap, contractRecord.contr_id, contractRecord.business_unit);
            newContr.setBusAssoc(ba);

            RateSched rtSched = getRtSched(rtSchedMap, contractRecord.rt_schedule_cd, contractRecord.rt_schedule_desc, contractRecord.business_unit);

            System.out.println("Creating rt schedule object with " + contractRecord.rt_schedule_cd + "bu :" + contractRecord.business_unit);

            newContr.setRtSched(rtSched);


        }
    }
}
