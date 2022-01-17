package com.nik.code.ecom.controllers;

import com.nik.code.ecom.dto.user.SignInDto;
import com.nik.code.ecom.dto.user.SignInResponseDto;
import com.nik.code.ecom.dto.user.SignUpResponseDto;
import com.nik.code.ecom.dto.user.SignupDto;
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
    public SignUpResponseDto Signup(@RequestBody SignupDto signupDto) throws UserException {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto Signup(@RequestBody SignInDto signInDto) throws UserException, AuthenticationFailException {
        return userService.signIn(signInDto);
    }

}