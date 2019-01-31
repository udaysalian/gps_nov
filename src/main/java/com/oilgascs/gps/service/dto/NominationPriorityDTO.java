package com.oilgascs.gps.service.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NominationPriority entity.
 */
public class NominationPriorityDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate gasDate;

    @NotNull
    private String prtyTp;

    @Min(value = 0)
    private Integer oldQty;

    @Min(value = 0)
    private Integer newQty;

    private String subType;

    private String dirOfFlow;

    private String updater;

    private ZonedDateTime updateTimeStamp;

    private String businessUnit;

    private Long nominationId;

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

    public String getPrtyTp() {
        return prtyTp;
    }

    public void setPrtyTp(String prtyTp) {
        this.prtyTp = prtyTp;
    }

    public Integer getOldQty() {
        return oldQty;
    }

    public void setOldQty(Integer oldQty) {
        this.oldQty = oldQty;
    }

    public Integer getNewQty() {
        return newQty;
    }

    public void setNewQty(Integer newQty) {
        this.newQty = newQty;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getDirOfFlow() {
        return dirOfFlow;
    }

    public void setDirOfFlow(String dirOfFlow) {
        this.dirOfFlow = dirOfFlow;
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

    public Long getNominationId() {
        return nominationId;
    }

    public void setNominationId(Long nominationId) {
        this.nominationId = nominationId;
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

        NominationPriorityDTO nominationPriorityDTO = (NominationPriorityDTO) o;
        if (nominationPriorityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nominationPriorityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NominationPriorityDTO{" +
            "id=" + getId() +
            ", gasDate='" + getGasDate() + "'" +
            ", prtyTp='" + getPrtyTp() + "'" +
            ", oldQty=" + getOldQty() +
            ", newQty=" + getNewQty() +
            ", subType='" + getSubType() + "'" +
            ", dirOfFlow='" + getDirOfFlow() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", nomination=" + getNominationId() +
            ", activity=" + getActivityId() +
            ", activity='" + getActivityActivityNbr() + "'" +
            ", contract=" + getContractId() +
            ", contract='" + getContractContrId() + "'" +
            "}";
    }
}
