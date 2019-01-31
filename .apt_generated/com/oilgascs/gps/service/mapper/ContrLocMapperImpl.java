package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.ContrLoc;
import com.oilgascs.gps.domain.Contract;
import com.oilgascs.gps.domain.MeasurementStation;
import com.oilgascs.gps.service.dto.ContrLocDTO;
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
public class ContrLocMapperImpl implements ContrLocMapper {

    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private MeasurementStationMapper measurementStationMapper;

    @Override
    public List<ContrLoc> toEntity(List<ContrLocDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ContrLoc> list = new ArrayList<ContrLoc>( dtoList.size() );
        for ( ContrLocDTO contrLocDTO : dtoList ) {
            list.add( toEntity( contrLocDTO ) );
        }

        return list;
    }

    @Override
    public List<ContrLocDTO> toDto(List<ContrLoc> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ContrLocDTO> list = new ArrayList<ContrLocDTO>( entityList.size() );
        for ( ContrLoc contrLoc : entityList ) {
            list.add( toDto( contrLoc ) );
        }

        return list;
    }

    @Override
    public ContrLocDTO toDto(ContrLoc contrLoc) {
        if ( contrLoc == null ) {
            return null;
        }

        ContrLocDTO contrLocDTO = new ContrLocDTO();

        Long id = contrLocContractId( contrLoc );
        if ( id != null ) {
            contrLocDTO.setContractId( id );
        }
        String locationNbr = contrLocLocationLocationNbr( contrLoc );
        if ( locationNbr != null ) {
            contrLocDTO.setLocationLocationNbr( locationNbr );
        }
        Long id1 = contrLocLocationId( contrLoc );
        if ( id1 != null ) {
            contrLocDTO.setLocationId( id1 );
        }
        contrLocDTO.setId( contrLoc.getId() );
        contrLocDTO.setType( contrLoc.getType() );
        contrLocDTO.setQuantity( contrLoc.getQuantity() );
        contrLocDTO.setEffectiveFromDate( contrLoc.getEffectiveFromDate() );
        contrLocDTO.setEffectiveToDate( contrLoc.getEffectiveToDate() );
        contrLocDTO.setUpdater( contrLoc.getUpdater() );
        contrLocDTO.setUpdateTimeStamp( contrLoc.getUpdateTimeStamp() );
        contrLocDTO.setBusinessUnit( contrLoc.getBusinessUnit() );

        return contrLocDTO;
    }

    @Override
    public ContrLoc toEntity(ContrLocDTO contrLocDTO) {
        if ( contrLocDTO == null ) {
            return null;
        }

        ContrLoc contrLoc = new ContrLoc();

        contrLoc.setLocation( measurementStationMapper.fromId( contrLocDTO.getLocationId() ) );
        contrLoc.setContract( contractMapper.fromId( contrLocDTO.getContractId() ) );
        contrLoc.setId( contrLocDTO.getId() );
        contrLoc.setType( contrLocDTO.getType() );
        contrLoc.setQuantity( contrLocDTO.getQuantity() );
        contrLoc.setEffectiveFromDate( contrLocDTO.getEffectiveFromDate() );
        contrLoc.setEffectiveToDate( contrLocDTO.getEffectiveToDate() );
        contrLoc.setUpdater( contrLocDTO.getUpdater() );
        contrLoc.setUpdateTimeStamp( contrLocDTO.getUpdateTimeStamp() );
        contrLoc.setBusinessUnit( contrLocDTO.getBusinessUnit() );

        return contrLoc;
    }

    private Long contrLocContractId(ContrLoc contrLoc) {
        if ( contrLoc == null ) {
            return null;
        }
        Contract contract = contrLoc.getContract();
        if ( contract == null ) {
            return null;
        }
        Long id = contract.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String contrLocLocationLocationNbr(ContrLoc contrLoc) {
        if ( contrLoc == null ) {
            return null;
        }
        MeasurementStation location = contrLoc.getLocation();
        if ( location == null ) {
            return null;
        }
        String locationNbr = location.getLocationNbr();
        if ( locationNbr == null ) {
            return null;
        }
        return locationNbr;
    }

    private Long contrLocLocationId(ContrLoc contrLoc) {
        if ( contrLoc == null ) {
            return null;
        }
        MeasurementStation location = contrLoc.getLocation();
        if ( location == null ) {
            return null;
        }
        Long id = location.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
