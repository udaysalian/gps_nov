package com.oilgascs.gps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A ContrLoc.
 */
@Entity
@Table(name = "contr_loc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ContrLoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_type", nullable = false)
    private String type;

    @Min(value = 0)
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "effective_from_date")
    private LocalDate effectiveFromDate;

    @Column(name = "effective_to_date")
    private LocalDate effectiveToDate;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_time_stamp")
    private ZonedDateTime updateTimeStamp;

    @Column(name = "business_unit")
    private String businessUnit;

    @ManyToOne
    @JsonIgnoreProperties("contrLocs")
    private Contract contract;

    @OneToOne
    @JoinColumn(unique = true)
    private MeasurementStation location;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public ContrLoc type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ContrLoc quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getEffectiveFromDate() {
        return effectiveFromDate;
    }

    public ContrLoc effectiveFromDate(LocalDate effectiveFromDate) {
        this.effectiveFromDate = effectiveFromDate;
        return this;
    }

    public void setEffectiveFromDate(LocalDate effectiveFromDate) {
        this.effectiveFromDate = effectiveFromDate;
    }

    public LocalDate getEffectiveToDate() {
        return effectiveToDate;
    }

    public ContrLoc effectiveToDate(LocalDate effectiveToDate) {
        this.effectiveToDate = effectiveToDate;
        return this;
    }

    public void setEffectiveToDate(LocalDate effectiveToDate) {
        this.effectiveToDate = effectiveToDate;
    }

    public String getUpdater() {
        return updater;
    }

    public ContrLoc updater(String updater) {
        this.updater = updater;
        return this;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public ContrLoc updateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
        return this;
    }

    public void setUpdateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public ContrLoc businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Contract getContract() {
        return contract;
    }

    public ContrLoc contract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public MeasurementStation getLocation() {
        return location;
    }

    public ContrLoc location(MeasurementStation measurementStation) {
        this.location = measurementStation;
        return this;
    }

    public void setLocation(MeasurementStation measurementStation) {
        this.location = measurementStation;
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
        ContrLoc contrLoc = (ContrLoc) o;
        if (contrLoc.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contrLoc.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContrLoc{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", quantity=" + getQuantity() +
            ", effectiveFromDate='" + getEffectiveFromDate() + "'" +
            ", effectiveToDate='" + getEffectiveToDate() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            "}";
    }
}
