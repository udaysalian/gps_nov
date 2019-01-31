package com.oilgascs.gps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A LocationBA.
 */
@Entity
@Table(name = "loc_ba")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LocationBA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "location_nbr", nullable = false)
    private String locationNbr;

    @Column(name = "location_ba_type")
    private String locationBAType;

    @NotNull
    @Column(name = "business_unit", nullable = false)
    private String businessUnit;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_timestamp")
    private ZonedDateTime updateTimestamp;

    @OneToOne
    @JoinColumn(unique = true)
    private BusinessAssociate busAssoc;

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

    public LocationBA locationNbr(String locationNbr) {
        this.locationNbr = locationNbr;
        return this;
    }

    public void setLocationNbr(String locationNbr) {
        this.locationNbr = locationNbr;
    }

    public String getLocationBAType() {
        return locationBAType;
    }

    public LocationBA locationBAType(String locationBAType) {
        this.locationBAType = locationBAType;
        return this;
    }

    public void setLocationBAType(String locationBAType) {
        this.locationBAType = locationBAType;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public LocationBA businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getUpdater() {
        return updater;
    }

    public LocationBA updater(String updater) {
        this.updater = updater;
        return this;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    public LocationBA updateTimestamp(ZonedDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
        return this;
    }

    public void setUpdateTimestamp(ZonedDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public BusinessAssociate getBusAssoc() {
        return busAssoc;
    }

    public LocationBA busAssoc(BusinessAssociate businessAssociate) {
        this.busAssoc = businessAssociate;
        return this;
    }

    public void setBusAssoc(BusinessAssociate businessAssociate) {
        this.busAssoc = businessAssociate;
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
        LocationBA locationBA = (LocationBA) o;
        if (locationBA.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), locationBA.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LocationBA{" +
            "id=" + getId() +
            ", locationNbr='" + getLocationNbr() + "'" +
            ", locationBAType='" + getLocationBAType() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimestamp='" + getUpdateTimestamp() + "'" +
            "}";
    }
}
