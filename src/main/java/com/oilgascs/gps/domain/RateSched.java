package com.oilgascs.gps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A RateSched.
 */
@Entity
@Table(name = "rt_sched")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RateSched implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "rs_type", nullable = false)
    private String rsType="default";

    @NotNull
    @Column(name = "rate_schedule_cd", nullable = false)
    private String rateScheduleCD;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_time_stamp")
    private ZonedDateTime updateTimeStamp;

    @NotNull
    @Column(name = "business_unit", nullable = false)
    private String businessUnit;
   
    @OneToMany(mappedBy = "rateSchd")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RateSchedVald> rtSchedValds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRsType() {
        return rsType;
    }

    public RateSched rsType(String rsType) {
        this.rsType = rsType;
        return this;
    }

    public void setRsType(String rsType) {
        this.rsType = rsType;
    }

    public String getRateScheduleCD() {
        return rateScheduleCD;
    }

    public RateSched rateScheduleCD(String rateScheduleCD) {
        this.rateScheduleCD = rateScheduleCD;
        return this;
    }

    public void setRateScheduleCD(String rateScheduleCD) {
        this.rateScheduleCD = rateScheduleCD;
    }

    public String getUpdater() {
        return updater;
    }

    public RateSched updater(String updater) {
        this.updater = updater;
        return this;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public RateSched updateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
        return this;
    }
    
    public void setUpdateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public RateSched businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Set<RateSchedVald> getRtSchedValds() {
        return rtSchedValds;
    }

    public RateSched rtSchedValds(Set<RateSchedVald> rateSchedValds) {
        this.rtSchedValds = rateSchedValds;
        return this;
    }

    public RateSched addRtSchedVald(RateSchedVald rateSchedVald) {
        this.rtSchedValds.add(rateSchedVald);
        rateSchedVald.setRateSchd(this);
        return this;
    }

    public RateSched removeRtSchedVald(RateSchedVald rateSchedVald) {
        this.rtSchedValds.remove(rateSchedVald);
        rateSchedVald.setRateSchd(null);
        return this;
    }

    public void setRtSchedValds(Set<RateSchedVald> rateSchedValds) {
        this.rtSchedValds = rateSchedValds;
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
        RateSched rateSched = (RateSched) o;
        if (rateSched.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rateSched.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "{" +
            "id=" + getId() +
            ", rsType='" + getRsType() + "'" +
            ", rateScheduleCD='" + getRateScheduleCD() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            "}";
    }
}
