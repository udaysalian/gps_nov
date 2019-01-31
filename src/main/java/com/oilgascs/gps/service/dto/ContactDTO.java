package com.oilgascs.gps.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Contact entity.
 */
public class ContactDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String updater;

    private ZonedDateTime updateTimestamp;

    private Long loginIdId;

    private String loginIdLogin;

    private Long employedById;

    private String employedByBaAbbr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Long getLoginIdId() {
        return loginIdId;
    }

    public void setLoginIdId(Long userId) {
        this.loginIdId = userId;
    }

    public String getLoginIdLogin() {
        return loginIdLogin;
    }

    public void setLoginIdLogin(String userLogin) {
        this.loginIdLogin = userLogin;
    }

    public Long getEmployedById() {
        return employedById;
    }

    public void setEmployedById(Long businessAssociateId) {
        this.employedById = businessAssociateId;
    }

    public String getEmployedByBaAbbr() {
        return employedByBaAbbr;
    }

    public void setEmployedByBaAbbr(String businessAssociateBaAbbr) {
        this.employedByBaAbbr = businessAssociateBaAbbr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactDTO contactDTO = (ContactDTO) o;
        if (contactDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contactDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", updater='" + getUpdater() + "'" +
            ", updateTimestamp='" + getUpdateTimestamp() + "'" +
            ", loginId=" + getLoginIdId() +
            ", loginId='" + getLoginIdLogin() + "'" +
            ", employedBy=" + getEmployedById() +
            ", employedBy='" + getEmployedByBaAbbr() + "'" +
            "}";
    }
}
