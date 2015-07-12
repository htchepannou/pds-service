package com.tchepannou.pds.dto;

import com.google.common.base.Preconditions;
import com.tchepannou.pds.domain.*;

import java.util.Date;

public class PartyRoleResponse {
    //-- Attributes
    private long id;
    private Date fromDate;
    private PartyRoleTypeResponse type;
    private PartyResponse party;
    private PartyRoleStatusResponse status;

    //-- Attributes
    private PartyRoleResponse (final Builder builder) {
        final PartyRole role = builder.partyRole;
        this.id = role.getId();
        this.fromDate = role.getFromDate();
        
        this.type = new PartyRoleTypeResponse.Builder()
                .withType(builder.type)
                .build();

        this.party = new PartyResponse.Builder()
                .withParty(builder.party)
                .build();

        if (builder.status != null) {
            this.status = new PartyRoleStatusResponse.Builder()
                    .withStatus(builder.status)
                    .withStatusCode(builder.statusCode)
                    .build();
        }
    }
    
    
    //-- Getter/Setter
    public PartyRoleStatusResponse getStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public PartyResponse getParty() {
        return party;
    }

    public PartyRoleTypeResponse getType() {
        return type;
    }

    //-- Builder
    public static class Builder {
        private PartyRole partyRole;
        private Party party;
        private PartyRoleStatus status;
        private PartyRoleType type;
        private PartyRoleStatusCode statusCode;
     
        public PartyRoleResponse build () {
            Preconditions.checkState(partyRole != null, "partyRole not set");
            
            Preconditions.checkState(party != null, "party not set");
            Preconditions.checkState(party.getId() == partyRole.getPartyId(), "Invalid party");

            if (partyRole.getStatusId() != 0) {
                Preconditions.checkState(status != null, "status not set");
                Preconditions.checkState(status.getId() == partyRole.getStatusId(), "Invalid status");

                Preconditions.checkState(statusCode != null, "statusCode not set");
                Preconditions.checkState(statusCode.getId() == status.getStatusCodeId(), "Invalid statusCode");
            }
            
            Preconditions.checkState(type != null, "type not set");
            Preconditions.checkState(type.getId() == partyRole.getTypeId(), "Invalid type");
            
            return new PartyRoleResponse(this);
        }
        
        public Builder withPartyRole (PartyRole role){
            this.partyRole = role;
            return this;
        }
        
        public Builder withParty (Party party){
            this.party = party;
            return this;
        }

        public Builder withStatus (PartyRoleStatus status){
            this.status = status;
            return this;
        }

        public Builder withStatusCode (PartyRoleStatusCode statusCode){
            this.statusCode = statusCode;
            return this;
        }

        public Builder withType (PartyRoleType type){
            this.type = type;
            return this;
        }
        
    }
}
