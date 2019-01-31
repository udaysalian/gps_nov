package com.oilgascs.gps.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A BusinessAssociate.
 */
@Entity
@Table(name = "bus_assoc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BusinessAssociate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ba_name", nullable = false)
    private String baName;

    @NotNull
    @Column(name = "ba_abbr", nullable = false)
    private String baAbbr;

    @Column(name = "ba_nbr")
    private String baNbr;

    @Column(name = "ba_duns_nbr")
    private String baDunsNbr;

    @OneToMany(mappedBy = "busAssoc")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contract> contracts = new HashSet<>();

    @OneToMany(mappedBy = "businessAssociate")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BusinessAssociateAddress> businessAssociateAddresses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaName() {
        return baName;
    }

    public BusinessAssociate baName(String baName) {
        this.baName = baName;
        return this;
    }

    public void setBaName(String baName) {
        this.baName = baName;
    }

    public String getBaAbbr() {
        return baAbbr;
    }

    public BusinessAssociate baAbbr(String baAbbr) {
        this.baAbbr = baAbbr;
        return this;
    }

    public void setBaAbbr(String baAbbr) {
        this.baAbbr = baAbbr;
    }

    public String getBaNbr() {
        return baNbr;
    }

    public BusinessAssociate baNbr(String baNbr) {
        this.baNbr = baNbr;
        return this;
    }

    public void setBaNbr(String baNbr) {
        this.baNbr = baNbr;
    }

    public String getBaDunsNbr() {
        return baDunsNbr;
    }

    public BusinessAssociate baDunsNbr(String baDunsNbr) {
        this.baDunsNbr = baDunsNbr;
        return this;
    }

    public void setBaDunsNbr(String baDunsNbr) {
        this.baDunsNbr = baDunsNbr;
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public BusinessAssociate contracts(Set<Contract> contracts) {
        this.contracts = contracts;
        return this;
    }

    public BusinessAssociate addContracts(Contract contract) {
        this.contracts.add(contract);
        contract.setBusAssoc(this);
        return this;
    }

    public BusinessAssociate removeContracts(Contract contract) {
        this.contracts.remove(contract);
        contract.setBusAssoc(null);
        return this;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public Set<BusinessAssociateAddress> getBusinessAssociateAddresses() {
        return businessAssociateAddresses;
    }

    public BusinessAssociate businessAssociateAddresses(Set<BusinessAssociateAddress> businessAssociateAddresses) {
        this.businessAssociateAddresses = businessAssociateAddresses;
        return this;
    }

    public BusinessAssociate addBusinessAssociateAddress(BusinessAssociateAddress businessAssociateAddress) {
        this.businessAssociateAddresses.add(businessAssociateAddress);
        businessAssociateAddress.setBusinessAssociate(this);
        return this;
    }

    public BusinessAssociate removeBusinessAssociateAddress(BusinessAssociateAddress businessAssociateAddress) {
        this.businessAssociateAddresses.remove(businessAssociateAddress);
        businessAssociateAddress.setBusinessAssociate(null);
        return this;
    }

    public void setBusinessAssociateAddresses(Set<BusinessAssociateAddress> businessAssociateAddresses) {
        this.businessAssociateAddresses = businessAssociateAddresses;
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
        BusinessAssociate businessAssociate = (BusinessAssociate) o;
        if (businessAssociate.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessAssociate.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessAssociate{" +
            "id=" + getId() +
            ", baName='" + getBaName() + "'" +
            ", baAbbr='" + getBaAbbr() + "'" +
            ", baNbr='" + getBaNbr() + "'" +
            ", baDunsNbr='" + getBaDunsNbr() + "'" +
            "}";
    }
}
