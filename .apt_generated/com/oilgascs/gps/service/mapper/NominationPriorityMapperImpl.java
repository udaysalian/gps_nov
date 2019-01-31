package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.Activity;
import com.oilgascs.gps.domain.Contract;
import com.oilgascs.gps.domain.Nomination;
import com.oilgascs.gps.domain.NominationPriority;
import com.oilgascs.gps.service.dto.NominationPriorityDTO;
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
public class NominationPriorityMapperImpl implements NominationPriorityMapper {

    @Autowired
    private NominationMapper nominationMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private ContractMapper contractMapper;

    @Override
    public List<NominationPriority> toEntity(List<NominationPriorityDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<NominationPriority> list = new ArrayList<NominationPriority>( dtoList.size() );
        for ( NominationPriorityDTO nominationPriorityDTO : dtoList ) {
            list.add( toEntity( nominationPriorityDTO ) );
        }

        return list;
    }

    @Override
    public List<NominationPriorityDTO> toDto(List<NominationPriority> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<NominationPriorityDTO> list = new ArrayList<NominationPriorityDTO>( entityList.size() );
        for ( NominationPriority nominationPriority : entityList ) {
            list.add( toDto( nominationPriority ) );
        }

        return list;
    }

    @Override
    public NominationPriorityDTO toDto(NominationPriority nominationPriority) {
        if ( nominationPriority == null ) {
            return null;
        }

        NominationPriorityDTO nominationPriorityDTO = new NominationPriorityDTO();

        Long id = nominationPriorityContractId( nominationPriority );
        if ( id != null ) {
            nominationPriorityDTO.setContractId( id );
        }
        Long id1 = nominationPriorityActivityId( nominationPriority );
        if ( id1 != null ) {
            nominationPriorityDTO.setActivityId( id1 );
        }
        String activityNbr = nominationPriorityActivityActivityNbr( nominationPriority );
        if ( activityNbr != null ) {
            nominationPriorityDTO.setActivityActivityNbr( activityNbr );
        }
        String contrId = nominationPriorityContractContrId( nominationPriority );
        if ( contrId != null ) {
            nominationPriorityDTO.setContractContrId( contrId );
        }
        Long id2 = nominationPriorityNominationId( nominationPriority );
        if ( id2 != null ) {
            nominationPriorityDTO.setNominationId( id2 );
        }
        nominationPriorityDTO.setId( nominationPriority.getId() );
        nominationPriorityDTO.setGasDate( nominationPriority.getGasDate() );
        nominationPriorityDTO.setPrtyTp( nominationPriority.getPrtyTp() );
        nominationPriorityDTO.setOldQty( nominationPriority.getOldQty() );
        nominationPriorityDTO.setNewQty( nominationPriority.getNewQty() );
        nominationPriorityDTO.setSubType( nominationPriority.getSubType() );
        nominationPriorityDTO.setDirOfFlow( nominationPriority.getDirOfFlow() );
        nominationPriorityDTO.setUpdater( nominationPriority.getUpdater() );
        nominationPriorityDTO.setUpdateTimeStamp( nominationPriority.getUpdateTimeStamp() );
        nominationPriorityDTO.setBusinessUnit( nominationPriority.getBusinessUnit() );

        return nominationPriorityDTO;
    }

    @Override
    public NominationPriority toEntity(NominationPriorityDTO nominationPriorityDTO) {
        if ( nominationPriorityDTO == null ) {
            return null;
        }

        NominationPriority nominationPriority = new NominationPriority();

        nominationPriority.setActivity( activityMapper.fromId( nominationPriorityDTO.getActivityId() ) );
        nominationPriority.setContract( contractMapper.fromId( nominationPriorityDTO.getContractId() ) );
        nominationPriority.setNomination( nominationMapper.fromId( nominationPriorityDTO.getNominationId() ) );
        nominationPriority.setId( nominationPriorityDTO.getId() );
        nominationPriority.setGasDate( nominationPriorityDTO.getGasDate() );
        nominationPriority.setPrtyTp( nominationPriorityDTO.getPrtyTp() );
        nominationPriority.setOldQty( nominationPriorityDTO.getOldQty() );
        nominationPriority.setNewQty( nominationPriorityDTO.getNewQty() );
        nominationPriority.setSubType( nominationPriorityDTO.getSubType() );
        nominationPriority.setDirOfFlow( nominationPriorityDTO.getDirOfFlow() );
        nominationPriority.setUpdater( nominationPriorityDTO.getUpdater() );
        nominationPriority.setUpdateTimeStamp( nominationPriorityDTO.getUpdateTimeStamp() );
        nominationPriority.setBusinessUnit( nominationPriorityDTO.getBusinessUnit() );

        return nominationPriority;
    }

    private Long nominationPriorityContractId(NominationPriority nominationPriority) {
        if ( nominationPriority == null ) {
            return null;
        }
        Contract contract = nominationPriority.getContract();
        if ( contract == null ) {
            return null;
        }
        Long id = contract.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long nominationPriorityActivityId(NominationPriority nominationPriority) {
        if ( nominationPriority == null ) {
            return null;
        }
        Activity activity = nominationPriority.getActivity();
        if ( activity == null ) {
            return null;
        }
        Long id = activity.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String nominationPriorityActivityActivityNbr(NominationPriority nominationPriority) {
        if ( nominationPriority == null ) {
            return null;
        }
        Activity activity = nominationPriority.getActivity();
        if ( activity == null ) {
            return null;
        }
        String activityNbr = activity.getActivityNbr();
        if ( activityNbr == null ) {
            return null;
        }
        return activityNbr;
    }

    private String nominationPriorityContractContrId(NominationPriority nominationPriority) {
        if ( nominationPriority == null ) {
            return null;
        }
        Contract contract = nominationPriority.getContract();
        if ( contract == null ) {
            return null;
        }
        String contrId = contract.getContrId();
        if ( contrId == null ) {
            return null;
        }
        return contrId;
    }

    private Long nominationPriorityNominationId(NominationPriority nominationPriority) {
        if ( nominationPriority == null ) {
            return null;
        }
        Nomination nomination = nominationPriority.getNomination();
        if ( nomination == null ) {
            return null;
        }
        Long id = nomination.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
