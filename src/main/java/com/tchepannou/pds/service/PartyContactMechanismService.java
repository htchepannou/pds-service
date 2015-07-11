package com.tchepannou.pds.service;

import com.tchepannou.pds.dto.ContactMechanismListResponse;

public interface PartyContactMechanismService {
    ContactMechanismListResponse findByParty(long partyId);
}
