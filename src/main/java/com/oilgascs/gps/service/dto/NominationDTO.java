package com.oilgascs.gps.service.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.oilgascs.gps.domain.enumeration.NomRequestStatus;

/**
 * A DTO for the Nomination entity.
 */
public class NominationDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate gasDate;

    @Min(value = 0)
    private Integer requestedRcptQty;

    @Min(value = 0)
    private Integer reqestedDlvryQty;

    @Min(value = 0)
    private Integer scheduledRcptQty;

    @Min(value = 0)
    private Integer scheduledDlvryQty;

    private NomRequestStatus requestStatus;

    private String updater;

    private ZonedDateTime updateTimeStamp;

    @NotNull
    private String businessUnit;

    private Long activityId;

    private String activityActivityNbr;

    private Long contractId;

    private String contractContrId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getGasDate() {
        return gasDate;
    }

    public void setGasDate(LocalDate gasDate) {
        this.gasDate = gasDate;
    }

    public Integer getRequestedRcptQty() {
        return requestedRcptQty;
    }

    public void setRequestedRcptQty(Integer requestedRcptQty) {
        this.requestedRcptQty = requestedRcptQty;
    }

    public Integer getReqestedDlvryQty() {
        return reqestedDlvryQty;
    }

    public void setReqestedDlvryQty(Integer reqestedDlvryQty) {
        this.reqestedDlvryQty = reqestedDlvryQty;
    }

    public Integer getScheduledRcptQty() {
        return scheduledRcptQty;
    }

    public void setScheduledRcptQty(Integer scheduledRcptQty) {
        this.scheduledRcptQty = scheduledRcptQty;
    }

    public Integer getScheduledDlvryQty() {
        return scheduledDlvryQty;
    }

    public void setScheduledDlvryQty(Integer scheduledDlvryQty) {
        this.scheduledDlvryQty = scheduledDlvryQty;
    }

    public NomRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(NomRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
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

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityActivityNbr() {
        return activityActivityNbr;
    }

    public void setActivityActivityNbr(String activityActivityNbr) {
        this.activityActivityNbr = activityActivityNbr;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getContractContrId() {
        return contractContrId;
    }

    public void setContractContrId(String contractContrId) {
        this.contractContrId = contractContrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NominationDTO nominationDTO = (NominationDTO) o;
        if (nominationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nominationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NominationDTO{" +
            "id=" + getId() +
            ", gasDate='" + getGasDate() + "'" +
            ", requestedRcptQty=" + getRequestedRcptQty() +
            ", reqestedDlvryQty=" + getReqestedDlvryQty() +
            ", scheduledRcptQty=" + getScheduledRcptQty() +
            ", scheduledDlvryQty=" + getScheduledDlvryQty() +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", activity=" + getActivityId() +
            ", activity='" + getActivityActivityNbr() + "'" +
            ", contract=" + getContractId() +
            ", contract='" + getContractContrId() + "'" +
            "}";
    }
}
