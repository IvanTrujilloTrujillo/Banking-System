package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User{
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street",column=@Column(name="primary_street")),
            @AttributeOverride(name="number",column=@Column(name="primary_number")),
            @AttributeOverride(name="floor",column=@Column(name="primary_floor")),
            @AttributeOverride(name="door",column=@Column(name="primary_door")),
            @AttributeOverride(name="postalCode",column=@Column(name="primary_postal_code")),
            @AttributeOverride(name="city",column=@Column(name="primary_city")),
            @AttributeOverride(name="country",column=@Column(name="primary_country"))
    })
    @NotNull
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street",column=@Column(name="mailing_street")),
            @AttributeOverride(name="number",column=@Column(name="mailing_number")),
            @AttributeOverride(name="floor",column=@Column(name="mailing_floor")),
            @AttributeOverride(name="door",column=@Column(name="mailing_door")),
            @AttributeOverride(name="postalCode",column=@Column(name="mailing_postal_code")),
            @AttributeOverride(name="city",column=@Column(name="mailing_city")),
            @AttributeOverride(name="country",column=@Column(name="mailing_country"))
    })
    private Address mailingAddress;

    public AccountHolder() {
    }

    public AccountHolder(String name, String username, String password, Address primaryAddress, Address mailingAddress) {
        super(name, username, password);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }

    public AccountHolder(String name, String username, String password, Address primaryAddress) {
        super(name, username, password);
        setPrimaryAddress(primaryAddress);
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}