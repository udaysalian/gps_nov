package com.oilgascs.gps.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LocationBA entity.
 */
public class LocationBADTO implements Serializable {

    private Long id;

    @NotNull
    private String locationNbr;

    private String locationBAType;

    @NotNull
    private String businessUnit;

    private String updater;

    private ZonedDateTime updateTimestamp;

    private Long busAssocId;

    private String busAssocBaAbbr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationNbr() {
        return locationNbr;
    }

    public void setLocationNbr(String locationNbr) {
        this.locationNbr = locationNbr;
    }

    public String getLocationBAType() {
        return locationBAType;
    }

    public void setLocationBAType(String locationBAType) {
        this.locationBAType = locationBAType;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(ZonedDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
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

        LocationBADTO locationBADTO = (LocationBADTO) o;
        if (locationBADTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), locationBADTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LocationBADTO{" +
            "id=" + getId() +
            ", locationNbr='" + getLocationNbr() + "'" +
            ", locationBAType='" + getLocationBAType() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimestamp='" + getUpdateTimestamp() + "'" +
            ", busAssoc=" + getBusAssocId() +
            ", busAssoc='" + getBusAssocBaAbbr() + "'" +
            "}";
    }
}
