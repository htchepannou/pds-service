package com.tchepannou.pds.service.impl;

import com.tchepannou.core.exception.NotFoundException;
import com.tchepannou.pds.dao.PartyDao;
import com.tchepannou.pds.domain.Party;
import com.tchepannou.pds.dto.CreatePartyRequest;
import com.tchepannou.pds.dto.PartyRequest;
import com.tchepannou.pds.dto.PartyResponse;
import com.tchepannou.pds.enums.Gender;
import com.tchepannou.pds.enums.PartyKind;
import com.tchepannou.pds.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class PartyServiceImpl implements PartyService {
    //-- Attributes
    @Autowired
    private PartyDao partyDao;

    //-- PartyService overrides
    @Override
    public PartyResponse findById(long id) {
        Party party = partyDao.findById(id);
        if (party == null) {
            throw new NotFoundException(id, Party.class);
        }
        return toPartyResponse(party);
    }

    @Override
    public PartyResponse create(final CreatePartyRequest request) {
        Party party = new Party();
        party.setKind(PartyKind.fromText(request.getKind()));
        party.setFromDate(new Date());
        update(party, request);
        partyDao.create(party);

        return toPartyResponse(party);
    }

    @Override
    public PartyResponse update(long id, PartyRequest request) {
        Party party = partyDao.findById(id);
        if (party == null) {
            throw new NotFoundException(id, Party.class);
        }

        update(party, request);
        partyDao.update(party);
        return toPartyResponse(party);
    }

    //-- Private
    private PartyResponse toPartyResponse (final Party party) {
        return new PartyResponse
                .Builder()
                .withParty(party)
                .build();
    }

    private void update (final Party party, final PartyRequest request) {
        party.setFirstName(request.getFirstName());
        party.setLastName(request.getLastName());
        party.setName(request.getName());
        party.setBirthDate(request.getBirthDate());
        party.setGender(Gender.fromText(request.getGender()));
        party.setHeigth(request.getHeigth());
        party.setWeight(request.getWeight());
        party.setPrefix(request.getPrefix());
        party.setSuffix(request.getSuffix());
    }
}
