package com.oilgascs.gps.service.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ContrLoc entity.
 */
public class ContrLocDTO implements Serializable {

    private Long id;

    @NotNull
    private String type;

    @Min(value = 0)
    private Integer quantity;

    private LocalDate effectiveFromDate;

    private LocalDate effectiveToDate;

    private String updater;

    private ZonedDateTime updateTimeStamp;

    private String businessUnit;

    private Long contractId;

    private Long locationId;

    private String locationLocationNbr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getEffectiveFromDate() {
        return effectiveFromDate;
    }

    public void setEffectiveFromDate(LocalDate effectiveFromDate) {
        this.effectiveFromDate = effectiveFromDate;
    }

    public LocalDate getEffectiveToDate() {
        return effectiveToDate;
    }

    public void setEffectiveToDate(LocalDate effectiveToDate) {
        this.effectiveToDate = effectiveToDate;
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

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long measurementStationId) {
        this.locationId = measurementStationId;
    }

    public String getLocationLocationNbr() {
        return locationLocationNbr;
    }

    public void setLocationLocationNbr(String measurementStationLocationNbr) {
        this.locationLocationNbr = measurementStationLocationNbr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContrLocDTO contrLocDTO = (ContrLocDTO) o;
        if (contrLocDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contrLocDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContrLocDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", quantity=" + getQuantity() +
            ", effectiveFromDate='" + getEffectiveFromDate() + "'" +
            ", effectiveToDate='" + getEffectiveToDate() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", contract=" + getContractId() +
            ", location=" + getLocationId() +
            ", location='" + getLocationLocationNbr() + "'" +
            "}";
    }
}
