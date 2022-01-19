package com.nik.code.ecom.builder;

import com.nik.code.ecom.dto.user.SignupDTO;
import com.nik.code.ecom.model.User;
import com.nik.code.ecom.model.UserDetails;

public class SignupBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;
    private UserDetails userDetails;

    public SignupBuilder(SignupDTO user, UserDetails userDetails){
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.mobile = user.getMobile();
        this.password = user.getPassword();
        this.userDetails = userDetails;
    }

    public User build(){
        return new User(this.firstName, this.lastName, this.email, this.mobile, this.password, this.userDetails);
    }

}
