package com.oilgascs.gps.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BusinessAssociate entity.
 */
public class BusinessAssociateDTO implements Serializable {

    private Long id;

    @NotNull
    private String baName;

    @NotNull
    private String baAbbr;

    private String baNbr;

    private String baDunsNbr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaName() {
        return baName;
    }

    public void setBaName(String baName) {
        this.baName = baName;
    }

    public String getBaAbbr() {
        return baAbbr;
    }

    public void setBaAbbr(String baAbbr) {
        this.baAbbr = baAbbr;
    }

    public String getBaNbr() {
        return baNbr;
    }

    public void setBaNbr(String baNbr) {
        this.baNbr = baNbr;
    }

    public String getBaDunsNbr() {
        return baDunsNbr;
    }

    public void setBaDunsNbr(String baDunsNbr) {
        this.baDunsNbr = baDunsNbr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BusinessAssociateDTO businessAssociateDTO = (BusinessAssociateDTO) o;
        if (businessAssociateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessAssociateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessAssociateDTO{" +
            "id=" + getId() +
            ", baName='" + getBaName() + "'" +
            ", baAbbr='" + getBaAbbr() + "'" +
            ", baNbr='" + getBaNbr() + "'" +
            ", baDunsNbr='" + getBaDunsNbr() + "'" +
            "}";
    }
}
