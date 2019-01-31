package com.oilgascs.gps.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RateSchedVald entity.
 */
public class RateSchedValdDTO implements Serializable {

    private Long id;

    private String validType;

    private String updater;

    private ZonedDateTime updateTimeStamp;

    private String businessUnit;

    private Long rateSchdId;

    private String rateSchdRateScheduleCD;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValidType() {
        return validType;
    }

    public void setValidType(String validType) {
        this.validType = validType;
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

    public Long getRateSchdId() {
        return rateSchdId;
    }

    public void setRateSchdId(Long rateSchedId) {
        this.rateSchdId = rateSchedId;
    }

    public String getRateSchdRateScheduleCD() {
        return rateSchdRateScheduleCD;
    }

    public void setRateSchdRateScheduleCD(String rateSchedRateScheduleCD) {
        this.rateSchdRateScheduleCD = rateSchedRateScheduleCD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RateSchedValdDTO rateSchedValdDTO = (RateSchedValdDTO) o;
        if (rateSchedValdDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rateSchedValdDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RateSchedValdDTO{" +
            "id=" + getId() +
            ", validType='" + getValidType() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", rateSchd=" + getRateSchdId() +
            ", rateSchd='" + getRateSchdRateScheduleCD() + "'" +
            "}";
    }
}
