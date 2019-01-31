package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.service.dto.ActivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Activity and its DTO ActivityDTO.
 */
@Mapper(componentModel = "spring", uses = {BusinessAssociateMapper.class, RateSchedMapper.class, MeasurementStationMapper.class, ContractMapper.class})
public interface ActivityMapper extends EntityMapper<ActivityDTO, Activity> {

    @Mapping(source = "upstreamBA.id", target = "upstreamBAId")
    @Mapping(source = "upstreamBA.baAbbr", target = "upstreamBABaAbbr")
    @Mapping(source = "downstreamBA.id", target = "downstreamBAId")
    @Mapping(source = "downstreamBA.baAbbr", target = "downstreamBABaAbbr")
    @Mapping(source = "rtSched.id", target = "rtSchedId")
    @Mapping(source = "rtSched.rateScheduleCD", target = "rtSchedRateScheduleCD")
    @Mapping(source = "receiptLocation.id", target = "receiptLocationId")
    @Mapping(source = "receiptLocation.locationNbr", target = "receiptLocationLocationNbr")
    @Mapping(source = "deliveryLocation.id", target = "deliveryLocationId")
    @Mapping(source = "deliveryLocation.locationNbr", target = "deliveryLocationLocationNbr")
    @Mapping(source = "upstreamContract.id", target = "upstreamContractId")
    @Mapping(source = "upstreamContract.contrId", target = "upstreamContractContrId")
    @Mapping(source = "downstreamContract.id", target = "downstreamContractId")
    @Mapping(source = "downstreamContract.contrId", target = "downstreamContractContrId")
    ActivityDTO toDto(Activity activity);

    @Mapping(source = "upstreamBAId", target = "upstreamBA")
    @Mapping(source = "downstreamBAId", target = "downstreamBA")
    @Mapping(source = "rtSchedId", target = "rtSched")
    @Mapping(source = "receiptLocationId", target = "receiptLocation")
    @Mapping(source = "deliveryLocationId", target = "deliveryLocation")
    @Mapping(source = "upstreamContractId", target = "upstreamContract")
    @Mapping(source = "downstreamContractId", target = "downstreamContract")
    Activity toEntity(ActivityDTO activityDTO);

    default Activity fromId(Long id) {
        if (id == null) {
            return null;
        }
        Activity activity = new Activity();
        activity.setId(id);
        return activity;
    }
}
