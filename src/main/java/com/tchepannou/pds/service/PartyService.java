package com.tchepannou.pds.service;

import com.tchepannou.pds.dto.CreatePartyRequest;
import com.tchepannou.pds.dto.PartyResponse;

public interface PartyService {
    PartyResponse findById(long id);

    PartyResponse create (CreatePartyRequest request);
}
