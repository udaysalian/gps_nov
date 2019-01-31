package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.BusinessUnit;
import com.oilgascs.gps.service.dto.BusinessUnitDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-30T04:27:49+0000",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.15.0.v20180905-0317, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class BusinessUnitMapperImpl implements BusinessUnitMapper {

    @Override
    public BusinessUnit toEntity(BusinessUnitDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BusinessUnit businessUnit = new BusinessUnit();

        businessUnit.setId( dto.getId() );
        businessUnit.setBusinessUnit( dto.getBusinessUnit() );
        businessUnit.setBusinessAssociateNbr( dto.getBusinessAssociateNbr() );
        businessUnit.setEdiPipeId( dto.getEdiPipeId() );
        byte[] companyLogo = dto.getCompanyLogo();
        if ( companyLogo != null ) {
            businessUnit.setCompanyLogo( Arrays.copyOf( companyLogo, companyLogo.length ) );
        }
        businessUnit.setCompanyLogoContentType( dto.getCompanyLogoContentType() );
        businessUnit.setUpdater( dto.getUpdater() );
        businessUnit.setUpdateTimestamp( dto.getUpdateTimestamp() );

        return businessUnit;
    }

    @Override
    public BusinessUnitDTO toDto(BusinessUnit entity) {
        if ( entity == null ) {
            return null;
        }

        BusinessUnitDTO businessUnitDTO = new BusinessUnitDTO();

        businessUnitDTO.setId( entity.getId() );
        businessUnitDTO.setBusinessUnit( entity.getBusinessUnit() );
        businessUnitDTO.setBusinessAssociateNbr( entity.getBusinessAssociateNbr() );
        businessUnitDTO.setEdiPipeId( entity.getEdiPipeId() );
        byte[] companyLogo = entity.getCompanyLogo();
        if ( companyLogo != null ) {
            businessUnitDTO.setCompanyLogo( Arrays.copyOf( companyLogo, companyLogo.length ) );
        }
        businessUnitDTO.setCompanyLogoContentType( entity.getCompanyLogoContentType() );
        businessUnitDTO.setUpdater( entity.getUpdater() );
        businessUnitDTO.setUpdateTimestamp( entity.getUpdateTimestamp() );

        return businessUnitDTO;
    }

    @Override
    public List<BusinessUnit> toEntity(List<BusinessUnitDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BusinessUnit> list = new ArrayList<BusinessUnit>( dtoList.size() );
        for ( BusinessUnitDTO businessUnitDTO : dtoList ) {
            list.add( toEntity( businessUnitDTO ) );
        }

        return list;
    }

    @Override
    public List<BusinessUnitDTO> toDto(List<BusinessUnit> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BusinessUnitDTO> list = new ArrayList<BusinessUnitDTO>( entityList.size() );
        for ( BusinessUnit businessUnit : entityList ) {
            list.add( toDto( businessUnit ) );
        }

        return list;
    }
}
