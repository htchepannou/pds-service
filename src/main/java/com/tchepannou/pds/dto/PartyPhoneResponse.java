package com.tchepannou.pds.dto;

import com.google.common.base.Preconditions;
import com.tchepannou.pds.domain.ContactMechanismPurpose;
import com.tchepannou.pds.domain.PartyPhone;
import com.tchepannou.pds.domain.Phone;

public class PartyPhoneResponse extends AbstractPartyContactMechanismResponse{
    private String countryCode;
    private String number;
    private String extension;

    private PartyPhoneResponse(final Builder builder){
        super(builder.partyPhone, builder.contactMechanismPurpose);

        this.number = builder.phone.getNumber();
        this.extension = builder.phone.getExtension();
        this.countryCode = builder.phone.getCountryCode();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getExtension() {
        return extension;
    }

    //-- Builder
    public static class Builder {
        private Phone phone;
        private PartyPhone partyPhone;
        private ContactMechanismPurpose contactMechanismPurpose;

        public PartyPhoneResponse build (){
            Preconditions.checkState(phone != null, "phone not set");
            Preconditions.checkState(partyPhone != null, "partyPhone not set");

            return new PartyPhoneResponse(this);
        }

        public Builder withPartyPhone(PartyPhone partyPhone){
            this.partyPhone = partyPhone;
            return this;
        }

        public Builder withPhone(Phone phone){
            this.phone = phone;
            return this;
        }

        public Builder withContactMechanismPurpose(ContactMechanismPurpose purpose){
            this.contactMechanismPurpose = purpose;
            return this;
        }
    }
}
