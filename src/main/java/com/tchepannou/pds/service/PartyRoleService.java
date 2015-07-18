package com.tchepannou.pds.service;

import com.tchepannou.pds.dto.CreatePartyRoleRequest;
import com.tchepannou.pds.dto.PartyRoleRelationshipRequest;
import com.tchepannou.pds.dto.PartyRoleResponse;
import com.tchepannou.pds.dto.PartyRoleStatusRequest;

public interface PartyRoleService {
    PartyRoleResponse findById (long id);

    PartyRoleResponse create (CreatePartyRoleRequest request);

    PartyRoleResponse setStatus(long id, PartyRoleStatusRequest request);

    boolean link (long fromId, PartyRoleRelationshipRequest request);

    boolean unlink (long fromId, PartyRoleRelationshipRequest request);
}
