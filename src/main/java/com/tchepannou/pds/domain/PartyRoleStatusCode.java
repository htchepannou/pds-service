package com.tchepannou.pds.domain;

import com.tchepannou.core.domain.PersistentEnum;

public class PartyRoleStatusCode extends PersistentEnum {
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
