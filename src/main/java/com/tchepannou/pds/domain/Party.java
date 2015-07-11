package com.tchepannou.pds.domain;

import com.google.common.base.Joiner;
import com.tchepannou.core.domain.Persistent;
import com.tchepannou.pds.enums.Gender;
import com.tchepannou.pds.enums.PartyKind;

import java.util.Date;

public class Party extends Persistent {
    //-- Attribute
    private PartyKind kind;
    private String name;
    private String firstName;
    private String lastName;
    private String prefix;
    private String suffix;
    private Date birthDate;
    private Gender gender;
    private int heigth;
    private int weight;
    private Date fromDate;

    //-- Getter/Setter
    public PartyKind getKind() {
        return kind;
    }

    public void setKind(PartyKind kind) {
        this.kind = kind;
    }

    public String getName() {
        if (this.name == null && PartyKind.PERSON.equals(this.kind)) {
            this.name = Joiner
                    .on(' ')
                    .skipNulls()
                    .join(this.firstName, this.lastName);
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
}
