package com.oilgascs.gps.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A BusinessAssociateAddress.
 */
@Entity
@Table(name = "ba_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BusinessAssociateAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "add_line_1", length = 100)
    private String addLine1;

    @Column(name = "address_nbr")
    private String addressNbr;

    @Column(name = "add_line_2")
    private String addLine2;

    @Size(max = 100)
    @Column(name = "city", length = 100)
    private String city;

    @Size(max = 100)
    @Column(name = "state", length = 100)
    private String state;

    @Size(max = 100)
    @Column(name = "zip_code", length = 100)
    private String zipCode;

    @ManyToOne
    @JsonIgnoreProperties("businessAssociateAddresses")
    private BusinessAssociate businessAssociate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public BusinessAssociateAddress addLine1(String addLine1) {
        this.addLine1 = addLine1;
        return this;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }

    public String getAddressNbr() {
        return addressNbr;
    }

    public BusinessAssociateAddress addressNbr(String addressNbr) {
        this.addressNbr = addressNbr;
        return this;
    }

    public void setAddressNbr(String addressNbr) {
        this.addressNbr = addressNbr;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public BusinessAssociateAddress addLine2(String addLine2) {
        this.addLine2 = addLine2;
        return this;
    }

    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }

    public String getCity() {
        return city;
    }

    public BusinessAssociateAddress city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public BusinessAssociateAddress state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public BusinessAssociateAddress zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public BusinessAssociate getBusinessAssociate() {
        return businessAssociate;
    }

    public BusinessAssociateAddress businessAssociate(BusinessAssociate businessAssociate) {
        this.businessAssociate = businessAssociate;
        return this;
    }

    public void setBusinessAssociate(BusinessAssociate businessAssociate) {
        this.businessAssociate = businessAssociate;
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
        BusinessAssociateAddress businessAssociateAddress = (BusinessAssociateAddress) o;
        if (businessAssociateAddress.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessAssociateAddress.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessAssociateAddress{" +
            "id=" + getId() +
            ", addLine1='" + getAddLine1() + "'" +
            ", addressNbr='" + getAddressNbr() + "'" +
            ", addLine2='" + getAddLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            "}";
    }
}
