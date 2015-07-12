package com.tchepannou.pds.dto;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.tchepannou.pds.enums.PartyKind;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

public class CreatePartyRoleRequest {
    //-- Attributes
    @NotBlank(message = "partyKind")
    @com.tchepannou.core.validator.Enum(enumClass = PartyKind.class, message="kind")
    @ApiModelProperty(allowableValues = "person, organization")
    private String partyKind;

    @NotBlank(message = "type")
    private String type;

    private String name;
    private String firstName;
    private String lastName;

    //-- Getter/Setter
    public String getPartyKind() {
        return partyKind;
    }

    public void setPartyKind(String kind) {
        this.partyKind = kind;
    }

    @NotBlank(message = "name")
    public String getName() {
        if (Strings.isNullOrEmpty(name)){
            name = Joiner
                    .on(' ')
                    .skipNulls()
                    .join(firstName, lastName);
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
