package com.tchepannou.pds.dao;

import com.tchepannou.pds.domain.PartyRoleRelationship;

import java.util.List;

public interface PartyRoleRelationshipDao {
    PartyRoleRelationship findById (long id);
    PartyRoleRelationship findByFromByToByType (long fromId, long toId, long typeId);
    List<PartyRoleRelationship> findByFromId (long fromId);
    long create(PartyRoleRelationship relationship);
    void delete(long id);
}
