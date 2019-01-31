package com.oilgascs.gps.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RateSched entity.
 */
public class RateSchedDTO implements Serializable {

    private Long id;

    @NotNull
    private String rsType;

    @NotNull
    private String rateScheduleCD;

    private String updater;

    private ZonedDateTime updateTimeStamp;

    @NotNull
    private String businessUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRsType() {
        return rsType;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
    }

    public String getRateScheduleCD() {
        return rateScheduleCD;
    }

    public void setRateScheduleCD(String rateScheduleCD) {
        this.rateScheduleCD = rateScheduleCD;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RateSchedDTO rateSchedDTO = (RateSchedDTO) o;
        if (rateSchedDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rateSchedDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RateSchedDTO{" +
            "id=" + getId() +
            ", rsType='" + getRsType() + "'" +
            ", rateScheduleCD='" + getRateScheduleCD() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            "}";
    }
}
