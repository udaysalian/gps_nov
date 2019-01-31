package com.oilgascs.gps.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the BusinessUnit entity.
 */
public class BusinessUnitDTO implements Serializable {

    private Long id;

    private String businessUnit;

    private String businessAssociateNbr;

    private String ediPipeId;

    @Lob
    private byte[] companyLogo;
    private String companyLogoContentType;

    private String updater;

    private ZonedDateTime updateTimestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getBusinessAssociateNbr() {
        return businessAssociateNbr;
    }

    public void setBusinessAssociateNbr(String businessAssociateNbr) {
        this.businessAssociateNbr = businessAssociateNbr;
    }

    public String getEdiPipeId() {
        return ediPipeId;
    }

    public void setEdiPipeId(String ediPipeId) {
        this.ediPipeId = ediPipeId;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyLogoContentType() {
        return companyLogoContentType;
    }

    public void setCompanyLogoContentType(String companyLogoContentType) {
        this.companyLogoContentType = companyLogoContentType;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public ZonedDateTime getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(ZonedDateTime updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BusinessUnitDTO businessUnitDTO = (BusinessUnitDTO) o;
        if (businessUnitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessUnitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessUnitDTO{" +
            "id=" + getId() +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", businessAssociateNbr='" + getBusinessAssociateNbr() + "'" +
            ", ediPipeId='" + getEdiPipeId() + "'" +
            ", companyLogo='" + getCompanyLogo() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimestamp='" + getUpdateTimestamp() + "'" +
            "}";
    }
}
