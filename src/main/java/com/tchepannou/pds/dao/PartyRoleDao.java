package com.tchepannou.pds.dao;

import com.tchepannou.pds.domain.PartyRole;

public interface PartyRoleDao {
    PartyRole findById (long id);

    long create (PartyRole partyRole);

    void update (PartyRole partyRole);
}
