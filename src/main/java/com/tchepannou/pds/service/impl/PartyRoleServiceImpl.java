package com.tchepannou.pds.service.impl;

import com.tchepannou.core.exception.NotFoundException;
import com.tchepannou.pds.dao.*;
import com.tchepannou.pds.domain.*;
import com.tchepannou.pds.dto.CreatePartyRoleRequest;
import com.tchepannou.pds.dto.PartyRoleResponse;
import com.tchepannou.pds.dto.PartyRoleStatusRequest;
import com.tchepannou.pds.enums.PartyKind;
import com.tchepannou.pds.exception.BadRequestException;
import com.tchepannou.pds.service.PartyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class PartyRoleServiceImpl implements PartyRoleService {
    //-- Attributes
    @Autowired
    private PartyDao partyDao;

    @Autowired
    private PartyRoleDao partyRoleDao;

    @Autowired
    private PartyRoleTypeDao typeDao;

    @Autowired
    private PartyRoleStatusDao statusDao;

    @Autowired
    private PartyRoleStatusCodeDao statusCodeDao;


    //-- PartyRoleService implementation
    public PartyRoleResponse findById (long id) {
        PartyRole partyRole = partyRoleDao.findById(id);
        if (partyRole == null) {
            throw new NotFoundException(id, PartyRole.class);
        }

        Party party = partyDao.findById(partyRole.getPartyId());
        PartyRoleType type = typeDao.findById(partyRole.getTypeId());
        PartyRoleStatus status = statusDao.findById(partyRole.getStatusId());
        PartyRoleStatusCode statusCode = statusCodeDao.findById(status.getStatusCodeId());

        return new PartyRoleResponse.Builder()
                .withParty(party)
                .withPartyRole(partyRole)
                .withStatus(status)
                .withStatusCode(statusCode)
                .withType(type)
                .build();
    }

    @Override
    @Transactional
    public PartyRoleResponse create(CreatePartyRoleRequest request) {
        PartyRoleType type = typeDao.findByName(request.getType());
        if (type == null){
            throw new BadRequestException("type");
        }

        Party party = createParty(request);
        PartyRole partyRole = createPartyRole(party, type);

        PartyRoleStatusCode statusCode = statusCodeDao.findDefault(type.getId());
        PartyRoleStatus status = createPartyRoleStatus(partyRole, statusCode.getId(), null);

        return new PartyRoleResponse.Builder()
                .withParty(party)
                .withPartyRole(partyRole)
                .withStatus(status)
                .withStatusCode(statusCode)
                .withType(type)
                .build()
        ;
    }

    @Override
    @Transactional
    public PartyRoleResponse setStatus(long id, PartyRoleStatusRequest request) {
        PartyRole partyRole = partyRoleDao.findById(id);
        if (partyRole == null) {
            throw new NotFoundException(id, PartyRole.class);
        }

        try {
            PartyRoleStatus status = createPartyRoleStatus(partyRole, request.getStatusCode(), request.getComment());

            partyRole.setStatusId(status.getId());
            partyRoleDao.update(partyRole);

            return new PartyRoleResponse.Builder()
                    .withParty(partyDao.findById(partyRole.getPartyId()))
                    .withPartyRole(partyRole)
                    .withStatus(status)
                    .withStatusCode(statusCodeDao.findById(status.getStatusCodeId()))
                    .withType(typeDao.findById(partyRole.getTypeId()))
                    .build()
                    ;
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestException("statusCode");
        }
    }

    //-- Private
    private Party createParty(CreatePartyRoleRequest request){
        Party party = new Party();
        party.setKind(PartyKind.fromText(request.getPartyKind()));
        party.setFirstName(request.getFirstName());
        party.setLastName(request.getLastName());
        party.setName(request.getName());
        party.setFromDate(new Date());
        partyDao.create(party);

        return party;
    }

    private PartyRole createPartyRole(Party party, PartyRoleType type) {
        PartyRole partyRole = new PartyRole();
        partyRole.setPartyId(party.getId());
        partyRole.setTypeId(type.getId());
        partyRole.setFromDate(new Date());
        partyRoleDao.create(partyRole);

        return partyRole;
    }

    private PartyRoleStatus createPartyRoleStatus(PartyRole partyRole, long statusCodeId, String comment) {
        PartyRoleStatus status = new PartyRoleStatus();
        status.setComment(comment);
        status.setStatusCodeId(statusCodeId);
        status.setDate(new Date());
        status.setPartyRoleId(partyRole.getId());
        statusDao.create(status);

        return status;
    }
}
