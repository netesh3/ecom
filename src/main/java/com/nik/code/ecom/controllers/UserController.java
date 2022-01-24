package com.nik.code.ecom.controllers;

import com.nik.code.ecom.common.ApiResponse;
import com.nik.code.ecom.dto.user.*;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.exceptions.UserException;
import com.nik.code.ecom.service.CustomUserDetailService;
import com.nik.code.ecom.service.OTPService;
import com.nik.code.ecom.service.UserService;
import com.nik.code.ecom.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    OTPService otpService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignupDTO signupDto) throws UserException {
        userService.signUp(signupDto);
        Boolean sent = otpService.sendOTPViaEmail(signupDto.getEmail());
        if(sent){
            return new ResponseEntity<>(new ApiResponse(true, "Verify OTP sent on your email"), HttpStatus.GONE);
        }
        return new ResponseEntity<>(new ApiResponse(false, "OTP could not sent"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/signIn")
    public SignUpResponseDTO signInByPassword(@RequestBody SignInDTO signInDto) throws Exception {
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

    //TODO only for backend
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getMobile(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getMobile());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}