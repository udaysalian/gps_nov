package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.domain.LocationBA;
import com.oilgascs.gps.service.dto.LocationBADTO;
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
public class LocationBAMapperImpl implements LocationBAMapper {

    @Autowired
    private BusinessAssociateMapper businessAssociateMapper;

    @Override
    public List<LocationBA> toEntity(List<LocationBADTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<LocationBA> list = new ArrayList<LocationBA>( dtoList.size() );
        for ( LocationBADTO locationBADTO : dtoList ) {
            list.add( toEntity( locationBADTO ) );
        }

        return list;
    }

    @Override
    public List<LocationBADTO> toDto(List<LocationBA> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<LocationBADTO> list = new ArrayList<LocationBADTO>( entityList.size() );
        for ( LocationBA locationBA : entityList ) {
            list.add( toDto( locationBA ) );
        }

        return list;
    }

    @Override
    public LocationBADTO toDto(LocationBA locationBA) {
        if ( locationBA == null ) {
            return null;
        }

        LocationBADTO locationBADTO = new LocationBADTO();

        String baAbbr = locationBABusAssocBaAbbr( locationBA );
        if ( baAbbr != null ) {
            locationBADTO.setBusAssocBaAbbr( baAbbr );
        }
        Long id = locationBABusAssocId( locationBA );
        if ( id != null ) {
            locationBADTO.setBusAssocId( id );
        }
        locationBADTO.setId( locationBA.getId() );
        locationBADTO.setLocationNbr( locationBA.getLocationNbr() );
        locationBADTO.setLocationBAType( locationBA.getLocationBAType() );
        locationBADTO.setBusinessUnit( locationBA.getBusinessUnit() );
        locationBADTO.setUpdater( locationBA.getUpdater() );
        locationBADTO.setUpdateTimestamp( locationBA.getUpdateTimestamp() );

        return locationBADTO;
    }

    @Override
    public LocationBA toEntity(LocationBADTO locationBADTO) {
        if ( locationBADTO == null ) {
            return null;
        }

        LocationBA locationBA = new LocationBA();

        locationBA.setBusAssoc( businessAssociateMapper.fromId( locationBADTO.getBusAssocId() ) );
        locationBA.setId( locationBADTO.getId() );
        locationBA.setLocationNbr( locationBADTO.getLocationNbr() );
        locationBA.setLocationBAType( locationBADTO.getLocationBAType() );
        locationBA.setBusinessUnit( locationBADTO.getBusinessUnit() );
        locationBA.setUpdater( locationBADTO.getUpdater() );
        locationBA.setUpdateTimestamp( locationBADTO.getUpdateTimestamp() );

        return locationBA;
    }

    private String locationBABusAssocBaAbbr(LocationBA locationBA) {
        if ( locationBA == null ) {
            return null;
        }
        BusinessAssociate busAssoc = locationBA.getBusAssoc();
        if ( busAssoc == null ) {
            return null;
        }
        String baAbbr = busAssoc.getBaAbbr();
        if ( baAbbr == null ) {
            return null;
        }
        return baAbbr;
    }

    private Long locationBABusAssocId(LocationBA locationBA) {
        if ( locationBA == null ) {
            return null;
        }
        BusinessAssociate busAssoc = locationBA.getBusAssoc();
        if ( busAssoc == null ) {
            return null;
        }
        Long id = busAssoc.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
