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
 * A NominationPriority.
 */
@Entity
@Table(name = "contr_nom_prty")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NominationPriority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "gas_date", nullable = false)
    private LocalDate gasDate;

    @NotNull
    @Column(name = "prty_tp", nullable = false)
    private String prtyTp;

    @Min(value = 0)
    @Column(name = "old_qty")
    private Integer oldQty;

    @Min(value = 0)
    @Column(name = "new_qty")
    private Integer newQty;

    @Column(name = "sub_type")
    private String subType;

    @Column(name = "dir_of_flow")
    private String dirOfFlow;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_time_stamp")
    private ZonedDateTime updateTimeStamp;

    @Column(name = "business_unit")
    private String businessUnit;

    @ManyToOne
    @JsonIgnoreProperties("priorities")
    private Nomination nomination;

    @OneToOne
    @JoinColumn(unique = true)
    private Activity activity;

    @OneToOne
    @JoinColumn(unique = true)
    private Contract contract;

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

    public NominationPriority gasDate(LocalDate gasDate) {
        this.gasDate = gasDate;
        return this;
    }

    public void setGasDate(LocalDate gasDate) {
        this.gasDate = gasDate;
    }

    public String getPrtyTp() {
        return prtyTp;
    }

    public NominationPriority prtyTp(String prtyTp) {
        this.prtyTp = prtyTp;
        return this;
    }

    public void setPrtyTp(String prtyTp) {
        this.prtyTp = prtyTp;
    }

    public Integer getOldQty() {
        return oldQty;
    }

    public NominationPriority oldQty(Integer oldQty) {
        this.oldQty = oldQty;
        return this;
    }

    public void setOldQty(Integer oldQty) {
        this.oldQty = oldQty;
    }

    public Integer getNewQty() {
        return newQty;
    }

    public NominationPriority newQty(Integer newQty) {
        this.newQty = newQty;
        return this;
    }

    public void setNewQty(Integer newQty) {
        this.newQty = newQty;
    }

    public String getSubType() {
        return subType;
    }

    public NominationPriority subType(String subType) {
        this.subType = subType;
        return this;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getDirOfFlow() {
        return dirOfFlow;
    }

    public NominationPriority dirOfFlow(String dirOfFlow) {
        this.dirOfFlow = dirOfFlow;
        return this;
    }

    public void setDirOfFlow(String dirOfFlow) {
        this.dirOfFlow = dirOfFlow;
    }

    public String getUpdater() {
        return updater;
    }

    public NominationPriority updater(String updater) {
        this.updater = updater;
        return this;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public NominationPriority updateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
        return this;
    }

    public void setUpdateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public NominationPriority businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Nomination getNomination() {
        return nomination;
    }

    public NominationPriority nomination(Nomination nomination) {
        this.nomination = nomination;
        return this;
    }

    public void setNomination(Nomination nomination) {
        this.nomination = nomination;
    }

    public Activity getActivity() {
        return activity;
    }

    public NominationPriority activity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Contract getContract() {
        return contract;
    }

    public NominationPriority contract(Contract contract) {
        this.contract = contract;
        return this;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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
        NominationPriority nominationPriority = (NominationPriority) o;
        if (nominationPriority.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nominationPriority.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NominationPriority{" +
            "id=" + getId() +
            ", gasDate='" + getGasDate() + "'" +
            ", prtyTp='" + getPrtyTp() + "'" +
            ", oldQty=" + getOldQty() +
            ", newQty=" + getNewQty() +
            ", subType='" + getSubType() + "'" +
            ", dirOfFlow='" + getDirOfFlow() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            "}";
    }
}
