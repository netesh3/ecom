package com.nik.code.ecom.repository;

import com.nik.code.ecom.model.OTP;
import com.nik.code.ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Integer> {
    @Query("from OTP where otp = :otp and user = :user")
    OTP findByUserAndOTP(User user, String otp);
}