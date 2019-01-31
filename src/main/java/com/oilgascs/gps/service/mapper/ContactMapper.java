package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.*;
import com.oilgascs.gps.service.dto.ContactDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Contact and its DTO ContactDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, BusinessAssociateMapper.class})
public interface ContactMapper extends EntityMapper<ContactDTO, Contact> {

    @Mapping(source = "loginId.id", target = "loginIdId")
    @Mapping(source = "loginId.login", target = "loginIdLogin")
    @Mapping(source = "employedBy.id", target = "employedById")
    @Mapping(source = "employedBy.baAbbr", target = "employedByBaAbbr")
    ContactDTO toDto(Contact contact);

    @Mapping(source = "loginIdId", target = "loginId")
    @Mapping(source = "employedById", target = "employedBy")
    Contact toEntity(ContactDTO contactDTO);

    default Contact fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
}
