package com.nik.code.ecom.service;


import com.nik.code.ecom.constant.Message;
import com.nik.code.ecom.dto.user.SignInDto;
import com.nik.code.ecom.dto.user.SignInResponseDto;
import com.nik.code.ecom.dto.user.SignUpResponseDto;
import com.nik.code.ecom.dto.user.SignupDto;
import com.nik.code.ecom.enums.Status;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.exceptions.UserException;
import com.nik.code.ecom.model.AuthenticationToken;
import com.nik.code.ecom.model.User;
import com.nik.code.ecom.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


    Logger logger = LoggerFactory.getLogger(UserService.class);

    public SignUpResponseDto signUp(SignupDto signupDto)  throws UserException {
        // Check to see if the current phone address has already been registered.
        if (Objects.nonNull(userRepository.findByMobile(signupDto.getMobile()))) {
            // If the phone address has been registered then throw an exception.
            throw new UserException("User already exists");
        }
        // first encrypt the password
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }

        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), signupDto.getMobile(), encryptedPassword );
        try {
            // save the User
            userRepository.save(user);
            // generate token for user
            final AuthenticationToken authenticationToken = new AuthenticationToken(user);
            // save token in database
            authenticationService.saveConfirmationToken(authenticationToken);
            // success in creating
            return new SignUpResponseDto(Status.SUCCESS.name(), "user created successfully", signupDto.getFirstName(), authenticationToken.getToken());
        } catch (Exception e) {
            // handle signup error
            throw new UserException(e.getMessage());
        }
    }

    String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

    public SignInResponseDto signIn(SignInDto signInDto) throws AuthenticationFailException, UserException {
        // first find User by email
        User user = userRepository.findByMobile(signInDto.getMobile());
        if(!Objects.nonNull(user)){
            throw new AuthenticationFailException("user not present");
        }
        try {
            // check if password is right
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                // passwords do not match
                throw  new AuthenticationFailException(Message.WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new UserException(e.getMessage());
        }

        AuthenticationToken token = authenticationService.getToken(user);

        if(!Objects.nonNull(token)) {
            // token not present
            throw new UserException(Message.AUTH_TOKEN_NOT_PRESENT);
        }

        return new SignInResponseDto (Status.SUCCESS.name(), user.getFirstName(), token.getToken());
    }
}
