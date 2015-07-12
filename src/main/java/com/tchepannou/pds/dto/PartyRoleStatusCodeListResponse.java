package com.tchepannou.pds.dto;

import com.tchepannou.pds.domain.PartyRoleStatusCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PartyRoleStatusCodeListResponse {
    //-- Attributes
    private final List<PartyRoleStatusCodeResponse> statusCodes;

    //-- Constructor
    private PartyRoleStatusCodeListResponse(final Builder builder){
        final PartyRoleStatusCodeResponse.Builder statusBuilder = new PartyRoleStatusCodeResponse.Builder();
        statusCodes = builder.statusCodes.stream()
                .map(type -> statusBuilder.withStatusCode(type).build())
                .collect(Collectors.toList());
    }

    //-- Getter/Setter
    public List<PartyRoleStatusCodeResponse> getStatusCodes() {
        return Collections.unmodifiableList(statusCodes);
    }

    public static class Builder {
        private List<PartyRoleStatusCode> statusCodes = new ArrayList<>();

        public PartyRoleStatusCodeListResponse build () {
            return new PartyRoleStatusCodeListResponse(this);
        }

        public Builder withStatusCodes(final List<PartyRoleStatusCode> statuses) {
            this.statusCodes = Collections.unmodifiableList(statuses);
            return this;
        }
    }
}
