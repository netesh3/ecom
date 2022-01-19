package com.nik.code.ecom.service;


import com.nik.code.ecom.constant.Message;
import com.nik.code.ecom.exceptions.AuthenticationFailException;
import com.nik.code.ecom.model.AuthenticationToken;
import com.nik.code.ecom.model.User;
import com.nik.code.ecom.model.UserDetails;
import com.nik.code.ecom.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository repository;

    // save the confirmation token
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        repository.save(authenticationToken);
    }

    // get token of the User
    public AuthenticationToken getToken(User user) {
        return user.getUserDetails().getToken();
    }

    // get User from the token
    public User getUser(String token) {
        AuthenticationToken authenticationToken = repository.findTokenByToken(token);
        if (Objects.nonNull(authenticationToken)) {
            if (Objects.nonNull(authenticationToken.getUserDetails())) {
                return authenticationToken.getUserDetails().getUser();
            }
        }
        return null;
    }

    // get User Details from the token
    public UserDetails getUserDetails(String token) {
        AuthenticationToken authenticationToken = repository.findTokenByToken(token);
        if (Objects.nonNull(authenticationToken)) {
            if (Objects.nonNull(authenticationToken.getUserDetails())) {
                return authenticationToken.getUserDetails();
            }
        }
        return null;
    }

    // check if the token is valid
    public void authenticate(String token) throws AuthenticationFailException {
        if (!Objects.nonNull(token)) {
            throw new AuthenticationFailException(Message.AUTH_TOKEN_NOT_PRESENT);
        }
        if (!Objects.nonNull(getUser(token))) {
            throw new AuthenticationFailException(Message.AUTH_TOKEN_NOT_VALID);
        }
    }
}