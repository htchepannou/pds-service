package com.tchepannou.pds.dto;

import org.hibernate.validator.constraints.NotBlank;

public class CreatePartyRoleRequest extends CreatePartyRequest{
    //-- Attributes
    @NotBlank(message = "type")
    private String type;

    private PartyRoleRelationshipRequest relationship;

    //-- Getter/Setter
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PartyRoleRelationshipRequest getRelationship() {
        return relationship;
    }

    public void setRelationship(PartyRoleRelationshipRequest relationship) {
        this.relationship = relationship;
    }
}
