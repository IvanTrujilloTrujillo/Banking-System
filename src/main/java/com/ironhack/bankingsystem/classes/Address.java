package com.ironhack.bankingsystem.classes;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String postalCode;
    private String city;
    private String country;

    public Address(String street, String postalCode, String city, String country) {
        setStreet(street);
        setPostalCode(postalCode);
        setCity(city);
        setCountry(country);
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
