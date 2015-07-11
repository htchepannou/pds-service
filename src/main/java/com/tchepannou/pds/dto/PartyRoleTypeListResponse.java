package com.tchepannou.pds.dto;

import com.tchepannou.pds.domain.PartyRoleType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PartyRoleTypeListResponse {
    //-- Attributes
    private final List<PartyRoleTypeResponse> types;

    //-- Constructor
    private PartyRoleTypeListResponse(final Builder builder){
        final PartyRoleTypeResponse.Builder statusBuilder = new PartyRoleTypeResponse.Builder();
        types = builder.types.stream()
                .map(type -> statusBuilder.withPartyRoleType(type).build())
                .collect(Collectors.toList());
    }

    //-- Getter/Setter
    public List<PartyRoleTypeResponse> getTypes() {
        return Collections.unmodifiableList(types);
    }

    public static class Builder {
        private List<PartyRoleType> types = new ArrayList<>();

        public PartyRoleTypeListResponse build () {
            return new PartyRoleTypeListResponse(this);
        }

        public Builder withPartyRoleTypes (final List<PartyRoleType> statuses) {
            this.types = Collections.unmodifiableList(statuses);
            return this;
        }
    }
}
