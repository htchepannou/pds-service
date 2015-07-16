package com.tchepannou.pds.domain;

import com.tchepannou.core.domain.Persistent;

import java.util.Date;

public class PartyRoleRelationship extends Persistent {
    //-- Attributes
    private long fromId;
    private long toId;
    private long typeId;
    private Date fromDate;

    //-- Getter/Setter
    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public long getToId() {
        return toId;
    }

    public void setToId(long toId) {
        this.toId = toId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
}
