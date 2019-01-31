package com.oilgascs.gps.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A BusinessUnit.
 */
@Entity
@Table(name = "business_unit")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BusinessUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "business_unit")
    private String businessUnit;

    @Column(name = "business_associate_nbr")
    private String businessAssociateNbr;

    @Column(name = "edi_pipe_id")
    private String ediPipeId;

    @Lob
    @Column(name = "company_logo")
    private byte[] companyLogo;

    @Column(name = "company_logo_content_type")
    private String companyLogoContentType;

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

    public String getBusinessUnit() {
        return businessUnit;
    }

    public BusinessUnit businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getBusinessAssociateNbr() {
        return businessAssociateNbr;
    }

    public BusinessUnit businessAssociateNbr(String businessAssociateNbr) {
        this.businessAssociateNbr = businessAssociateNbr;
        return this;
    }

    public void setBusinessAssociateNbr(String businessAssociateNbr) {
        this.businessAssociateNbr = businessAssociateNbr;
    }

    public String getEdiPipeId() {
        return ediPipeId;
    }

    public BusinessUnit ediPipeId(String ediPipeId) {
        this.ediPipeId = ediPipeId;
        return this;
    }

    public void setEdiPipeId(String ediPipeId) {
        this.ediPipeId = ediPipeId;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public BusinessUnit companyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
        return this;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyLogoContentType() {
        return companyLogoContentType;
    }

    public BusinessUnit companyLogoContentType(String companyLogoContentType) {
        this.companyLogoContentType = companyLogoContentType;
        return this;
    }

    public void setCompanyLogoContentType(String companyLogoContentType) {
        this.companyLogoContentType = companyLogoContentType;
    }

    public String getUpdater() {
        return updater;
    }

    public BusinessUnit updater(String updater) {
        this.updater = updater;
        return this;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    public BusinessUnit updateTimestamp(ZonedDateTime updateTimestamp) {
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
        BusinessUnit businessUnit = (BusinessUnit) o;
        if (businessUnit.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessUnit.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessUnit{" +
            "id=" + getId() +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", businessAssociateNbr='" + getBusinessAssociateNbr() + "'" +
            ", ediPipeId='" + getEdiPipeId() + "'" +
            ", companyLogo='" + getCompanyLogo() + "'" +
            ", companyLogoContentType='" + getCompanyLogoContentType() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimestamp='" + getUpdateTimestamp() + "'" +
            "}";
    }
}
