package com.tchepannou.pds.dao;

import com.tchepannou.pds.domain.PartyRoleRelationship;

import java.util.List;

public interface PartyRoleRelationshipDao {
    PartyRoleRelationship findById (long id);
    List<PartyRoleRelationship> findByFromId (long fromId);
    long create(PartyRoleRelationship relationship);
    void delete(PartyRoleRelationship relationship);
}
