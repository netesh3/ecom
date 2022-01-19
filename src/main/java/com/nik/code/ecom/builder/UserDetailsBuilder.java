package com.nik.code.ecom.builder;

import com.nik.code.ecom.model.*;

public class UserDetailsBuilder {
    private Address address;
    private AuthenticationToken token;

    public UserDetailsBuilder(AuthenticationToken token){
        this.token = token;
    }


    public UserDetailsBuilder withAddress(Address address){
        this.address = address;
        return this;
    }

    public UserDetails build(){
        return new UserDetails(this.address, this.token);
    }
}
