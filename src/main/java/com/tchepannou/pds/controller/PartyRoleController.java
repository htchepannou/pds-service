package com.tchepannou.pds.controller;

import com.tchepannou.pds.dto.PartyRoleStatusCodeListResponse;
import com.tchepannou.pds.dto.PartyRoleTypeListResponse;
import com.tchepannou.pds.service.PartyRoleStatusCodeService;
import com.tchepannou.pds.service.PartyRoleTypeService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(basePath = "/party-roles", value = "Party Roles", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value="/api/party-roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartyRoleController extends AbstractController {
    //-- Attributes
    private Logger LOG = LoggerFactory.getLogger(PartyRoleController.class);

    @Autowired
    private PartyRoleTypeService partyRoleTypeService;

    @Autowired
    private PartyRoleStatusCodeService partyRoleStatusCodeService;

    //-- AbstractController overrides
    @Override
    protected Logger getLogger() {
        return LOG;
    }

    //-- REST methods
    @RequestMapping(method = RequestMethod.GET, value = "/types")
    @ApiOperation("Returns all the PartyRole types ")
    public PartyRoleTypeListResponse types (){
        return partyRoleTypeService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/status-codes")
    @ApiOperation("Returns all the PartyRole status codes")
    public PartyRoleStatusCodeListResponse statusCodes (){
        return partyRoleStatusCodeService.findAll();
    }
}
