package com.nik.code.ecom.service;

import com.nik.code.ecom.config.EmailConfig;
import com.nik.code.ecom.dto.user.SignUpResponseDTO;
import com.nik.code.ecom.enums.Status;
import com.nik.code.ecom.exceptions.UserException;
import com.nik.code.ecom.model.OTP;
import com.nik.code.ecom.model.User;
import com.nik.code.ecom.repository.OTPRepository;
import com.nik.code.ecom.repository.UserRepository;
import com.nik.code.ecom.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

@Service
public class OTPService {

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    public boolean sendOTPViaEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("No User found with this Email Id: " + email);
        }
        String otpNumber = generateOTP();
        OTP otpEntity = new OTP(otpNumber, user);
        String subject = "Verification OTP";
        String body = "Your 6-digit OTP is: " + otpNumber + " .\nThanks for visiting us.";
        boolean isSent = sendEmail(email, subject, body);
        if(isSent) {
            otpRepository.save(otpEntity);
        }
        return isSent;
    }

    public SignUpResponseDTO validateOTP(String email, String otpNumber) throws Exception {

        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new Exception("User doesn't exist");
        }
        OTP confirmationOTP = otpRepository.findByUserAndOTP(user, otpNumber);

        if(confirmationOTP == null){
            throw new IllegalStateException("Incorrect OTP");
        }

        if (confirmationOTP.getAuthenticatedAt() != null) {
            throw new IllegalStateException("OTP already authenticated");
        }

        LocalDateTime expiredAt = confirmationOTP.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("otp expired");
        }

        confirmationOTP.setAuthenticatedAt(LocalDateTime.now());

        final org.springframework.security.core.userdetails.UserDetails userDetail = userDetailsService
                .loadUserByUsername(user.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetail);
        String returnMessage;

        if(!user.isActivated()){
            user.setActivated(true);
            returnMessage = "user created successfully";

        }else{
            returnMessage = "user authenticated successfully";
        }
        otpRepository.save(confirmationOTP);
        return new SignUpResponseDTO(Status.SUCCESS.name(), returnMessage, user.getFirstName(), jwt);
    }

    private String generateOTP(){

        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    private boolean sendEmail(String to, String subject, String body){

        boolean sent = false;

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", emailConfig.getHost());
        properties.put("mail.smtp.port", emailConfig.getPort());
        properties.put("mail.smtp.ssl.enable", emailConfig.isSsl());
        properties.put("mail.smtp.auth", emailConfig.isAuth());


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
            }
        });

        MimeMessage content = new MimeMessage(session);

        try{
            content.setFrom(emailConfig.getUsername());
            content.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            content.setSubject(subject);
            content.setText(body);
            Transport.send(content);
            sent = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return sent;
    }

}
