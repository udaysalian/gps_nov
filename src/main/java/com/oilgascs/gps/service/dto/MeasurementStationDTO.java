package com.oilgascs.gps.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MeasurementStation entity.
 */
public class MeasurementStationDTO implements Serializable {

    private Long id;

    @NotNull
    private String locationNbr;

    private Double milepostNbr;

    private String upstreamPipeNode;

    private String downStreamPipeNode;

    @NotNull
    private String businessUnit;

    private String updater;

    private ZonedDateTime updateTimestamp;

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

    public Double getMilepostNbr() {
        return milepostNbr;
    }

    public void setMilepostNbr(Double milepostNbr) {
        this.milepostNbr = milepostNbr;
    }

    public String getUpstreamPipeNode() {
        return upstreamPipeNode;
    }

    public void setUpstreamPipeNode(String upstreamPipeNode) {
        this.upstreamPipeNode = upstreamPipeNode;
    }

    public String getDownStreamPipeNode() {
        return downStreamPipeNode;
    }

    public void setDownStreamPipeNode(String downStreamPipeNode) {
        this.downStreamPipeNode = downStreamPipeNode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MeasurementStationDTO measurementStationDTO = (MeasurementStationDTO) o;
        if (measurementStationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), measurementStationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MeasurementStationDTO{" +
            "id=" + getId() +
            ", locationNbr='" + getLocationNbr() + "'" +
            ", milepostNbr=" + getMilepostNbr() +
            ", upstreamPipeNode='" + getUpstreamPipeNode() + "'" +
            ", downStreamPipeNode='" + getDownStreamPipeNode() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimestamp='" + getUpdateTimestamp() + "'" +
            "}";
    }
}
