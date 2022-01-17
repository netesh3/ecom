package com.nik.code.ecom.dto.user;

public class SignUpResponseDTO {
    private String status;
    private String message;
    private String name;
    private String token;

    public SignUpResponseDTO() {
    }

    public SignUpResponseDTO(String status, String message, String name, String token) {
        this.status = status;
        this.message = message;
        this.name = name;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
