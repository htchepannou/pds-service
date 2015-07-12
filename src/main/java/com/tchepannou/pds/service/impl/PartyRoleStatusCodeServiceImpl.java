package com.tchepannou.pds.service.impl;

import com.tchepannou.pds.dao.PartyRoleStatusCodeDao;
import com.tchepannou.pds.domain.PartyRoleStatusCode;
import com.tchepannou.pds.dto.PartyRoleStatusCodeListResponse;
import com.tchepannou.pds.service.PartyRoleStatusCodeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PartyRoleStatusCodeServiceImpl implements PartyRoleStatusCodeService {
    @Autowired
    private PartyRoleStatusCodeDao dao;

    @Override
    public PartyRoleStatusCodeListResponse findAll() {
        List<PartyRoleStatusCode> statusCodes = dao.findAll();
        return new PartyRoleStatusCodeListResponse.Builder()
                .withStatusCodes(statusCodes)
                .build();
    }
}
