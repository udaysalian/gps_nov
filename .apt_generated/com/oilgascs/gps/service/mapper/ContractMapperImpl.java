package com.oilgascs.gps.service.mapper;

import com.oilgascs.gps.domain.BusinessAssociate;
import com.oilgascs.gps.domain.Contract;
import com.oilgascs.gps.domain.RateSched;
import com.oilgascs.gps.service.dto.ContractDTO;
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
public class ContractMapperImpl implements ContractMapper {

    @Autowired
    private RateSchedMapper rateSchedMapper;
    @Autowired
    private BusinessAssociateMapper businessAssociateMapper;

    @Override
    public List<Contract> toEntity(List<ContractDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Contract> list = new ArrayList<Contract>( dtoList.size() );
        for ( ContractDTO contractDTO : dtoList ) {
            list.add( toEntity( contractDTO ) );
        }

        return list;
    }

    @Override
    public List<ContractDTO> toDto(List<Contract> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<ContractDTO> list = new ArrayList<ContractDTO>( entityList.size() );
        for ( Contract contract : entityList ) {
            list.add( toDto( contract ) );
        }

        return list;
    }

    @Override
    public ContractDTO toDto(Contract contract) {
        if ( contract == null ) {
            return null;
        }

        ContractDTO contractDTO = new ContractDTO();

        String baAbbr = contractBusAssocBaAbbr( contract );
        if ( baAbbr != null ) {
            contractDTO.setBusAssocBaAbbr( baAbbr );
        }
        String rateScheduleCD = contractRtSchedRateScheduleCD( contract );
        if ( rateScheduleCD != null ) {
            contractDTO.setRtSchedRateScheduleCD( rateScheduleCD );
        }
        Long id = contractBusAssocId( contract );
        if ( id != null ) {
            contractDTO.setBusAssocId( id );
        }
        Long id1 = contractRtSchedId( contract );
        if ( id1 != null ) {
            contractDTO.setRtSchedId( id1 );
        }
        contractDTO.setId( contract.getId() );
        contractDTO.setContrId( contract.getContrId() );
        contractDTO.setStatus( contract.getStatus() );
        contractDTO.setUpdater( contract.getUpdater() );
        contractDTO.setUpdateTimeStamp( contract.getUpdateTimeStamp() );
        contractDTO.setBusinessUnit( contract.getBusinessUnit() );

        return contractDTO;
    }

    @Override
    public Contract toEntity(ContractDTO contractDTO) {
        if ( contractDTO == null ) {
            return null;
        }

        Contract contract = new Contract();

        contract.setRtSched( rateSchedMapper.fromId( contractDTO.getRtSchedId() ) );
        contract.setBusAssoc( businessAssociateMapper.fromId( contractDTO.getBusAssocId() ) );
        contract.setId( contractDTO.getId() );
        contract.setContrId( contractDTO.getContrId() );
        contract.setStatus( contractDTO.getStatus() );
        contract.setUpdater( contractDTO.getUpdater() );
        contract.setUpdateTimeStamp( contractDTO.getUpdateTimeStamp() );
        contract.setBusinessUnit( contractDTO.getBusinessUnit() );

        return contract;
    }

    private String contractBusAssocBaAbbr(Contract contract) {
        if ( contract == null ) {
            return null;
        }
        BusinessAssociate busAssoc = contract.getBusAssoc();
        if ( busAssoc == null ) {
            return null;
        }
        String baAbbr = busAssoc.getBaAbbr();
        if ( baAbbr == null ) {
            return null;
        }
        return baAbbr;
    }

    private String contractRtSchedRateScheduleCD(Contract contract) {
        if ( contract == null ) {
            return null;
        }
        RateSched rtSched = contract.getRtSched();
        if ( rtSched == null ) {
            return null;
        }
        String rateScheduleCD = rtSched.getRateScheduleCD();
        if ( rateScheduleCD == null ) {
            return null;
        }
        return rateScheduleCD;
    }

    private Long contractBusAssocId(Contract contract) {
        if ( contract == null ) {
            return null;
        }
        BusinessAssociate busAssoc = contract.getBusAssoc();
        if ( busAssoc == null ) {
            return null;
        }
        Long id = busAssoc.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long contractRtSchedId(Contract contract) {
        if ( contract == null ) {
            return null;
        }
        RateSched rtSched = contract.getRtSched();
        if ( rtSched == null ) {
            return null;
        }
        Long id = rtSched.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
