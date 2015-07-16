package com.tchepannou.pds.dto;

public class PartyRoleRelationshipRequest {
    private Integer toId;
    private String typeName;

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
