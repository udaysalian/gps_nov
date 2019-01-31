package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.service.dto.NominationPriorityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NominationPriority and its DTO NominationPriorityDTO.
 */
@Mapper(componentModel = "spring", uses = {NominationMapper.class, ActivityMapper.class, ContractMapper.class})
public interface NominationPriorityMapper extends EntityMapper<NominationPriorityDTO, NominationPriority> {

    @Mapping(source = "nomination.id", target = "nominationId")
    @Mapping(source = "activity.id", target = "activityId")
    @Mapping(source = "activity.activityNbr", target = "activityActivityNbr")
    @Mapping(source = "contract.id", target = "contractId")
    @Mapping(source = "contract.contrId", target = "contractContrId")
    NominationPriorityDTO toDto(NominationPriority nominationPriority);

    @Mapping(source = "nominationId", target = "nomination")
    @Mapping(source = "activityId", target = "activity")
    @Mapping(source = "contractId", target = "contract")
    NominationPriority toEntity(NominationPriorityDTO nominationPriorityDTO);

    default NominationPriority fromId(Long id) {
        if (id == null) {
            return null;
        }
        NominationPriority nominationPriority = new NominationPriority();
        nominationPriority.setId(id);
        return nominationPriority;
    }
}
