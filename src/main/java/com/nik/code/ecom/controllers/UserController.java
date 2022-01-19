package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.user.*;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.exceptions.UserException;
import com.nik.code.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/address")
    public ResponseEntity<ApiResponse> saveAddress(@RequestParam String token, @RequestBody AddressDTO addressDTO)  throws UserException, AuthenticationFailException{
        Boolean isSaved = userService.saveAddress(token, addressDTO);
        if(!isSaved){
            return new ResponseEntity<>(new ApiResponse(false, "Couldn't updated"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(true, "Address upated successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/hasAddress")
    public ResponseEntity<ApiResponse> hasAddress(@RequestParam String token)  throws UserException, AuthenticationFailException{
        Boolean hasAddress = userService.hasAddress(token);
        if(!hasAddress){
            return new ResponseEntity<>(new ApiResponse(false, "Address doesn't exist"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(true, "Address Exists"), HttpStatus.CREATED);
    }

}