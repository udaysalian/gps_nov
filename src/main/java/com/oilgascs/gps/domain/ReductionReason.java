package com.oilgascs.gps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ReductionReason.
 */
@Entity
@Table(name = "reduction_reason")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReductionReason implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reason")
    private String reason;

    @Column(name = "proprietery_reason_code")
    private String proprieteryReasonCode;

    @NotNull
    @Column(name = "business_unit", nullable = false)
    private String businessUnit;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public ReductionReason reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProprieteryReasonCode() {
        return proprieteryReasonCode;
    }

    public ReductionReason proprieteryReasonCode(String proprieteryReasonCode) {
        this.proprieteryReasonCode = proprieteryReasonCode;
        return this;
    }

    public void setProprieteryReasonCode(String proprieteryReasonCode) {
        this.proprieteryReasonCode = proprieteryReasonCode;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public ReductionReason businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReductionReason reductionReason = (ReductionReason) o;
        if (reductionReason.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reductionReason.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReductionReason{" +
            "id=" + getId() +
            ", reason='" + getReason() + "'" +
            ", proprieteryReasonCode='" + getProprieteryReasonCode() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            "}";
    }
}
