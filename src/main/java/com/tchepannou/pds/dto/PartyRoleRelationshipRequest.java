package com.tchepannou.pds.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

public class PartyRoleRelationshipRequest {
    @Min(value = 1, message="toId")
    private long toId;

    @NotBlank(message = "typeName")
    private String typeName;

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
