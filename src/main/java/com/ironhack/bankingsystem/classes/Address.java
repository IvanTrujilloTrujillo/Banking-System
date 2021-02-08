package com.ironhack.bankingsystem.classes;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private Integer number;
    private Integer floor;
    private String door;
    private String postalCode;
    private String city;
    private String country;

    public Address(String street, Integer number, Integer floor, String door, String postalCode, String city, String country) {
        setStreet(street);
        setNumber(number);
        setFloor(floor);
        setDoor(door);
        setPostalCode(postalCode);
        setCity(city);
        setCountry(country);
    }

    public Address(String street, Integer number, String postalCode, String city, String country) {
        setStreet(street);
        setNumber(number);
        setPostalCode(postalCode);
        setCity(city);
        setCountry(country);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
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
