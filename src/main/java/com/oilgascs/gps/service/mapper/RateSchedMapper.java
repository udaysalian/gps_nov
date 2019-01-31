package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.service.dto.RateSchedDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RateSched and its DTO RateSchedDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RateSchedMapper extends EntityMapper<RateSchedDTO, RateSched> {


    @Mapping(target = "rtSchedValds", ignore = true)
    RateSched toEntity(RateSchedDTO rateSchedDTO);

    default RateSched fromId(Long id) {
        if (id == null) {
            return null;
        }
        RateSched rateSched = new RateSched();
        rateSched.setId(id);
        return rateSched;
    }
}
