package com.oilgascs.gps.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Activity entity.
 */
public class ActivityDTO implements Serializable {

    private Long id;

    @NotNull
    private String contrId;

    @NotNull
    private String activityNbr;

    private String transactionType;

    private String updater;

    private ZonedDateTime updateTimeStamp;

    @NotNull
    private String businessUnit;

    private Long upstreamBAId;

    private String upstreamBABaAbbr;

    private Long downstreamBAId;

    private String downstreamBABaAbbr;

    private Long rtSchedId;

    private String rtSchedRateScheduleCD;

    private Long receiptLocationId;

    private String receiptLocationLocationNbr;

    private Long deliveryLocationId;

    private String deliveryLocationLocationNbr;

    private Long upstreamContractId;

    private String upstreamContractContrId;

    private Long downstreamContractId;

    private String downstreamContractContrId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContrId() {
        return contrId;
    }

    public void setContrId(String contrId) {
        this.contrId = contrId;
    }

    public String getActivityNbr() {
        return activityNbr;
    }

    public void setActivityNbr(String activityNbr) {
        this.activityNbr = activityNbr;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Long getUpstreamBAId() {
        return upstreamBAId;
    }

    public void setUpstreamBAId(Long businessAssociateId) {
        this.upstreamBAId = businessAssociateId;
    }

    public String getUpstreamBABaAbbr() {
        return upstreamBABaAbbr;
    }

    public void setUpstreamBABaAbbr(String businessAssociateBaAbbr) {
        this.upstreamBABaAbbr = businessAssociateBaAbbr;
    }

    public Long getDownstreamBAId() {
        return downstreamBAId;
    }

    public void setDownstreamBAId(Long businessAssociateId) {
        this.downstreamBAId = businessAssociateId;
    }

    public String getDownstreamBABaAbbr() {
        return downstreamBABaAbbr;
    }

    public void setDownstreamBABaAbbr(String businessAssociateBaAbbr) {
        this.downstreamBABaAbbr = businessAssociateBaAbbr;
    }

    public Long getRtSchedId() {
        return rtSchedId;
    }

    public void setRtSchedId(Long rateSchedId) {
        this.rtSchedId = rateSchedId;
    }

    public String getRtSchedRateScheduleCD() {
        return rtSchedRateScheduleCD;
    }

    public void setRtSchedRateScheduleCD(String rateSchedRateScheduleCD) {
        this.rtSchedRateScheduleCD = rateSchedRateScheduleCD;
    }

    public Long getReceiptLocationId() {
        return receiptLocationId;
    }

    public void setReceiptLocationId(Long measurementStationId) {
        this.receiptLocationId = measurementStationId;
    }

    public String getReceiptLocationLocationNbr() {
        return receiptLocationLocationNbr;
    }

    public void setReceiptLocationLocationNbr(String measurementStationLocationNbr) {
        this.receiptLocationLocationNbr = measurementStationLocationNbr;
    }

    public Long getDeliveryLocationId() {
        return deliveryLocationId;
    }

    public void setDeliveryLocationId(Long measurementStationId) {
        this.deliveryLocationId = measurementStationId;
    }

    public String getDeliveryLocationLocationNbr() {
        return deliveryLocationLocationNbr;
    }

    public void setDeliveryLocationLocationNbr(String measurementStationLocationNbr) {
        this.deliveryLocationLocationNbr = measurementStationLocationNbr;
    }

    public Long getUpstreamContractId() {
        return upstreamContractId;
    }

    public void setUpstreamContractId(Long contractId) {
        this.upstreamContractId = contractId;
    }

    public String getUpstreamContractContrId() {
        return upstreamContractContrId;
    }

    public void setUpstreamContractContrId(String contractContrId) {
        this.upstreamContractContrId = contractContrId;
    }

    public Long getDownstreamContractId() {
        return downstreamContractId;
    }

    public void setDownstreamContractId(Long contractId) {
        this.downstreamContractId = contractId;
    }

    public String getDownstreamContractContrId() {
        return downstreamContractContrId;
    }

    public void setDownstreamContractContrId(String contractContrId) {
        this.downstreamContractContrId = contractContrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActivityDTO activityDTO = (ActivityDTO) o;
        if (activityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), activityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActivityDTO{" +
            "id=" + getId() +
            ", contrId='" + getContrId() + "'" +
            ", activityNbr='" + getActivityNbr() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", upstreamBA=" + getUpstreamBAId() +
            ", upstreamBA='" + getUpstreamBABaAbbr() + "'" +
            ", downstreamBA=" + getDownstreamBAId() +
            ", downstreamBA='" + getDownstreamBABaAbbr() + "'" +
            ", rtSched=" + getRtSchedId() +
            ", rtSched='" + getRtSchedRateScheduleCD() + "'" +
            ", receiptLocation=" + getReceiptLocationId() +
            ", receiptLocation='" + getReceiptLocationLocationNbr() + "'" +
            ", deliveryLocation=" + getDeliveryLocationId() +
            ", deliveryLocation='" + getDeliveryLocationLocationNbr() + "'" +
            ", upstreamContract=" + getUpstreamContractId() +
            ", upstreamContract='" + getUpstreamContractContrId() + "'" +
            ", downstreamContract=" + getDownstreamContractId() +
            ", downstreamContract='" + getDownstreamContractContrId() + "'" +
            "}";
    }
}
