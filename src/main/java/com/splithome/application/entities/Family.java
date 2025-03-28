package com.splithome.application.entities;

import java.util.List;
import java.util.UUID;

public class Family {

    private UUID id;
    private String name;
    private List<User> members;
    private String familyCode;

    public Family(UUID id, String name, List<User> members, String familyCode) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.familyCode = familyCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }
}
