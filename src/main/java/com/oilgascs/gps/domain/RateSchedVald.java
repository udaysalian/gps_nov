package com.oilgascs.gps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A RateSchedVald.
 */
@Entity
@Table(name = "rt_sched_vald")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RateSchedVald implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valid_type")
    private String validType;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_time_stamp")
    private ZonedDateTime updateTimeStamp;

    @Column(name = "business_unit")
    private String businessUnit;

    @ManyToOne
    @JsonIgnoreProperties("rtSchedValds")
    private RateSched rateSchd;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValidType() {
        return validType;
    }

    public RateSchedVald validType(String validType) {
        this.validType = validType;
        return this;
    }

    public void setValidType(String validType) {
        this.validType = validType;
    }

    public String getUpdater() {
        return updater;
    }

    public RateSchedVald updater(String updater) {
        this.updater = updater;
        return this;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public RateSchedVald updateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
        return this;
    }

    public void setUpdateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public RateSchedVald businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public RateSched getRateSchd() {
        return rateSchd;
    }

    public RateSchedVald rateSchd(RateSched rateSched) {
        this.rateSchd = rateSched;
        return this;
    }

    public void setRateSchd(RateSched rateSched) {
        this.rateSchd = rateSched;
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
        RateSchedVald rateSchedVald = (RateSchedVald) o;
        if (rateSchedVald.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rateSchedVald.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RateSchedVald{" +
            "id=" + getId() +
            ", validType='" + getValidType() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            "}";
    }
}
