package com.tchepannou.pds.service;

import com.tchepannou.pds.domain.PartyPostalAddress;
import com.tchepannou.pds.dto.CreatePartyPostalAddressRequest;
import com.tchepannou.pds.dto.PartyPostalAddressRequest;
import com.tchepannou.pds.dto.PartyPostalAddressResponse;

public interface PartyPostalAddressService extends AbstractPartyContactMechanismService<PartyPostalAddress>{
    PartyPostalAddressResponse addAddress(long partyId, CreatePartyPostalAddressRequest request);

    PartyPostalAddressResponse updateAddress(long partyId, long addressId, PartyPostalAddressRequest request);

    void removeAddress(long partyId, long addressId);
}
