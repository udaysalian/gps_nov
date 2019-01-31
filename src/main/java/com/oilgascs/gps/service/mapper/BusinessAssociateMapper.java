package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.service.dto.BusinessAssociateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BusinessAssociate and its DTO BusinessAssociateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BusinessAssociateMapper extends EntityMapper<BusinessAssociateDTO, BusinessAssociate> {


    @Mapping(target = "contracts", ignore = true)
    @Mapping(target = "businessAssociateAddresses", ignore = true)
    BusinessAssociate toEntity(BusinessAssociateDTO businessAssociateDTO);

    default BusinessAssociate fromId(Long id) {
        if (id == null) {
            return null;
        }
        BusinessAssociate businessAssociate = new BusinessAssociate();
        businessAssociate.setId(id);
        return businessAssociate;
    }
}
