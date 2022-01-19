package com.nik.code.ecom.builder;

import com.nik.code.ecom.dto.user.AddressDTO;
import com.nik.code.ecom.model.Address;

public class AddressBuilder {
    private String house;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public AddressBuilder() {
    }

    public AddressBuilder(AddressDTO address){
        this.house = address.getHouse();
        this.city = address.getCity();
        this.street = address.getStreet();
        this.country = address.getCountry();
        this.postalCode = address.getPostalCode();
        this.state = address.getState();
    }

    public Address build(){
        return new Address(this.house, this.street, this.city, this.state, this.postalCode, this.country);
    }
}
