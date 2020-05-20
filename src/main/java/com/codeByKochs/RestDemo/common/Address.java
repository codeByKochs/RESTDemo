package com.codeByKochs.RestDemo.common;

import java.util.UUID;

/**
 * Object used to store address information
 * empty constructor and getter methods are needed to serialize/deserialize address objects with jackson
 */

public class Address{
    private UUID id;
    private String name;
    private String street;
    private String city;
    private String zipcode;

    // empty constructor is needed to serialize objects with jackson
    public Address(){
        super();
    }

    public Address(UUID id, String name, String street, String city, String zipcode) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    public static UUID generateUUID(){ return UUID.randomUUID();}

    public void setId(UUID id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getZipcode() {
        return zipcode;
    }

    public String toString(){
        return String.format("name: %s, street: %s, city: %s, zipcode: %s", name, street, city, zipcode);
    }
}
