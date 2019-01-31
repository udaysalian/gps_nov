package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.service.dto.NominationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Nomination and its DTO NominationDTO.
 */
@Mapper(componentModel = "spring", uses = {ActivityMapper.class, ContractMapper.class})
public interface NominationMapper extends EntityMapper<NominationDTO, Nomination> {

    @Mapping(source = "activity.id", target = "activityId")
    @Mapping(source = "activity.activityNbr", target = "activityActivityNbr")
    @Mapping(source = "contract.id", target = "contractId")
    @Mapping(source = "contract.contrId", target = "contractContrId")
    NominationDTO toDto(Nomination nomination);

    @Mapping(source = "activityId", target = "activity")
    @Mapping(source = "contractId", target = "contract")
    @Mapping(target = "priorities", ignore = true)
    Nomination toEntity(NominationDTO nominationDTO);

    default Nomination fromId(Long id) {
        if (id == null) {
            return null;
        }
        Nomination nomination = new Nomination();
        nomination.setId(id);
        return nomination;
    }
}
