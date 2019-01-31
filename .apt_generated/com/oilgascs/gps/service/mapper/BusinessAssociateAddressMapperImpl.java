package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.domain.BusinessAssociateAddress;
import com.oilgascs.gps.service.dto.BusinessAssociateAddressDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-30T04:27:49+0000",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.15.0.v20180905-0317, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class BusinessAssociateAddressMapperImpl implements BusinessAssociateAddressMapper {

    @Autowired
    private BusinessAssociateMapper businessAssociateMapper;

    @Override
    public List<BusinessAssociateAddress> toEntity(List<BusinessAssociateAddressDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BusinessAssociateAddress> list = new ArrayList<BusinessAssociateAddress>( dtoList.size() );
        for ( BusinessAssociateAddressDTO businessAssociateAddressDTO : dtoList ) {
            list.add( toEntity( businessAssociateAddressDTO ) );
        }

        return list;
    }

    @Override
    public List<BusinessAssociateAddressDTO> toDto(List<BusinessAssociateAddress> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BusinessAssociateAddressDTO> list = new ArrayList<BusinessAssociateAddressDTO>( entityList.size() );
        for ( BusinessAssociateAddress businessAssociateAddress : entityList ) {
            list.add( toDto( businessAssociateAddress ) );
        }

        return list;
    }

    @Override
    public BusinessAssociateAddressDTO toDto(BusinessAssociateAddress businessAssociateAddress) {
        if ( businessAssociateAddress == null ) {
            return null;
        }

        BusinessAssociateAddressDTO businessAssociateAddressDTO = new BusinessAssociateAddressDTO();

        Long id = businessAssociateAddressBusinessAssociateId( businessAssociateAddress );
        if ( id != null ) {
            businessAssociateAddressDTO.setBusinessAssociateId( id );
        }
        businessAssociateAddressDTO.setId( businessAssociateAddress.getId() );
        businessAssociateAddressDTO.setAddLine1( businessAssociateAddress.getAddLine1() );
        businessAssociateAddressDTO.setAddressNbr( businessAssociateAddress.getAddressNbr() );
        businessAssociateAddressDTO.setAddLine2( businessAssociateAddress.getAddLine2() );
        businessAssociateAddressDTO.setCity( businessAssociateAddress.getCity() );
        businessAssociateAddressDTO.setState( businessAssociateAddress.getState() );
        businessAssociateAddressDTO.setZipCode( businessAssociateAddress.getZipCode() );

        return businessAssociateAddressDTO;
    }

    @Override
    public BusinessAssociateAddress toEntity(BusinessAssociateAddressDTO businessAssociateAddressDTO) {
        if ( businessAssociateAddressDTO == null ) {
            return null;
        }

        BusinessAssociateAddress businessAssociateAddress = new BusinessAssociateAddress();

        businessAssociateAddress.setBusinessAssociate( businessAssociateMapper.fromId( businessAssociateAddressDTO.getBusinessAssociateId() ) );
        businessAssociateAddress.setId( businessAssociateAddressDTO.getId() );
        businessAssociateAddress.setAddLine1( businessAssociateAddressDTO.getAddLine1() );
        businessAssociateAddress.setAddressNbr( businessAssociateAddressDTO.getAddressNbr() );
        businessAssociateAddress.setAddLine2( businessAssociateAddressDTO.getAddLine2() );
        businessAssociateAddress.setCity( businessAssociateAddressDTO.getCity() );
        businessAssociateAddress.setState( businessAssociateAddressDTO.getState() );
        businessAssociateAddress.setZipCode( businessAssociateAddressDTO.getZipCode() );

        return businessAssociateAddress;
    }

    private Long businessAssociateAddressBusinessAssociateId(BusinessAssociateAddress businessAssociateAddress) {
        if ( businessAssociateAddress == null ) {
            return null;
        }
        BusinessAssociate businessAssociate = businessAssociateAddress.getBusinessAssociate();
        if ( businessAssociate == null ) {
            return null;
        }
        Long id = businessAssociate.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
