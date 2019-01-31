package com.oilgascs.gps.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BusinessAssociateContact entity.
 */
public class BusinessAssociateContactDTO implements Serializable {

    private Long id;

    private LocalDate beginDate;

    private LocalDate endDate;

    private Long businessAssociateId;

    private String businessAssociateBaAbbr;

    private Long mailAddressId;

    private Long deliveryAddressId;

    private Long contactId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getBusinessAssociateId() {
        return businessAssociateId;
    }

    public void setBusinessAssociateId(Long businessAssociateId) {
        this.businessAssociateId = businessAssociateId;
    }

    public String getBusinessAssociateBaAbbr() {
        return businessAssociateBaAbbr;
    }

    public void setBusinessAssociateBaAbbr(String businessAssociateBaAbbr) {
        this.businessAssociateBaAbbr = businessAssociateBaAbbr;
    }

    public Long getMailAddressId() {
        return mailAddressId;
    }

    public void setMailAddressId(Long businessAssociateAddressId) {
        this.mailAddressId = businessAssociateAddressId;
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long businessAssociateAddressId) {
        this.deliveryAddressId = businessAssociateAddressId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BusinessAssociateContactDTO businessAssociateContactDTO = (BusinessAssociateContactDTO) o;
        if (businessAssociateContactDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessAssociateContactDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessAssociateContactDTO{" +
            "id=" + getId() +
            ", beginDate='" + getBeginDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", businessAssociate=" + getBusinessAssociateId() +
            ", businessAssociate='" + getBusinessAssociateBaAbbr() + "'" +
            ", mailAddress=" + getMailAddressId() +
            ", deliveryAddress=" + getDeliveryAddressId() +
            ", contact=" + getContactId() +
            "}";
    }
}
