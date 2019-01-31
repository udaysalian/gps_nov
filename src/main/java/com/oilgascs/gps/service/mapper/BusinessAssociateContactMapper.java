package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.service.dto.BusinessAssociateContactDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BusinessAssociateContact and its DTO BusinessAssociateContactDTO.
 */
@Mapper(componentModel = "spring", uses = {BusinessAssociateMapper.class, BusinessAssociateAddressMapper.class, ContactMapper.class})
public interface BusinessAssociateContactMapper extends EntityMapper<BusinessAssociateContactDTO, BusinessAssociateContact> {

    @Mapping(source = "businessAssociate.id", target = "businessAssociateId")
    @Mapping(source = "businessAssociate.baAbbr", target = "businessAssociateBaAbbr")
    @Mapping(source = "mailAddress.id", target = "mailAddressId")
    @Mapping(source = "deliveryAddress.id", target = "deliveryAddressId")
    @Mapping(source = "contact.id", target = "contactId")
    BusinessAssociateContactDTO toDto(BusinessAssociateContact businessAssociateContact);

    @Mapping(source = "businessAssociateId", target = "businessAssociate")
    @Mapping(source = "mailAddressId", target = "mailAddress")
    @Mapping(source = "deliveryAddressId", target = "deliveryAddress")
    @Mapping(source = "contactId", target = "contact")
    BusinessAssociateContact toEntity(BusinessAssociateContactDTO businessAssociateContactDTO);

    default BusinessAssociateContact fromId(Long id) {
        if (id == null) {
            return null;
        }
        BusinessAssociateContact businessAssociateContact = new BusinessAssociateContact();
        businessAssociateContact.setId(id);
        return businessAssociateContact;
    }
}
