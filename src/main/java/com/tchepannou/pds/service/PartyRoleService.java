package com.tchepannou.pds.service;

import com.tchepannou.pds.dto.CreatePartyRoleRequest;
import com.tchepannou.pds.dto.PartyRoleResponse;

public interface PartyRoleService {
    PartyRoleResponse findById (long id);

    PartyRoleResponse create (CreatePartyRoleRequest request);
}
