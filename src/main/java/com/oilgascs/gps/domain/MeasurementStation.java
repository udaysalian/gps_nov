package com.oilgascs.gps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A MeasurementStation.
 */
@Entity
@Table(name = "meas_station")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MeasurementStation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "location_nbr", nullable = false)
    private String locationNbr;

    @Column(name = "milepost_nbr")
    private Double milepostNbr;

    @Column(name = "upstream_pipe_node")
    private String upstreamPipeNode;

    @Column(name = "down_stream_pipe_node")
    private String downStreamPipeNode;

    @NotNull
    @Column(name = "business_unit", nullable = false)
    private String businessUnit;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_timestamp")
    private ZonedDateTime updateTimestamp;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationNbr() {
        return locationNbr;
    }

    public MeasurementStation locationNbr(String locationNbr) {
        this.locationNbr = locationNbr;
        return this;
    }

    public void setLocationNbr(String locationNbr) {
        this.locationNbr = locationNbr;
    }

    public Double getMilepostNbr() {
        return milepostNbr;
    }

    public MeasurementStation milepostNbr(Double milepostNbr) {
        this.milepostNbr = milepostNbr;
        return this;
    }

    public void setMilepostNbr(Double milepostNbr) {
        this.milepostNbr = milepostNbr;
    }

    public String getUpstreamPipeNode() {
        return upstreamPipeNode;
    }

    public MeasurementStation upstreamPipeNode(String upstreamPipeNode) {
        this.upstreamPipeNode = upstreamPipeNode;
        return this;
    }

    public void setUpstreamPipeNode(String upstreamPipeNode) {
        this.upstreamPipeNode = upstreamPipeNode;
    }

    public String getDownStreamPipeNode() {
        return downStreamPipeNode;
    }

    public MeasurementStation downStreamPipeNode(String downStreamPipeNode) {
        this.downStreamPipeNode = downStreamPipeNode;
        return this;
    }

    public void setDownStreamPipeNode(String downStreamPipeNode) {
        this.downStreamPipeNode = downStreamPipeNode;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public MeasurementStation businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getUpdater() {
        return updater;
    }

    public MeasurementStation updater(String updater) {
        this.updater = updater;
        return this;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    public MeasurementStation updateTimestamp(ZonedDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
        return this;
    }

    public void setUpdateTimestamp(ZonedDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
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
        MeasurementStation measurementStation = (MeasurementStation) o;
        if (measurementStation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), measurementStation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MeasurementStation{" +
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
