package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.domain.Contact;
import com.oilgascs.gps.domain.User;
import com.oilgascs.gps.service.dto.ContactDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-30T04:27:46+0000",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.15.0.v20180905-0317, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BusinessAssociateMapper businessAssociateMapper;

    @Override
    public List<Contact> toEntity(List<ContactDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Contact> list = new ArrayList<Contact>( dtoList.size() );
        for ( ContactDTO contactDTO : dtoList ) {
            list.add( toEntity( contactDTO ) );
        }

        return list;
    }

    @Override
    public List<ContactDTO> toDto(List<Contact> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ContactDTO> list = new ArrayList<ContactDTO>( entityList.size() );
        for ( Contact contact : entityList ) {
            list.add( toDto( contact ) );
        }

        return list;
    }

    @Override
    public ContactDTO toDto(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDTO contactDTO = new ContactDTO();

        Long id = contactEmployedById( contact );
        if ( id != null ) {
            contactDTO.setEmployedById( id );
        }
        String login = contactLoginIdLogin( contact );
        if ( login != null ) {
            contactDTO.setLoginIdLogin( login );
        }
        String baAbbr = contactEmployedByBaAbbr( contact );
        if ( baAbbr != null ) {
            contactDTO.setEmployedByBaAbbr( baAbbr );
        }
        Long id1 = contactLoginIdId( contact );
        if ( id1 != null ) {
            contactDTO.setLoginIdId( id1 );
        }
        contactDTO.setId( contact.getId() );
        contactDTO.setFirstName( contact.getFirstName() );
        contactDTO.setLastName( contact.getLastName() );
        contactDTO.setUpdater( contact.getUpdater() );
        contactDTO.setUpdateTimestamp( contact.getUpdateTimestamp() );

        return contactDTO;
    }

    @Override
    public Contact toEntity(ContactDTO contactDTO) {
        if ( contactDTO == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setLoginId( userMapper.userFromId( contactDTO.getLoginIdId() ) );
        contact.setEmployedBy( businessAssociateMapper.fromId( contactDTO.getEmployedById() ) );
        contact.setId( contactDTO.getId() );
        contact.setFirstName( contactDTO.getFirstName() );
        contact.setLastName( contactDTO.getLastName() );
        contact.setUpdater( contactDTO.getUpdater() );
        contact.setUpdateTimestamp( contactDTO.getUpdateTimestamp() );

        return contact;
    }

    private Long contactEmployedById(Contact contact) {
        if ( contact == null ) {
            return null;
        }
        BusinessAssociate employedBy = contact.getEmployedBy();
        if ( employedBy == null ) {
            return null;
        }
        Long id = employedBy.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String contactLoginIdLogin(Contact contact) {
        if ( contact == null ) {
            return null;
        }
        User loginId = contact.getLoginId();
        if ( loginId == null ) {
            return null;
        }
        String login = loginId.getLogin();
        if ( login == null ) {
            return null;
        }
        return login;
    }

    private String contactEmployedByBaAbbr(Contact contact) {
        if ( contact == null ) {
            return null;
        }
        BusinessAssociate employedBy = contact.getEmployedBy();
        if ( employedBy == null ) {
            return null;
        }
        String baAbbr = employedBy.getBaAbbr();
        if ( baAbbr == null ) {
            return null;
        }
        return baAbbr;
    }

    private Long contactLoginIdId(Contact contact) {
        if ( contact == null ) {
            return null;
        }
        User loginId = contact.getLoginId();
        if ( loginId == null ) {
            return null;
        }
        Long id = loginId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
