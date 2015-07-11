package com.tchepannou.pds.service;

import com.tchepannou.pds.domain.PartyPhone;
import com.tchepannou.pds.dto.CreatePartyPhoneRequest;
import com.tchepannou.pds.dto.PartyPhoneRequest;
import com.tchepannou.pds.dto.PartyPhoneResponse;

public interface PartyPhoneService extends AbstractPartyContactMechanismService<PartyPhone>{
    PartyPhoneResponse addAddress(long partyId, CreatePartyPhoneRequest request);

    PartyPhoneResponse updateAddress(long partyId, long addressId, PartyPhoneRequest request);

    void removeAddress(long partyId, long addressId);
}
