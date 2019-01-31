package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.domain.BusinessAssociateAddress;
import com.oilgascs.gps.domain.BusinessAssociateContact;
import com.oilgascs.gps.domain.Contact;
import com.oilgascs.gps.service.dto.BusinessAssociateContactDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-30T04:27:48+0000",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.15.0.v20180905-0317, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class BusinessAssociateContactMapperImpl implements BusinessAssociateContactMapper {

    @Autowired
    private BusinessAssociateMapper businessAssociateMapper;
    @Autowired
    private BusinessAssociateAddressMapper businessAssociateAddressMapper;
    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<BusinessAssociateContact> toEntity(List<BusinessAssociateContactDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BusinessAssociateContact> list = new ArrayList<BusinessAssociateContact>( dtoList.size() );
        for ( BusinessAssociateContactDTO businessAssociateContactDTO : dtoList ) {
            list.add( toEntity( businessAssociateContactDTO ) );
        }

        return list;
    }

    @Override
    public List<BusinessAssociateContactDTO> toDto(List<BusinessAssociateContact> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BusinessAssociateContactDTO> list = new ArrayList<BusinessAssociateContactDTO>( entityList.size() );
        for ( BusinessAssociateContact businessAssociateContact : entityList ) {
            list.add( toDto( businessAssociateContact ) );
        }

        return list;
    }

    @Override
    public BusinessAssociateContactDTO toDto(BusinessAssociateContact businessAssociateContact) {
        if ( businessAssociateContact == null ) {
            return null;
        }

        BusinessAssociateContactDTO businessAssociateContactDTO = new BusinessAssociateContactDTO();

        Long id = businessAssociateContactDeliveryAddressId( businessAssociateContact );
        if ( id != null ) {
            businessAssociateContactDTO.setDeliveryAddressId( id );
        }
        Long id1 = businessAssociateContactBusinessAssociateId( businessAssociateContact );
        if ( id1 != null ) {
            businessAssociateContactDTO.setBusinessAssociateId( id1 );
        }
        Long id2 = businessAssociateContactContactId( businessAssociateContact );
        if ( id2 != null ) {
            businessAssociateContactDTO.setContactId( id2 );
        }
        Long id3 = businessAssociateContactMailAddressId( businessAssociateContact );
        if ( id3 != null ) {
            businessAssociateContactDTO.setMailAddressId( id3 );
        }
        String baAbbr = businessAssociateContactBusinessAssociateBaAbbr( businessAssociateContact );
        if ( baAbbr != null ) {
            businessAssociateContactDTO.setBusinessAssociateBaAbbr( baAbbr );
        }
        businessAssociateContactDTO.setId( businessAssociateContact.getId() );
        businessAssociateContactDTO.setBeginDate( businessAssociateContact.getBeginDate() );
        businessAssociateContactDTO.setEndDate( businessAssociateContact.getEndDate() );

        return businessAssociateContactDTO;
    }

    @Override
    public BusinessAssociateContact toEntity(BusinessAssociateContactDTO businessAssociateContactDTO) {
        if ( businessAssociateContactDTO == null ) {
            return null;
        }

        BusinessAssociateContact businessAssociateContact = new BusinessAssociateContact();

        businessAssociateContact.setMailAddress( businessAssociateAddressMapper.fromId( businessAssociateContactDTO.getMailAddressId() ) );
        businessAssociateContact.setDeliveryAddress( businessAssociateAddressMapper.fromId( businessAssociateContactDTO.getDeliveryAddressId() ) );
        businessAssociateContact.setBusinessAssociate( businessAssociateMapper.fromId( businessAssociateContactDTO.getBusinessAssociateId() ) );
        businessAssociateContact.setContact( contactMapper.fromId( businessAssociateContactDTO.getContactId() ) );
        businessAssociateContact.setId( businessAssociateContactDTO.getId() );
        businessAssociateContact.setBeginDate( businessAssociateContactDTO.getBeginDate() );
        businessAssociateContact.setEndDate( businessAssociateContactDTO.getEndDate() );

        return businessAssociateContact;
    }

    private Long businessAssociateContactDeliveryAddressId(BusinessAssociateContact businessAssociateContact) {
        if ( businessAssociateContact == null ) {
            return null;
        }
        BusinessAssociateAddress deliveryAddress = businessAssociateContact.getDeliveryAddress();
        if ( deliveryAddress == null ) {
            return null;
        }
        Long id = deliveryAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long businessAssociateContactBusinessAssociateId(BusinessAssociateContact businessAssociateContact) {
        if ( businessAssociateContact == null ) {
            return null;
        }
        BusinessAssociate businessAssociate = businessAssociateContact.getBusinessAssociate();
        if ( businessAssociate == null ) {
            return null;
        }
        Long id = businessAssociate.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long businessAssociateContactContactId(BusinessAssociateContact businessAssociateContact) {
        if ( businessAssociateContact == null ) {
            return null;
        }
        Contact contact = businessAssociateContact.getContact();
        if ( contact == null ) {
            return null;
        }
        Long id = contact.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long businessAssociateContactMailAddressId(BusinessAssociateContact businessAssociateContact) {
        if ( businessAssociateContact == null ) {
            return null;
        }
        BusinessAssociateAddress mailAddress = businessAssociateContact.getMailAddress();
        if ( mailAddress == null ) {
            return null;
        }
        Long id = mailAddress.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String businessAssociateContactBusinessAssociateBaAbbr(BusinessAssociateContact businessAssociateContact) {
        if ( businessAssociateContact == null ) {
            return null;
        }
        BusinessAssociate businessAssociate = businessAssociateContact.getBusinessAssociate();
        if ( businessAssociate == null ) {
            return null;
        }
        String baAbbr = businessAssociate.getBaAbbr();
        if ( baAbbr == null ) {
            return null;
        }
        return baAbbr;
    }
}
