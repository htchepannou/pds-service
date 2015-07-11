package com.tchepannou.pds.dto.party;

import com.tchepannou.pds.domain.ContactMechanismPurpose;
import com.tchepannou.pds.domain.PartyContactMecanism;
import com.tchepannou.pds.enums.Privacy;

public abstract class AbstractPartyContactMechanismResponse {
    private long id;
    private boolean noSolicitation;
    private String privacy;
    private String purpose;

    protected AbstractPartyContactMechanismResponse(
            final PartyContactMecanism contactMechanism,
            final ContactMechanismPurpose purpose
    ){
        final Privacy aPrivacy = contactMechanism.getPrivacy();
        this.id = contactMechanism.getId();
        this.noSolicitation = contactMechanism.isNoSolicitation();
        this.privacy = aPrivacy != null ? aPrivacy.toString() : null;

        this.purpose = purpose != null ? purpose.getName() : null;
    }

    public long getId() {
        return id;
    }

    public boolean isNoSolicitation() {
        return noSolicitation;
    }

    public String getPrivacy() {
        return privacy;
    }

    public String getPurpose() {
        return purpose;
    }
}
