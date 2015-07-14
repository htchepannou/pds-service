package com.tchepannou.pds.dto;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.tchepannou.pds.enums.Gender;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class PartyRequest {
    //-- Attributes
    private  String name;
    private  String firstName;
    private  String lastName;

    private Date birthDate;

    @NotBlank(message = "gender")
    @com.tchepannou.core.validator.Enum(enumClass = Gender.class, message="gender")
    @ApiModelProperty(allowableValues = "male, female")
    private  String gender;

    private  int heigth;
    private  int weight;
    private  String prefix;
    private  String suffix;


    //-- Getter/Setter
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeigth() {
        return heigth;
    }

    public void setHeigth(int heigth) {
        this.heigth = heigth;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
