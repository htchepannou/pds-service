package com.tchepannou.pds.controller;

import com.tchepannou.pds.dto.*;
import com.tchepannou.pds.service.PartyRoleService;
import com.tchepannou.pds.service.PartyRoleStatusCodeService;
import com.tchepannou.pds.service.PartyRoleTypeService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(basePath = "/party-roles", value = "Party Roles", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(value="/api/party-roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartyRoleController extends AbstractController {
    //-- Attributes
    private static final Logger LOG = LoggerFactory.getLogger(PartyRoleController.class);

    @Autowired
    private PartyRoleTypeService partyRoleTypeService;

    @Autowired
    private PartyRoleStatusCodeService partyRoleStatusCodeService;

    @Autowired
    private PartyRoleService partyRoleService;

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

    @RequestMapping(method = RequestMethod.GET, value = "/{partyRoleId}")
    @ApiOperation("Returns a PartyRole")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 404, message = "PartyRole not found", response = ErrorResponse.class)
    })
    public PartyRoleResponse findById (@PathVariable final long partyRoleId){
        return partyRoleService.findById(partyRoleId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("Create")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 404, message = "PartyRole not found", response = ErrorResponse.class)
    })
    public ResponseEntity<PartyRoleResponse> create (@Valid @RequestBody final CreatePartyRoleRequest request){
        PartyRoleResponse response = partyRoleService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{partyRoleId}/status")
    @ApiOperation("Set Status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "PartyRole not found", response = ErrorResponse.class)
    })
    public PartyRoleResponse setStatus(
            @PathVariable final long partyRoleId,
            @Valid @RequestBody final PartyRoleStatusRequest request
    ){
        return partyRoleService.setStatus(partyRoleId, request);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{partyRoleId}/link")
    @ApiOperation("Link")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Bad type, fromId or toId", response = ErrorResponse.class)
    })
    public void link(
            @PathVariable final long partyRoleId,
            @Valid @RequestBody final PartyRoleRelationshipRequest request
    ){
        partyRoleService.link(partyRoleId, request);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{partyRoleId}/unlink")
    @ApiOperation("Unlink")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Bad type, fromId or toId", response = ErrorResponse.class)
    })
    public void unlink(
            @PathVariable final long partyRoleId,
            @Valid @RequestBody final PartyRoleRelationshipRequest request
    ){
        partyRoleService.unlink(partyRoleId, request);
    }
}
