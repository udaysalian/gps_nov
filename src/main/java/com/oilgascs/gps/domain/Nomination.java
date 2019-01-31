package com.oilgascs.gps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.oilgascs.gps.domain.enumeration.NomRequestStatus;

/**
 * A Nomination.
 */
@Entity
@Table(name = "contr_nom")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Nomination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "gas_date", nullable = false)
    private LocalDate gasDate;

    @Min(value = 0)
    @Column(name = "requested_rcpt_qty")
    private Integer requestedRcptQty;

    @Min(value = 0)
    @Column(name = "reqested_dlvry_qty")
    private Integer reqestedDlvryQty;

    @Min(value = 0)
    @Column(name = "scheduled_rcpt_qty")
    private Integer scheduledRcptQty;

    @Min(value = 0)
    @Column(name = "scheduled_dlvry_qty")
    private Integer scheduledDlvryQty;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private NomRequestStatus requestStatus;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_time_stamp")
    private ZonedDateTime updateTimeStamp;

    @NotNull
    @Column(name = "business_unit", nullable = false)
    private String businessUnit;

    @OneToOne
    @JoinColumn(unique = true)
    private Activity activity;

    @OneToOne
    @JoinColumn(unique = true)
    private Contract contract;

    @OneToMany(mappedBy = "nomination")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NominationPriority> priorities = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getGasDate() {
        return gasDate;
    }

    public Nomination gasDate(LocalDate gasDate) {
        this.gasDate = gasDate;
        return this;
    }

    public void setGasDate(LocalDate gasDate) {
        this.gasDate = gasDate;
    }

    public Integer getRequestedRcptQty() {
        return requestedRcptQty;
    }

    public Nomination requestedRcptQty(Integer requestedRcptQty) {
        this.requestedRcptQty = requestedRcptQty;
        return this;
    }

    public void setRequestedRcptQty(Integer requestedRcptQty) {
        this.requestedRcptQty = requestedRcptQty;
    }

    public Integer getReqestedDlvryQty() {
        return reqestedDlvryQty;
    }

    public Nomination reqestedDlvryQty(Integer reqestedDlvryQty) {
        this.reqestedDlvryQty = reqestedDlvryQty;
        return this;
    }

    public void setReqestedDlvryQty(Integer reqestedDlvryQty) {
        this.reqestedDlvryQty = reqestedDlvryQty;
    }

    public Integer getScheduledRcptQty() {
        return scheduledRcptQty;
    }

    public Nomination scheduledRcptQty(Integer scheduledRcptQty) {
        this.scheduledRcptQty = scheduledRcptQty;
        return this;
    }

    public void setScheduledRcptQty(Integer scheduledRcptQty) {
        this.scheduledRcptQty = scheduledRcptQty;
    }

    public Integer getScheduledDlvryQty() {
        return scheduledDlvryQty;
    }

    public Nomination scheduledDlvryQty(Integer scheduledDlvryQty) {
        this.scheduledDlvryQty = scheduledDlvryQty;
        return this;
    }

    public void setScheduledDlvryQty(Integer scheduledDlvryQty) {
        this.scheduledDlvryQty = scheduledDlvryQty;
    }

    public NomRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public Nomination requestStatus(NomRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(NomRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getUpdater() {
        return updater;
    }

    public Nomination updater(String updater) {
        this.updater = updater;
        return this;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public Nomination updateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
        return this;
    }

    public void setUpdateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public Nomination businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Activity getActivity() {
        return activity;
    }

    public Nomination activity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Contract getContract() {
        return contract;
    }

    public Nomination contract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Set<NominationPriority> getPriorities() {
        return priorities;
    }

    public Nomination priorities(Set<NominationPriority> nominationPriorities) {
        this.priorities = nominationPriorities;
        return this;
    }

    public Nomination addPriorities(NominationPriority nominationPriority) {
        this.priorities.add(nominationPriority);
        nominationPriority.setNomination(this);
        return this;
    }

    public Nomination removePriorities(NominationPriority nominationPriority) {
        this.priorities.remove(nominationPriority);
        nominationPriority.setNomination(null);
        return this;
    }

    public void setPriorities(Set<NominationPriority> nominationPriorities) {
        this.priorities = nominationPriorities;
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
        Nomination nomination = (Nomination) o;
        if (nomination.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nomination.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Nomination{" +
            "id=" + getId() +
            ", gasDate='" + getGasDate() + "'" +
            ", requestedRcptQty=" + getRequestedRcptQty() +
            ", reqestedDlvryQty=" + getReqestedDlvryQty() +
            ", scheduledRcptQty=" + getScheduledRcptQty() +
            ", scheduledDlvryQty=" + getScheduledDlvryQty() +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            "}";
    }
}
