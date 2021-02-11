package com.ironhack.bankingsystem.model;

import com.ironhack.bankingsystem.classes.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class AccountHolder extends User{
    @Past
    private LocalDateTime birthDate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street",column=@Column(name="primary_street")),
            @AttributeOverride(name="postalCode",column=@Column(name="primary_postal_code")),
            @AttributeOverride(name="city",column=@Column(name="primary_city")),
            @AttributeOverride(name="country",column=@Column(name="primary_country"))
    })
    @NotNull
    private Address primaryAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street",column=@Column(name="mailing_street")),
            @AttributeOverride(name="postalCode",column=@Column(name="mailing_postal_code")),
            @AttributeOverride(name="city",column=@Column(name="mailing_city")),
            @AttributeOverride(name="country",column=@Column(name="mailing_country"))
    })
    private Address mailingAddress;

    public AccountHolder() {
    }

    public AccountHolder(String name, String username, String password, LocalDateTime birthDate, Address primaryAddress, Address mailingAddress) {
        super(name, username, password);
        setBirthDate(birthDate);
        setPrimaryAddress(primaryAddress);
        setMailingAddress(mailingAddress);
    }

    public AccountHolder(String name, String username, String password, LocalDateTime birthDate, Address primaryAddress) {
        super(name, username, password);
        setBirthDate(birthDate);
        setPrimaryAddress(primaryAddress);
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
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
