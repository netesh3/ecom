package com.nik.code.ecom.dto.user;

public class SignInResponseDTO {
    private String status;
    private String name;
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SignInResponseDTO(String status, String name, String token) {
        this.status = status;
        this.name = name;
        this.token = token;
    }
}
