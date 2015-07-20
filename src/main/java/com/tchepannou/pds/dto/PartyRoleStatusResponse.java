package com.tchepannou.pds.dto;

import com.google.common.base.Preconditions;
import com.tchepannou.pds.domain.PartyRoleStatus;
import com.tchepannou.pds.domain.PartyRoleStatusCode;

import java.io.Serializable;
import java.util.Date;

public class PartyRoleStatusResponse implements Serializable {
    //-- Attribute
    private long id;
    private long statusCode;
    private String statusText;
    private String comment;
    private Date date;

    //-- Constructor
    public PartyRoleStatusResponse(final Builder builder){
        final PartyRoleStatus status = builder.status;
        final PartyRoleStatusCode code = builder.statusCode;

        this.id = status.getId();
        this.statusCode = code.getId();
        this.statusText = code.getName();
        this.date = status.getDate();
        this.comment = status.getComment();
    }

    //-- Getter
    public long getId() {
        return id;
    }

    public long getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    //-- Builder
    public static class Builder {
        private PartyRoleStatus status;
        private PartyRoleStatusCode statusCode;

        public PartyRoleStatusResponse build () {
            Preconditions.checkState(status != null, "status not set");
            Preconditions.checkState(statusCode != null, "statusCode not set");
            Preconditions.checkState(statusCode.getId() == status.getStatusCodeId(), "statusCode is invalid");

            return new PartyRoleStatusResponse(this);
        }
        
        public Builder withStatus(final PartyRoleStatus status) {
            this.status = status;
            return this;
        }
        
        public Builder withStatusCode (final PartyRoleStatusCode statusCode) {
            this.statusCode = statusCode;
            return this;
        }
    }
}
