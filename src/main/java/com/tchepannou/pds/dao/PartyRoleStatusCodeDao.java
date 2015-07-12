package com.tchepannou.pds.dao;

import com.tchepannou.core.dao.AbstractPersistentEnumDao;
import com.tchepannou.pds.domain.PartyRoleStatusCode;

public interface PartyRoleStatusCodeDao extends AbstractPersistentEnumDao<PartyRoleStatusCode> {
    PartyRoleStatusCode findDefault(long typeId);

    PartyRoleStatusCode findByPartyRole(long partyRoleId);
}
