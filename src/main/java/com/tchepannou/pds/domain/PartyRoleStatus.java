package com.tchepannou.pds.domain;

import com.tchepannou.core.domain.Persistent;

import java.util.Date;

public class PartyRoleStatus extends Persistent {
    //-- Attribute
    private long partyRoleId;
    private long statusCodeId;
    private String comment;
    private Date date;


    //-- Getter/Setter
    public long getPartyRoleId() {
        return partyRoleId;
    }

    public void setPartyRoleId(long partyRoleId) {
        this.partyRoleId = partyRoleId;
    }

    public long getStatusCodeId() {
        return statusCodeId;
    }

    public void setStatusCodeId(long statusCodeId) {
        this.statusCodeId = statusCodeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
