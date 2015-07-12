package com.tchepannou.pds.dto;

import com.google.common.base.Preconditions;
import com.tchepannou.pds.domain.PartyRoleStatusCode;

public class PartyRoleStatusCodeResponse {
    private long id;
    private String name;
    private boolean active;


    //-- Constructor
    private PartyRoleStatusCodeResponse(final Builder builder){
        PartyRoleStatusCode statusCode = builder.statusCode;
        this.id = statusCode.getId();
        this.name = statusCode.getName();
        this.active = statusCode.isActive();
    }

    //-- Getter/Setter
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public static class Builder {
        private PartyRoleStatusCode statusCode;

        public PartyRoleStatusCodeResponse build () {
            Preconditions.checkState(statusCode != null, "statusCode is not set");

            return new PartyRoleStatusCodeResponse(this);
        }

        public Builder withStatusCode (final PartyRoleStatusCode statusCode) {
            this.statusCode = statusCode;
            return this;
        }
    }}
