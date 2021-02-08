package com.ironhack.bankingsystem.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ThirdParty extends User{
    @NotEmpty
    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(String name, String username, String password, String hashedKey) {
        super(name, username, password);
        setHashedKey(hashedKey);
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
