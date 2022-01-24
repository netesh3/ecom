package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.user.SignUpResponseDTO;
import com.nik.code.ecom.model.User;
import com.nik.code.ecom.repository.UserRepository;
import com.nik.code.ecom.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
public class OTPController {

    @Autowired
    OTPService otpService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/sendViaEmail")
    public ResponseEntity<ApiResponse> sendOTPViaEmail(@RequestParam String email) throws Exception{
        Boolean sent = otpService.sendOTPViaEmail(email);
        if(sent){
            return new ResponseEntity<>(new ApiResponse(true, "OTP sent successfully"), HttpStatus.GONE);
        }
        return new ResponseEntity<>(new ApiResponse(false, "OTP not sent"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/validate")
    public SignUpResponseDTO validateOTP(@RequestParam String email, @RequestParam String otp) throws Exception{
        return otpService.validateOTP(email, otp);
    }
}
