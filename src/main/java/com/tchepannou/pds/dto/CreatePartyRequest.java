package com.tchepannou.pds.dto;

import com.tchepannou.pds.enums.PartyKind;
import org.hibernate.validator.constraints.NotBlank;

public class CreatePartyRequest extends PartyRequest {
    //-- Attributes
    @NotBlank(message = "kind")
    @com.tchepannou.core.validator.Enum(enumClass = PartyKind.class, message="kind")
    private String kind;


    //-- Getter/Setter
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
