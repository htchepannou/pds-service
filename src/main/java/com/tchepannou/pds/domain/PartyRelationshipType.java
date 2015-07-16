package com.tchepannou.pds.domain;

import com.tchepannou.core.domain.PersistentEnum;

public class PartyRelationshipType extends PersistentEnum{
    //-- Private
    private long fromTypeId;
    private long toTypeId;

    //-- Public
    public long getFromTypeId() {
        return fromTypeId;
    }

    public void setFromTypeId(long fromTypeId) {
        this.fromTypeId = fromTypeId;
    }

    public long getToTypeId() {
        return toTypeId;
    }

    public void setToTypeId(long toTypeId) {
        this.toTypeId = toTypeId;
    }
}
