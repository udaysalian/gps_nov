package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.service.dto.ContrLocDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ContrLoc and its DTO ContrLocDTO.
 */
@Mapper(componentModel = "spring", uses = {ContractMapper.class, MeasurementStationMapper.class})
public interface ContrLocMapper extends EntityMapper<ContrLocDTO, ContrLoc> {

    @Mapping(source = "contract.id", target = "contractId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "location.locationNbr", target = "locationLocationNbr")
    ContrLocDTO toDto(ContrLoc contrLoc);

    @Mapping(source = "contractId", target = "contract")
    @Mapping(source = "locationId", target = "location")
    ContrLoc toEntity(ContrLocDTO contrLocDTO);

    default ContrLoc fromId(Long id) {
        if (id == null) {
            return null;
        }
        ContrLoc contrLoc = new ContrLoc();
        contrLoc.setId(id);
        return contrLoc;
    }
}
