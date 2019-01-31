package com.oilgascs.gps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * A Contract.
 */
@Entity
@Table(name = "contract")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "contr_id", nullable = false)
    private String contrId;

    @Column(name = "status")
    private String status;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_time_stamp")
    private ZonedDateTime updateTimeStamp;

    @NotNull
    @Column(name = "business_unit", nullable = false)
    private String businessUnit;

    @OneToMany(mappedBy = "contract")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ContrLoc> contrLocs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("")
    private RateSched rtSched;

    @ManyToOne
    @JsonIgnoreProperties("contracts")
    private BusinessAssociate busAssoc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContrId() {
        return contrId;
    }

    public Contract contrId(String contrId) {
        this.contrId = contrId;
        return this;
    }

    public void setContrId(String contrId) {
        this.contrId = contrId;
    }

    public String getStatus() {
        return status;
    }

    public Contract status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public Contract updater(String updater) {
        this.updater = updater;
        return this;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public Contract updateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
        return this;
    }

    public void setUpdateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public Contract businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Set<ContrLoc> getContrLocs() {
        return contrLocs;
    }

    public Contract contrLocs(Set<ContrLoc> contrLocs) {
        this.contrLocs = contrLocs;
        return this;
    }

    public Contract addContrLoc(ContrLoc contrLoc) {
        this.contrLocs.add(contrLoc);
        contrLoc.setContract(this);
        return this;
    }

    public Contract removeContrLoc(ContrLoc contrLoc) {
        this.contrLocs.remove(contrLoc);
        contrLoc.setContract(null);
        return this;
    }

    public void setContrLocs(Set<ContrLoc> contrLocs) {
        this.contrLocs = contrLocs;
    }

    public RateSched getRtSched() {
        return rtSched;
    }

    public Contract rtSched(RateSched rateSched) {
        this.rtSched = rateSched;
        return this;
    }

    public void setRtSched(RateSched rateSched) {
        this.rtSched = rateSched;
    }

    public BusinessAssociate getBusAssoc() {
        return busAssoc;
    }

    public Contract busAssoc(BusinessAssociate businessAssociate) {
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
        Contract contract = (Contract) o;
        if (contract.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contract.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contract{" +
            "id=" + getId() +
            ", contrId='" + getContrId() + "'" +
            ", status='" + getStatus() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            "}";
    }
}
