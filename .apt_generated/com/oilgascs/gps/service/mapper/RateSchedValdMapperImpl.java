package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.RateSched;
import com.oilgascs.gps.domain.RateSchedVald;
import com.oilgascs.gps.service.dto.RateSchedValdDTO;
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
public class RateSchedValdMapperImpl implements RateSchedValdMapper {

    @Autowired
    private RateSchedMapper rateSchedMapper;

    @Override
    public List<RateSchedVald> toEntity(List<RateSchedValdDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<RateSchedVald> list = new ArrayList<RateSchedVald>( dtoList.size() );
        for ( RateSchedValdDTO rateSchedValdDTO : dtoList ) {
            list.add( toEntity( rateSchedValdDTO ) );
        }

        return list;
    }

    @Override
    public List<RateSchedValdDTO> toDto(List<RateSchedVald> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RateSchedValdDTO> list = new ArrayList<RateSchedValdDTO>( entityList.size() );
        for ( RateSchedVald rateSchedVald : entityList ) {
            list.add( toDto( rateSchedVald ) );
        }

        return list;
    }

    @Override
    public RateSchedValdDTO toDto(RateSchedVald rateSchedVald) {
        if ( rateSchedVald == null ) {
            return null;
        }

        RateSchedValdDTO rateSchedValdDTO = new RateSchedValdDTO();

        String rateScheduleCD = rateSchedValdRateSchdRateScheduleCD( rateSchedVald );
        if ( rateScheduleCD != null ) {
            rateSchedValdDTO.setRateSchdRateScheduleCD( rateScheduleCD );
        }
        Long id = rateSchedValdRateSchdId( rateSchedVald );
        if ( id != null ) {
            rateSchedValdDTO.setRateSchdId( id );
        }
        rateSchedValdDTO.setId( rateSchedVald.getId() );
        rateSchedValdDTO.setValidType( rateSchedVald.getValidType() );
        rateSchedValdDTO.setUpdater( rateSchedVald.getUpdater() );
        rateSchedValdDTO.setUpdateTimeStamp( rateSchedVald.getUpdateTimeStamp() );
        rateSchedValdDTO.setBusinessUnit( rateSchedVald.getBusinessUnit() );

        return rateSchedValdDTO;
    }

    @Override
    public RateSchedVald toEntity(RateSchedValdDTO rateSchedValdDTO) {
        if ( rateSchedValdDTO == null ) {
            return null;
        }

        RateSchedVald rateSchedVald = new RateSchedVald();

        rateSchedVald.setRateSchd( rateSchedMapper.fromId( rateSchedValdDTO.getRateSchdId() ) );
        rateSchedVald.setId( rateSchedValdDTO.getId() );
        rateSchedVald.setValidType( rateSchedValdDTO.getValidType() );
        rateSchedVald.setUpdater( rateSchedValdDTO.getUpdater() );
        rateSchedVald.setUpdateTimeStamp( rateSchedValdDTO.getUpdateTimeStamp() );
        rateSchedVald.setBusinessUnit( rateSchedValdDTO.getBusinessUnit() );

        return rateSchedVald;
    }

    private String rateSchedValdRateSchdRateScheduleCD(RateSchedVald rateSchedVald) {
        if ( rateSchedVald == null ) {
            return null;
        }
        RateSched rateSchd = rateSchedVald.getRateSchd();
        if ( rateSchd == null ) {
            return null;
        }
        String rateScheduleCD = rateSchd.getRateScheduleCD();
        if ( rateScheduleCD == null ) {
            return null;
        }
        return rateScheduleCD;
    }

    private Long rateSchedValdRateSchdId(RateSchedVald rateSchedVald) {
        if ( rateSchedVald == null ) {
            return null;
        }
        RateSched rateSchd = rateSchedVald.getRateSchd();
        if ( rateSchd == null ) {
            return null;
        }
        Long id = rateSchd.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
