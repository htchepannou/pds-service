package com.tchepannou.pds.dao;

import com.tchepannou.pds.domain.Party;

public interface PartyDao {
    Party findById (long id);

    long create (Party party);

    void update (Party party);
}
