package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.MeasurementStation;
import com.oilgascs.gps.service.dto.MeasurementStationDTO;
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
public class MeasurementStationMapperImpl implements MeasurementStationMapper {

    @Override
    public MeasurementStation toEntity(MeasurementStationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MeasurementStation measurementStation = new MeasurementStation();

        measurementStation.setId( dto.getId() );
        measurementStation.setLocationNbr( dto.getLocationNbr() );
        measurementStation.setMilepostNbr( dto.getMilepostNbr() );
        measurementStation.setUpstreamPipeNode( dto.getUpstreamPipeNode() );
        measurementStation.setDownStreamPipeNode( dto.getDownStreamPipeNode() );
        measurementStation.setBusinessUnit( dto.getBusinessUnit() );
        measurementStation.setUpdater( dto.getUpdater() );
        measurementStation.setUpdateTimestamp( dto.getUpdateTimestamp() );

        return measurementStation;
    }

    @Override
    public MeasurementStationDTO toDto(MeasurementStation entity) {
        if ( entity == null ) {
            return null;
        }

        MeasurementStationDTO measurementStationDTO = new MeasurementStationDTO();

        measurementStationDTO.setId( entity.getId() );
        measurementStationDTO.setLocationNbr( entity.getLocationNbr() );
        measurementStationDTO.setMilepostNbr( entity.getMilepostNbr() );
        measurementStationDTO.setUpstreamPipeNode( entity.getUpstreamPipeNode() );
        measurementStationDTO.setDownStreamPipeNode( entity.getDownStreamPipeNode() );
        measurementStationDTO.setBusinessUnit( entity.getBusinessUnit() );
        measurementStationDTO.setUpdater( entity.getUpdater() );
        measurementStationDTO.setUpdateTimestamp( entity.getUpdateTimestamp() );

        return measurementStationDTO;
    }

    @Override
    public List<MeasurementStation> toEntity(List<MeasurementStationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MeasurementStation> list = new ArrayList<MeasurementStation>( dtoList.size() );
        for ( MeasurementStationDTO measurementStationDTO : dtoList ) {
            list.add( toEntity( measurementStationDTO ) );
        }

        return list;
    }

    @Override
    public List<MeasurementStationDTO> toDto(List<MeasurementStation> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MeasurementStationDTO> list = new ArrayList<MeasurementStationDTO>( entityList.size() );
        for ( MeasurementStation measurementStation : entityList ) {
            list.add( toDto( measurementStation ) );
        }

        return list;
    }
}
