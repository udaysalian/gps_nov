package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.RateSched;
import com.oilgascs.gps.service.dto.RateSchedDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-01-30T04:27:50+0000",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.15.0.v20180905-0317, environment: Java 1.8.0_151 (Oracle Corporation)"
)
@Component
public class RateSchedMapperImpl implements RateSchedMapper {

    @Override
    public RateSchedDTO toDto(RateSched entity) {
        if ( entity == null ) {
            return null;
        }

        RateSchedDTO rateSchedDTO = new RateSchedDTO();

        rateSchedDTO.setId( entity.getId() );
        rateSchedDTO.setRsType( entity.getRsType() );
        rateSchedDTO.setRateScheduleCD( entity.getRateScheduleCD() );
        rateSchedDTO.setUpdater( entity.getUpdater() );
        rateSchedDTO.setUpdateTimeStamp( entity.getUpdateTimeStamp() );
        rateSchedDTO.setBusinessUnit( entity.getBusinessUnit() );

        return rateSchedDTO;
    }

    @Override
    public List<RateSched> toEntity(List<RateSchedDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<RateSched> list = new ArrayList<RateSched>( dtoList.size() );
        for ( RateSchedDTO rateSchedDTO : dtoList ) {
            list.add( toEntity( rateSchedDTO ) );
        }

        return list;
    }

    @Override
    public List<RateSchedDTO> toDto(List<RateSched> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RateSchedDTO> list = new ArrayList<RateSchedDTO>( entityList.size() );
        for ( RateSched rateSched : entityList ) {
            list.add( toDto( rateSched ) );
        }

        return list;
    }

    @Override
    public RateSched toEntity(RateSchedDTO rateSchedDTO) {
        if ( rateSchedDTO == null ) {
            return null;
        }

        RateSched rateSched = new RateSched();

        rateSched.setId( rateSchedDTO.getId() );
        rateSched.setRsType( rateSchedDTO.getRsType() );
        rateSched.setRateScheduleCD( rateSchedDTO.getRateScheduleCD() );
        rateSched.setUpdater( rateSchedDTO.getUpdater() );
        rateSched.setUpdateTimeStamp( rateSchedDTO.getUpdateTimeStamp() );
        rateSched.setBusinessUnit( rateSchedDTO.getBusinessUnit() );

        return rateSched;
    }
}
