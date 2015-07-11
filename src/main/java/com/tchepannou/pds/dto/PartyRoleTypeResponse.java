package com.tchepannou.pds.dto;

import com.google.common.base.Preconditions;
import com.tchepannou.pds.domain.PartyRoleType;

public class PartyRoleTypeResponse {
    private long id;
    private String name;

    //-- Constructor
    private PartyRoleTypeResponse(final Builder builder){
        PartyRoleType status = builder.role;
        this.id = status.getId();
        this.name = status.getName();
    }

    //-- Getter/Setter
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder {
        private PartyRoleType role;

        public PartyRoleTypeResponse build () {
            Preconditions.checkState(role != null, "role is not set");

            return new PartyRoleTypeResponse(this);
        }

        public Builder withPartyRoleType (final PartyRoleType role) {
            this.role = role;
            return this;
        }
    }}
