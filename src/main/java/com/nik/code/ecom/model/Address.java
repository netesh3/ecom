package com.nik.code.ecom.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    @Column(name = "address_1")
    private String house;

    @NotNull
    @NotEmpty
    @Column(name = "street")
    private String street;

    @NotNull
    @NotEmpty
    @Column(name = "city")
    private String city;

    @NotNull
    @NotEmpty
    @Column(name = "state")
    private String state;

    @NotNull
    @NotEmpty
    @Column(name = "postal_code")
    private String postalCode;

    @NotNull
    @NotEmpty
    @Column(name = "country")
    private String country;

    @OneToOne(mappedBy = "address", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private UserDetails userDetails;

    public Address() {
    }

    public Address(String house, String street, String city, String state, String postalCode, String country) {
        this.house = house;
        this.street = street;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Integer getId() {
        return id;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postcode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
