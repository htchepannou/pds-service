package com.tchepannou.pds.service.impl;

import com.tchepannou.core.exception.NotFoundException;
import com.tchepannou.pds.dao.*;
import com.tchepannou.pds.domain.*;
import com.tchepannou.pds.dto.CreatePartyRoleRequest;
import com.tchepannou.pds.dto.PartyRoleRelationshipRequest;
import com.tchepannou.pds.dto.PartyRoleResponse;
import com.tchepannou.pds.dto.PartyRoleStatusRequest;
import com.tchepannou.pds.enums.PartyKind;
import com.tchepannou.pds.exception.BadRequestException;
import com.tchepannou.pds.service.PartyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private PartyRoleRelationshipTypeDao partyRelationshipTypeDao;

    @Autowired
    private PartyRoleRelationshipDao partyRelationshipDao;

    //-- PartyRoleService implementation
    public PartyRoleResponse findById (final long id) {
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
    public PartyRoleResponse create(final CreatePartyRoleRequest request) {
        PartyRoleType type = typeDao.findByName(request.getType());
        if (type == null){
            throw new BadRequestException("type");
        }

        Party party = createParty(request);
        PartyRole partyRole = createPartyRole(party, type);

        PartyRoleStatusCode statusCode = statusCodeDao.findDefault(type.getId());
        PartyRoleStatus status = createPartyRoleStatus(partyRole, statusCode.getId(), null);
        createPartyRelationship(partyRole, request.getRelationship());

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
    public PartyRoleResponse setStatus(final long id, final PartyRoleStatusRequest request) {
        PartyRole partyRole = partyRoleDao.findById(id);
        if (partyRole == null) {
            throw new NotFoundException(id, PartyRole.class);
        }

        PartyRoleStatusCode statusCode = statusCodeDao.findById(request.getStatusCode());
        if (statusCode == null){
            throw new NotFoundException(request.getStatusCode(), PartyRoleStatus.class);
        }

        /* create */
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
    }

    @Override
    @Transactional
    public boolean link(final long fromId, final PartyRoleRelationshipRequest request) {
        PartyRelationshipType type = partyRelationshipTypeDao.findByName(request.getTypeName());
        if (type == null){
            throw new NotFoundException(request.getTypeName(), PartyRelationshipType.class);
        }

        PartyRole from = partyRoleDao.findById(fromId);
        if (from == null){
            throw new NotFoundException(fromId, PartyRole.class);
        }

        PartyRole to = partyRoleDao.findById(request.getToId());
        if (to == null){
            throw new NotFoundException(request.getToId(), PartyRole.class);
        }

        PartyRoleRelationship relationship = partyRelationshipDao.findByFromByToByType(fromId, to.getId(), type.getId());
        if (relationship == null){
            relationship = new PartyRoleRelationship();
            relationship.setToId(to.getId());
            relationship.setFromId(fromId);
            relationship.setFromDate(new Date());
            relationship.setTypeId(type.getId());
            partyRelationshipDao.create(relationship);
            return true;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean unlink(final long fromId, final PartyRoleRelationshipRequest request) {
        PartyRelationshipType type = partyRelationshipTypeDao.findByName(request.getTypeName());
        if (type == null){
            return false;
        }

        PartyRoleRelationship relationship = partyRelationshipDao.findByFromByToByType(fromId, request.getToId(), type.getId());
        if (relationship != null){
            partyRelationshipDao.delete(relationship.getId());
            return true;
        } else {
            List keys = new ArrayList();
            keys.add(Arrays.asList(fromId, request.getToId(), request.getTypeName()));
            throw new NotFoundException((ArrayList)keys, PartyRoleRelationship.class);
        }
    }

    //-- Private
    private Party createParty(final CreatePartyRoleRequest request){
        Party party = new Party();
        party.setKind(PartyKind.fromText(request.getKind()));
        party.setFirstName(request.getFirstName());
        party.setLastName(request.getLastName());
        party.setName(request.getName());
        party.setFromDate(new Date());
        partyDao.create(party);

        return party;
    }

    private PartyRole createPartyRole(final Party party, final PartyRoleType type) {
        PartyRole partyRole = new PartyRole();
        partyRole.setPartyId(party.getId());
        partyRole.setTypeId(type.getId());
        partyRole.setFromDate(new Date());
        partyRoleDao.create(partyRole);

        return partyRole;
    }

    private PartyRoleRelationship createPartyRelationship (final PartyRole from, final PartyRoleRelationshipRequest request) {
        if (request == null) {
            return null;
        }

        final PartyRelationshipType type =  partyRelationshipTypeDao.findByName(request.getTypeName());
        if (type == null){
            throw new BadRequestException("relationship.typeName");
        }

        final PartyRole to = partyRoleDao.findById(request.getToId());
        if (to == null) {
            throw new NotFoundException(request.getToId(), PartyRole.class);
        }

        PartyRoleRelationship relationship = new PartyRoleRelationship();
        relationship.setTypeId(type.getId());
        relationship.setFromId(from.getId());
        relationship.setToId(to.getId());
        relationship.setFromDate(new Date());

        partyRelationshipDao.create(relationship);

        return relationship;
    }

    private PartyRoleStatus createPartyRoleStatus(final PartyRole partyRole, final long statusCodeId, final String comment) {
        PartyRoleStatus status = new PartyRoleStatus();
        status.setComment(comment);
        status.setStatusCodeId(statusCodeId);
        status.setDate(new Date());
        status.setPartyRoleId(partyRole.getId());
        statusDao.create(status);

        return status;
    }
}
