package com.nik.code.ecom.dto.user;


import java.io.Serializable;

public class AuthenticationRequest implements Serializable {


    private String mobile;
    private String password;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //need default constructor for JSON Parsing
    public AuthenticationRequest()
    {

    }

    public AuthenticationRequest(String mobile, String password) {
        this.setMobile(mobile);
        this.setPassword(password);
    }
}