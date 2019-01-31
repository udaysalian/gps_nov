package com.oilgascs.gps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A BusinessAssociateContact.
 */
@Entity
@Table(name = "ba_contact")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BusinessAssociateContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "begin_date")
    private LocalDate beginDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JsonIgnoreProperties("")
    private BusinessAssociate businessAssociate;

    @ManyToOne
    @JsonIgnoreProperties("")
    private BusinessAssociateAddress mailAddress;

    @ManyToOne
    @JsonIgnoreProperties("")
    private BusinessAssociateAddress deliveryAddress;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Contact contact;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public BusinessAssociateContact beginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public BusinessAssociateContact endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BusinessAssociate getBusinessAssociate() {
        return businessAssociate;
    }

    public BusinessAssociateContact businessAssociate(BusinessAssociate businessAssociate) {
        this.businessAssociate = businessAssociate;
        return this;
    }

    public void setBusinessAssociate(BusinessAssociate businessAssociate) {
        this.businessAssociate = businessAssociate;
    }

    public BusinessAssociateAddress getMailAddress() {
        return mailAddress;
    }

    public BusinessAssociateContact mailAddress(BusinessAssociateAddress businessAssociateAddress) {
        this.mailAddress = businessAssociateAddress;
        return this;
    }

    public void setMailAddress(BusinessAssociateAddress businessAssociateAddress) {
        this.mailAddress = businessAssociateAddress;
    }

    public BusinessAssociateAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public BusinessAssociateContact deliveryAddress(BusinessAssociateAddress businessAssociateAddress) {
        this.deliveryAddress = businessAssociateAddress;
        return this;
    }

    public void setDeliveryAddress(BusinessAssociateAddress businessAssociateAddress) {
        this.deliveryAddress = businessAssociateAddress;
    }

    public Contact getContact() {
        return contact;
    }

    public BusinessAssociateContact contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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
        BusinessAssociateContact businessAssociateContact = (BusinessAssociateContact) o;
        if (businessAssociateContact.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessAssociateContact.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessAssociateContact{" +
            "id=" + getId() +
            ", beginDate='" + getBeginDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
}
