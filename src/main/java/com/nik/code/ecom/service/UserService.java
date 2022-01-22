package com.nik.code.ecom.service;


import com.nik.code.ecom.builder.*;
import com.nik.code.ecom.config.AES256EncryptionAlgo;
import com.nik.code.ecom.constant.Message;
import com.nik.code.ecom.dto.user.*;
import com.nik.code.ecom.enums.Status;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.exceptions.UserException;
import com.nik.code.ecom.model.Address;
import com.nik.code.ecom.model.AuthenticationToken;
import com.nik.code.ecom.model.User;
import com.nik.code.ecom.model.UserDetails;
import com.nik.code.ecom.repository.UserDetailsRepository;
import com.nik.code.ecom.repository.UserRepository;
import com.nik.code.ecom.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public SignUpResponseDTO signUp(SignupDTO signupDto)  throws UserException {
        // Check to see if the current phone address has already been registered.
        if (Objects.nonNull(userRepository.findByMobile(signupDto.getMobile()))) {
            // If the phone address has been registered then throw an exception.
            throw new UserException("User already exists");
        }
        // first encrypt the password

        String encryptedPassword = AES256EncryptionAlgo.encrypt(signupDto.getPassword());
        signupDto.setPassword(encryptedPassword);

        try {

            // generate token for user
            final AuthenticationToken authenticationToken = new AuthenticationToken();

            UserDetails userDetails = new UserDetailsBuilder(authenticationToken).build();

            User user = new SignupBuilder(signupDto, userDetails).build();

            // save the User
            userRepository.save(user);

            final org.springframework.security.core.userdetails.UserDetails userDetail = userDetailsService
                    .loadUserByUsername(user.getMobile());

            final String jwt = jwtTokenUtil.generateToken(userDetail);

            return new SignUpResponseDTO(Status.SUCCESS.name(), "user created successfully", signupDto.getFirstName(), jwt);
        } catch (Exception e) {
            // handle signup error
            throw new UserException(e.getMessage());
        }
    }

    public SignInResponseDTO signIn(SignInDTO signInDto) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInDto.getMobile(), signInDto.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final org.springframework.security.core.userdetails.UserDetails userDetails = userDetailsService
                .loadUserByUsername(signInDto.getMobile());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new SignInResponseDTO(Status.SUCCESS.name(), signInDto.getMobile(), jwt);
    }

    public Boolean saveAddress(String token, AddressDTO addressDTO) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        Address address = new AddressBuilder(addressDTO).build();
        userDetails.setAddress(address);
        userDetailsRepository.save(userDetails);
        return true;
    }

    public Boolean hasAddress(String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        UserDetails userDetails = authenticationService.getUserDetails(token);
        return Objects.nonNull(userDetails.getAddress());
    }


}
