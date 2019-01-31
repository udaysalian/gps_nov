package com.oilgascs.gps.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Contract entity.
 */
public class ContractDTO implements Serializable {

    private Long id;

    @NotNull
    private String contrId;

    private String status;

    private String updater;

    private ZonedDateTime updateTimeStamp;

    @NotNull
    private String businessUnit;

    private Long rtSchedId;

    private String rtSchedRateScheduleCD;

    private Long busAssocId;

    private String busAssocBaAbbr;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Long getBusAssocId() {
        return busAssocId;
    }

    public void setBusAssocId(Long businessAssociateId) {
        this.busAssocId = businessAssociateId;
    }

    public String getBusAssocBaAbbr() {
        return busAssocBaAbbr;
    }

    public void setBusAssocBaAbbr(String businessAssociateBaAbbr) {
        this.busAssocBaAbbr = businessAssociateBaAbbr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContractDTO contractDTO = (ContractDTO) o;
        if (contractDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contractDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
            "id=" + getId() +
            ", contrId='" + getContrId() + "'" +
            ", status='" + getStatus() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", rtSched=" + getRtSchedId() +
            ", rtSched='" + getRtSchedRateScheduleCD() + "'" +
            ", busAssoc=" + getBusAssocId() +
            ", busAssoc='" + getBusAssocBaAbbr() + "'" +
            "}";
    }
}
