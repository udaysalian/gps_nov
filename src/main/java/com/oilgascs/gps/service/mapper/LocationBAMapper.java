package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.service.dto.LocationBADTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LocationBA and its DTO LocationBADTO.
 */
@Mapper(componentModel = "spring", uses = {BusinessAssociateMapper.class})
public interface LocationBAMapper extends EntityMapper<LocationBADTO, LocationBA> {

    @Mapping(source = "busAssoc.id", target = "busAssocId")
    @Mapping(source = "busAssoc.baAbbr", target = "busAssocBaAbbr")
    LocationBADTO toDto(LocationBA locationBA);

    @Mapping(source = "busAssocId", target = "busAssoc")
    LocationBA toEntity(LocationBADTO locationBADTO);

    default LocationBA fromId(Long id) {
        if (id == null) {
            return null;
        }
        LocationBA locationBA = new LocationBA();
        locationBA.setId(id);
        return locationBA;
    }
}
