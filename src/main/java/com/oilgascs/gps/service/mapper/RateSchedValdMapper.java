package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.service.dto.RateSchedValdDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RateSchedVald and its DTO RateSchedValdDTO.
 */
@Mapper(componentModel = "spring", uses = {RateSchedMapper.class})
public interface RateSchedValdMapper extends EntityMapper<RateSchedValdDTO, RateSchedVald> {

    @Mapping(source = "rateSchd.id", target = "rateSchdId")
    @Mapping(source = "rateSchd.rateScheduleCD", target = "rateSchdRateScheduleCD")
    RateSchedValdDTO toDto(RateSchedVald rateSchedVald);

    @Mapping(source = "rateSchdId", target = "rateSchd")
    RateSchedVald toEntity(RateSchedValdDTO rateSchedValdDTO);

    default RateSchedVald fromId(Long id) {
        if (id == null) {
            return null;
        }
        RateSchedVald rateSchedVald = new RateSchedVald();
        rateSchedVald.setId(id);
        return rateSchedVald;
    }
}
