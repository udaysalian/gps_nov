package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.service.dto.BusinessAssociateDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-30T04:27:49+0000",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.15.0.v20180905-0317, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class BusinessAssociateMapperImpl implements BusinessAssociateMapper {

    @Override
    public BusinessAssociateDTO toDto(BusinessAssociate entity) {
        if ( entity == null ) {
            return null;
        }

        BusinessAssociateDTO businessAssociateDTO = new BusinessAssociateDTO();

        businessAssociateDTO.setId( entity.getId() );
        businessAssociateDTO.setBaName( entity.getBaName() );
        businessAssociateDTO.setBaAbbr( entity.getBaAbbr() );
        businessAssociateDTO.setBaNbr( entity.getBaNbr() );
        businessAssociateDTO.setBaDunsNbr( entity.getBaDunsNbr() );

        return businessAssociateDTO;
    }

    @Override
    public List<BusinessAssociate> toEntity(List<BusinessAssociateDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BusinessAssociate> list = new ArrayList<BusinessAssociate>( dtoList.size() );
        for ( BusinessAssociateDTO businessAssociateDTO : dtoList ) {
            list.add( toEntity( businessAssociateDTO ) );
        }

        return list;
    }

    @Override
    public List<BusinessAssociateDTO> toDto(List<BusinessAssociate> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BusinessAssociateDTO> list = new ArrayList<BusinessAssociateDTO>( entityList.size() );
        for ( BusinessAssociate businessAssociate : entityList ) {
            list.add( toDto( businessAssociate ) );
        }

        return list;
    }

    @Override
    public BusinessAssociate toEntity(BusinessAssociateDTO businessAssociateDTO) {
        if ( businessAssociateDTO == null ) {
            return null;
        }

        BusinessAssociate businessAssociate = new BusinessAssociate();

        businessAssociate.setId( businessAssociateDTO.getId() );
        businessAssociate.setBaName( businessAssociateDTO.getBaName() );
        businessAssociate.setBaAbbr( businessAssociateDTO.getBaAbbr() );
        businessAssociate.setBaNbr( businessAssociateDTO.getBaNbr() );
        businessAssociate.setBaDunsNbr( businessAssociateDTO.getBaDunsNbr() );

        return businessAssociate;
    }
}
