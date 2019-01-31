package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.ReductionReason;
import com.oilgascs.gps.service.dto.ReductionReasonDTO;
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
public class ReductionReasonMapperImpl implements ReductionReasonMapper {

    @Override
    public ReductionReason toEntity(ReductionReasonDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ReductionReason reductionReason = new ReductionReason();

        reductionReason.setId( dto.getId() );
        reductionReason.setReason( dto.getReason() );
        reductionReason.setProprieteryReasonCode( dto.getProprieteryReasonCode() );
        reductionReason.setBusinessUnit( dto.getBusinessUnit() );

        return reductionReason;
    }

    @Override
    public ReductionReasonDTO toDto(ReductionReason entity) {
        if ( entity == null ) {
            return null;
        }

        ReductionReasonDTO reductionReasonDTO = new ReductionReasonDTO();

        reductionReasonDTO.setId( entity.getId() );
        reductionReasonDTO.setReason( entity.getReason() );
        reductionReasonDTO.setProprieteryReasonCode( entity.getProprieteryReasonCode() );
        reductionReasonDTO.setBusinessUnit( entity.getBusinessUnit() );

        return reductionReasonDTO;
    }

    @Override
    public List<ReductionReason> toEntity(List<ReductionReasonDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ReductionReason> list = new ArrayList<ReductionReason>( dtoList.size() );
        for ( ReductionReasonDTO reductionReasonDTO : dtoList ) {
            list.add( toEntity( reductionReasonDTO ) );
        }

        return list;
    }

    @Override
    public List<ReductionReasonDTO> toDto(List<ReductionReason> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ReductionReasonDTO> list = new ArrayList<ReductionReasonDTO>( entityList.size() );
        for ( ReductionReason reductionReason : entityList ) {
            list.add( toDto( reductionReason ) );
        }

        return list;
    }
}
