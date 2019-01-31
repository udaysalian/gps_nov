package com.oilgascs.gps.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ReductionReason entity.
 */
public class ReductionReasonDTO implements Serializable {

    private Long id;

    private String reason;

    private String proprieteryReasonCode;

    @NotNull
    private String businessUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProprieteryReasonCode() {
        return proprieteryReasonCode;
    }

    public void setProprieteryReasonCode(String proprieteryReasonCode) {
        this.proprieteryReasonCode = proprieteryReasonCode;
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

        ReductionReasonDTO reductionReasonDTO = (ReductionReasonDTO) o;
        if (reductionReasonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reductionReasonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReductionReasonDTO{" +
            "id=" + getId() +
            ", reason='" + getReason() + "'" +
            ", proprieteryReasonCode='" + getProprieteryReasonCode() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            "}";
    }
}
