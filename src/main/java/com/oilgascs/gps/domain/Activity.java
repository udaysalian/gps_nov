package com.oilgascs.gps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Activity.
 */
@Entity
@Table(name = "activity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "contr_id", nullable = false)
    private String contrId;

    @NotNull
    @Column(name = "activity_nbr", nullable = false)
    private String activityNbr;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "updater")
    private String updater;

    @Column(name = "update_time_stamp")
    private ZonedDateTime updateTimeStamp;

    @NotNull
    @Column(name = "business_unit", nullable = false)
    private String businessUnit;

    @OneToOne
    @JoinColumn(unique = true)
    private BusinessAssociate upstreamBA;

    @OneToOne
    @JoinColumn(unique = true)
    private BusinessAssociate downstreamBA;

    @ManyToOne
    @JsonIgnoreProperties("")
    private RateSched rtSched;

    @ManyToOne
    @JsonIgnoreProperties("")
    private MeasurementStation receiptLocation;

    @ManyToOne
    @JsonIgnoreProperties("")
    private MeasurementStation deliveryLocation;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Contract upstreamContract;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Contract downstreamContract;

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

    public Activity contrId(String contrId) {
        this.contrId = contrId;
        return this;
    }

    public void setContrId(String contrId) {
        this.contrId = contrId;
    }

    public String getActivityNbr() {
        return activityNbr;
    }

    public Activity activityNbr(String activityNbr) {
        this.activityNbr = activityNbr;
        return this;
    }

    public void setActivityNbr(String activityNbr) {
        this.activityNbr = activityNbr;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Activity transactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getUpdater() {
        return updater;
    }

    public Activity updater(String updater) {
        this.updater = updater;
        return this;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public Activity updateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
        return this;
    }

    public void setUpdateTimeStamp(ZonedDateTime updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public Activity businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public BusinessAssociate getUpstreamBA() {
        return upstreamBA;
    }

    public Activity upstreamBA(BusinessAssociate businessAssociate) {
        this.upstreamBA = businessAssociate;
        return this;
    }

    public void setUpstreamBA(BusinessAssociate businessAssociate) {
        this.upstreamBA = businessAssociate;
    }

    public BusinessAssociate getDownstreamBA() {
        return downstreamBA;
    }

    public Activity downstreamBA(BusinessAssociate businessAssociate) {
        this.downstreamBA = businessAssociate;
        return this;
    }

    public void setDownstreamBA(BusinessAssociate businessAssociate) {
        this.downstreamBA = businessAssociate;
    }

    public RateSched getRtSched() {
        return rtSched;
    }

    public Activity rtSched(RateSched rateSched) {
        this.rtSched = rateSched;
        return this;
    }

    public void setRtSched(RateSched rateSched) {
        this.rtSched = rateSched;
    }

    public MeasurementStation getReceiptLocation() {
        return receiptLocation;
    }

    public Activity receiptLocation(MeasurementStation measurementStation) {
        this.receiptLocation = measurementStation;
        return this;
    }

    public void setReceiptLocation(MeasurementStation measurementStation) {
        this.receiptLocation = measurementStation;
    }

    public MeasurementStation getDeliveryLocation() {
        return deliveryLocation;
    }

    public Activity deliveryLocation(MeasurementStation measurementStation) {
        this.deliveryLocation = measurementStation;
        return this;
    }

    public void setDeliveryLocation(MeasurementStation measurementStation) {
        this.deliveryLocation = measurementStation;
    }

    public Contract getUpstreamContract() {
        return upstreamContract;
    }

    public Activity upstreamContract(Contract contract) {
        this.upstreamContract = contract;
        return this;
    }

    public void setUpstreamContract(Contract contract) {
        this.upstreamContract = contract;
    }

    public Contract getDownstreamContract() {
        return downstreamContract;
    }

    public Activity downstreamContract(Contract contract) {
        this.downstreamContract = contract;
        return this;
    }

    public void setDownstreamContract(Contract contract) {
        this.downstreamContract = contract;
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
        Activity activity = (Activity) o;
        if (activity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), activity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Activity{" +
            "id=" + getId() +
            ", contrId='" + getContrId() + "'" +
            ", activityNbr='" + getActivityNbr() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimeStamp='" + getUpdateTimeStamp() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            "}";
    }
}
