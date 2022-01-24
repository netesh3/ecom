package com.nik.code.ecom.service;

import com.nik.code.ecom.config.AES256EncryptionAlgo;
import com.nik.code.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.nik.code.ecom.model.User user = userRepository.findByEmail(email);
        String userName = user.getEmail();
        String password = AES256EncryptionAlgo.decrypt(user.getPassword());
        return new User(userName, password, new ArrayList<>());
    }
}
