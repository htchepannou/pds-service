package com.tchepannou.pds.dao;

import com.tchepannou.pds.domain.PartyRoleStatus;

import java.util.List;

public interface PartyRoleStatusDao {
    PartyRoleStatus findById(long id);
    List<PartyRoleStatus> findByPartyRole(long partyRoleId);
    long create(PartyRoleStatus status);
}
