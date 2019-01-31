package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.Activity;
import com.oilgascs.gps.domain.Contract;
import com.oilgascs.gps.domain.Nomination;
import com.oilgascs.gps.service.dto.NominationDTO;
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
public class NominationMapperImpl implements NominationMapper {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ContractMapper contractMapper;

    @Override
    public List<Nomination> toEntity(List<NominationDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Nomination> list = new ArrayList<Nomination>( dtoList.size() );
        for ( NominationDTO nominationDTO : dtoList ) {
            list.add( toEntity( nominationDTO ) );
        }

        return list;
    }

    @Override
    public List<NominationDTO> toDto(List<Nomination> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NominationDTO> list = new ArrayList<NominationDTO>( entityList.size() );
        for ( Nomination nomination : entityList ) {
            list.add( toDto( nomination ) );
        }

        return list;
    }

    @Override
    public NominationDTO toDto(Nomination nomination) {
        if ( nomination == null ) {
            return null;
        }

        NominationDTO nominationDTO = new NominationDTO();

        Long id = nominationContractId( nomination );
        if ( id != null ) {
            nominationDTO.setContractId( id );
        }
        Long id1 = nominationActivityId( nomination );
        if ( id1 != null ) {
            nominationDTO.setActivityId( id1 );
        }
        String activityNbr = nominationActivityActivityNbr( nomination );
        if ( activityNbr != null ) {
            nominationDTO.setActivityActivityNbr( activityNbr );
        }
        String contrId = nominationContractContrId( nomination );
        if ( contrId != null ) {
            nominationDTO.setContractContrId( contrId );
        }
        nominationDTO.setId( nomination.getId() );
        nominationDTO.setGasDate( nomination.getGasDate() );
        nominationDTO.setRequestedRcptQty( nomination.getRequestedRcptQty() );
        nominationDTO.setReqestedDlvryQty( nomination.getReqestedDlvryQty() );
        nominationDTO.setScheduledRcptQty( nomination.getScheduledRcptQty() );
        nominationDTO.setScheduledDlvryQty( nomination.getScheduledDlvryQty() );
        nominationDTO.setRequestStatus( nomination.getRequestStatus() );
        nominationDTO.setUpdater( nomination.getUpdater() );
        nominationDTO.setUpdateTimeStamp( nomination.getUpdateTimeStamp() );
        nominationDTO.setBusinessUnit( nomination.getBusinessUnit() );

        return nominationDTO;
    }

    @Override
    public Nomination toEntity(NominationDTO nominationDTO) {
        if ( nominationDTO == null ) {
            return null;
        }

        Nomination nomination = new Nomination();

        nomination.setActivity( activityMapper.fromId( nominationDTO.getActivityId() ) );
        nomination.setContract( contractMapper.fromId( nominationDTO.getContractId() ) );
        nomination.setId( nominationDTO.getId() );
        nomination.setGasDate( nominationDTO.getGasDate() );
        nomination.setRequestedRcptQty( nominationDTO.getRequestedRcptQty() );
        nomination.setReqestedDlvryQty( nominationDTO.getReqestedDlvryQty() );
        nomination.setScheduledRcptQty( nominationDTO.getScheduledRcptQty() );
        nomination.setScheduledDlvryQty( nominationDTO.getScheduledDlvryQty() );
        nomination.setRequestStatus( nominationDTO.getRequestStatus() );
        nomination.setUpdater( nominationDTO.getUpdater() );
        nomination.setUpdateTimeStamp( nominationDTO.getUpdateTimeStamp() );
        nomination.setBusinessUnit( nominationDTO.getBusinessUnit() );

        return nomination;
    }

    private Long nominationContractId(Nomination nomination) {
        if ( nomination == null ) {
            return null;
        }
        Contract contract = nomination.getContract();
        if ( contract == null ) {
            return null;
        }
        Long id = contract.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long nominationActivityId(Nomination nomination) {
        if ( nomination == null ) {
            return null;
        }
        Activity activity = nomination.getActivity();
        if ( activity == null ) {
            return null;
        }
        Long id = activity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String nominationActivityActivityNbr(Nomination nomination) {
        if ( nomination == null ) {
            return null;
        }
        Activity activity = nomination.getActivity();
        if ( activity == null ) {
            return null;
        }
        String activityNbr = activity.getActivityNbr();
        if ( activityNbr == null ) {
            return null;
        }
        return activityNbr;
    }

    private String nominationContractContrId(Nomination nomination) {
        if ( nomination == null ) {
            return null;
        }
        Contract contract = nomination.getContract();
        if ( contract == null ) {
            return null;
        }
        String contrId = contract.getContrId();
        if ( contrId == null ) {
            return null;
        }
        return contrId;
    }
}
