package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.Activity;
import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.domain.Contract;
import com.oilgascs.gps.domain.MeasurementStation;
import com.oilgascs.gps.domain.RateSched;
import com.oilgascs.gps.service.dto.ActivityDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-30T04:27:48+0000",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.15.0.v20180905-0317, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class ActivityMapperImpl implements ActivityMapper {

    @Autowired
    private BusinessAssociateMapper businessAssociateMapper;
    @Autowired
    private RateSchedMapper rateSchedMapper;
    @Autowired
    private MeasurementStationMapper measurementStationMapper;
    @Autowired
    private ContractMapper contractMapper;

    @Override
    public List<Activity> toEntity(List<ActivityDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Activity> list = new ArrayList<Activity>( dtoList.size() );
        for ( ActivityDTO activityDTO : dtoList ) {
            list.add( toEntity( activityDTO ) );
        }

        return list;
    }

    @Override
    public List<ActivityDTO> toDto(List<Activity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ActivityDTO> list = new ArrayList<ActivityDTO>( entityList.size() );
        for ( Activity activity : entityList ) {
            list.add( toDto( activity ) );
        }

        return list;
    }

    @Override
    public ActivityDTO toDto(Activity activity) {
        if ( activity == null ) {
            return null;
        }

        ActivityDTO activityDTO = new ActivityDTO();

        String baAbbr = activityUpstreamBABaAbbr( activity );
        if ( baAbbr != null ) {
            activityDTO.setUpstreamBABaAbbr( baAbbr );
        }
        Long id = activityDeliveryLocationId( activity );
        if ( id != null ) {
            activityDTO.setDeliveryLocationId( id );
        }
        Long id1 = activityUpstreamBAId( activity );
        if ( id1 != null ) {
            activityDTO.setUpstreamBAId( id1 );
        }
        String rateScheduleCD = activityRtSchedRateScheduleCD( activity );
        if ( rateScheduleCD != null ) {
            activityDTO.setRtSchedRateScheduleCD( rateScheduleCD );
        }
        Long id2 = activityDownstreamContractId( activity );
        if ( id2 != null ) {
            activityDTO.setDownstreamContractId( id2 );
        }
        String baAbbr1 = activityDownstreamBABaAbbr( activity );
        if ( baAbbr1 != null ) {
            activityDTO.setDownstreamBABaAbbr( baAbbr1 );
        }
        Long id3 = activityRtSchedId( activity );
        if ( id3 != null ) {
            activityDTO.setRtSchedId( id3 );
        }
        String locationNbr = activityDeliveryLocationLocationNbr( activity );
        if ( locationNbr != null ) {
            activityDTO.setDeliveryLocationLocationNbr( locationNbr );
        }
        String contrId = activityUpstreamContractContrId( activity );
        if ( contrId != null ) {
            activityDTO.setUpstreamContractContrId( contrId );
        }
        Long id4 = activityReceiptLocationId( activity );
        if ( id4 != null ) {
            activityDTO.setReceiptLocationId( id4 );
        }
        Long id5 = activityUpstreamContractId( activity );
        if ( id5 != null ) {
            activityDTO.setUpstreamContractId( id5 );
        }
        Long id6 = activityDownstreamBAId( activity );
        if ( id6 != null ) {
            activityDTO.setDownstreamBAId( id6 );
        }
        String locationNbr1 = activityReceiptLocationLocationNbr( activity );
        if ( locationNbr1 != null ) {
            activityDTO.setReceiptLocationLocationNbr( locationNbr1 );
        }
        String contrId1 = activityDownstreamContractContrId( activity );
        if ( contrId1 != null ) {
            activityDTO.setDownstreamContractContrId( contrId1 );
        }
        activityDTO.setId( activity.getId() );
        activityDTO.setContrId( activity.getContrId() );
        activityDTO.setActivityNbr( activity.getActivityNbr() );
        activityDTO.setTransactionType( activity.getTransactionType() );
        activityDTO.setUpdater( activity.getUpdater() );
        activityDTO.setUpdateTimeStamp( activity.getUpdateTimeStamp() );
        activityDTO.setBusinessUnit( activity.getBusinessUnit() );

        return activityDTO;
    }

    @Override
    public Activity toEntity(ActivityDTO activityDTO) {
        if ( activityDTO == null ) {
            return null;
        }

        Activity activity = new Activity();

        activity.setDownstreamContract( contractMapper.fromId( activityDTO.getDownstreamContractId() ) );
        activity.setDeliveryLocation( measurementStationMapper.fromId( activityDTO.getDeliveryLocationId() ) );
        activity.setDownstreamBA( businessAssociateMapper.fromId( activityDTO.getDownstreamBAId() ) );
        activity.setUpstreamBA( businessAssociateMapper.fromId( activityDTO.getUpstreamBAId() ) );
        activity.setUpstreamContract( contractMapper.fromId( activityDTO.getUpstreamContractId() ) );
        activity.setRtSched( rateSchedMapper.fromId( activityDTO.getRtSchedId() ) );
        activity.setReceiptLocation( measurementStationMapper.fromId( activityDTO.getReceiptLocationId() ) );
        activity.setId( activityDTO.getId() );
        activity.setContrId( activityDTO.getContrId() );
        activity.setActivityNbr( activityDTO.getActivityNbr() );
        activity.setTransactionType( activityDTO.getTransactionType() );
        activity.setUpdater( activityDTO.getUpdater() );
        activity.setUpdateTimeStamp( activityDTO.getUpdateTimeStamp() );
        activity.setBusinessUnit( activityDTO.getBusinessUnit() );

        return activity;
    }

    private String activityUpstreamBABaAbbr(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        BusinessAssociate upstreamBA = activity.getUpstreamBA();
        if ( upstreamBA == null ) {
            return null;
        }
        String baAbbr = upstreamBA.getBaAbbr();
        if ( baAbbr == null ) {
            return null;
        }
        return baAbbr;
    }

    private Long activityDeliveryLocationId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        MeasurementStation deliveryLocation = activity.getDeliveryLocation();
        if ( deliveryLocation == null ) {
            return null;
        }
        Long id = deliveryLocation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long activityUpstreamBAId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        BusinessAssociate upstreamBA = activity.getUpstreamBA();
        if ( upstreamBA == null ) {
            return null;
        }
        Long id = upstreamBA.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String activityRtSchedRateScheduleCD(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        RateSched rtSched = activity.getRtSched();
        if ( rtSched == null ) {
            return null;
        }
        String rateScheduleCD = rtSched.getRateScheduleCD();
        if ( rateScheduleCD == null ) {
            return null;
        }
        return rateScheduleCD;
    }

    private Long activityDownstreamContractId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        Contract downstreamContract = activity.getDownstreamContract();
        if ( downstreamContract == null ) {
            return null;
        }
        Long id = downstreamContract.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String activityDownstreamBABaAbbr(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        BusinessAssociate downstreamBA = activity.getDownstreamBA();
        if ( downstreamBA == null ) {
            return null;
        }
        String baAbbr = downstreamBA.getBaAbbr();
        if ( baAbbr == null ) {
            return null;
        }
        return baAbbr;
    }

    private Long activityRtSchedId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        RateSched rtSched = activity.getRtSched();
        if ( rtSched == null ) {
            return null;
        }
        Long id = rtSched.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String activityDeliveryLocationLocationNbr(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        MeasurementStation deliveryLocation = activity.getDeliveryLocation();
        if ( deliveryLocation == null ) {
            return null;
        }
        String locationNbr = deliveryLocation.getLocationNbr();
        if ( locationNbr == null ) {
            return null;
        }
        return locationNbr;
    }

    private String activityUpstreamContractContrId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        Contract upstreamContract = activity.getUpstreamContract();
        if ( upstreamContract == null ) {
            return null;
        }
        String contrId = upstreamContract.getContrId();
        if ( contrId == null ) {
            return null;
        }
        return contrId;
    }

    private Long activityReceiptLocationId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        MeasurementStation receiptLocation = activity.getReceiptLocation();
        if ( receiptLocation == null ) {
            return null;
        }
        Long id = receiptLocation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long activityUpstreamContractId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        Contract upstreamContract = activity.getUpstreamContract();
        if ( upstreamContract == null ) {
            return null;
        }
        Long id = upstreamContract.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long activityDownstreamBAId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        BusinessAssociate downstreamBA = activity.getDownstreamBA();
        if ( downstreamBA == null ) {
            return null;
        }
        Long id = downstreamBA.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String activityReceiptLocationLocationNbr(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        MeasurementStation receiptLocation = activity.getReceiptLocation();
        if ( receiptLocation == null ) {
            return null;
        }
        String locationNbr = receiptLocation.getLocationNbr();
        if ( locationNbr == null ) {
            return null;
        }
        return locationNbr;
    }

    private String activityDownstreamContractContrId(Activity activity) {
        if ( activity == null ) {
            return null;
        }
        Contract downstreamContract = activity.getDownstreamContract();
        if ( downstreamContract == null ) {
            return null;
        }
        String contrId = downstreamContract.getContrId();
        if ( contrId == null ) {
            return null;
        }
        return contrId;
    }
}
