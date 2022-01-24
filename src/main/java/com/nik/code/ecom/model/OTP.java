package com.nik.code.ecom.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "authentication_otp")
public class OTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String otp;
    private LocalDateTime generatedAt;
    private LocalDateTime expiresAt;
    private LocalDateTime authenticatedAt;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public OTP(String otp, User user) {
        this.otp = otp;
        this.user = user;
        this.generatedAt = LocalDateTime.now();
        this.expiresAt = LocalDateTime.now().plusMinutes(15);
    }

    public OTP() {

    }

    public Integer getId() {
        return id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getAuthenticatedAt() {
        return authenticatedAt;
    }

    public void setAuthenticatedAt(LocalDateTime authenticatedAt) {
        this.authenticatedAt = authenticatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
