package com.tchepannou.pds.service.impl;

import com.tchepannou.pds.dao.PartyRoleTypeDao;
import com.tchepannou.pds.domain.PartyRoleType;
import com.tchepannou.pds.dto.PartyRoleTypeListResponse;
import com.tchepannou.pds.service.PartyRoleTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PartyRoleTypeServiceImpl implements PartyRoleTypeService {
    @Autowired
    private PartyRoleTypeDao dao;

    @Override
    public PartyRoleTypeListResponse findAll() {
        List<PartyRoleType> types = dao.findAll();
        return new PartyRoleTypeListResponse.Builder()
                .withPartyRoleTypes(types)
                .build();
    }
}
