package com.oilgascs.gps.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BusinessAssociateAddress entity.
 */
public class BusinessAssociateAddressDTO implements Serializable {

    private Long id;

    @Size(max = 100)
    private String addLine1;

    private String addressNbr;

    private String addLine2;

    @Size(max = 100)
    private String city;

    @Size(max = 100)
    private String state;

    @Size(max = 100)
    private String zipCode;

    private Long businessAssociateId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddLine1() {
        return addLine1;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }

    public String getAddressNbr() {
        return addressNbr;
    }

    public void setAddressNbr(String addressNbr) {
        this.addressNbr = addressNbr;
    }

    public String getAddLine2() {
        return addLine2;
    }

    public void setAddLine2(String addLine2) {
        this.addLine2 = addLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getBusinessAssociateId() {
        return businessAssociateId;
    }

    public void setBusinessAssociateId(Long businessAssociateId) {
        this.businessAssociateId = businessAssociateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BusinessAssociateAddressDTO businessAssociateAddressDTO = (BusinessAssociateAddressDTO) o;
        if (businessAssociateAddressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessAssociateAddressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessAssociateAddressDTO{" +
            "id=" + getId() +
            ", addLine1='" + getAddLine1() + "'" +
            ", addressNbr='" + getAddressNbr() + "'" +
            ", addLine2='" + getAddLine2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            ", businessAssociate=" + getBusinessAssociateId() +
            "}";
    }
}
