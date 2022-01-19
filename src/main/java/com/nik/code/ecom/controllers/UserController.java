package com.nik.code.ecom.controllers;

import com.nik.code.ecom.dto.user.SignInDTO;
import com.nik.code.ecom.dto.user.SignInResponseDTO;
import com.nik.code.ecom.dto.user.SignUpResponseDTO;
import com.nik.code.ecom.dto.user.SignupDTO;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.exceptions.UserException;
import com.nik.code.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public SignUpResponseDTO signUp(@RequestBody SignupDTO signupDto) throws UserException {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDTO signIn(@RequestBody SignInDTO signInDto) throws UserException, AuthenticationFailException {
        return userService.signIn(signInDto);
    }

    //addAddress
    //updateAddress

}