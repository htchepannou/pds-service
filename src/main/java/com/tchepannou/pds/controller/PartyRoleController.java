package com.tchepannou.pds.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    //-- AbstractController overrides
    @Override
    protected Logger getLogger() {
        return LOG;
    }

    //-- REST methods
    @RequestMapping(method = RequestMethod.GET, value = "/types")
    @ApiOperation("Returns all the PartyRole types ")
    public void types (){

    }
}
