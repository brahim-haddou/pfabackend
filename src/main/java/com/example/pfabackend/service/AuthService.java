package com.example.pfabackend.service;

import com.example.pfabackend.dto.RegisterRequest;
import com.example.pfabackend.exceptions.SpringPfaException;
import com.example.pfabackend.model.NotificationEmail;
import com.example.pfabackend.model.User;
import com.example.pfabackend.model.VerificationToken;
import com.example.pfabackend.repository.UserRepository;
import com.example.pfabackend.repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signUp(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(true);
        userRepository.save(user);
        String token = generateVerificationToken(user);
        // TODO : send mail to enable account

        // mailService.sendMail(new NotificationEmail(
        // "Please Activate Your Account",
        // "thank you for signing up to PFA",
        // "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
//        verificationToken.setExpiryDate();
        verificationTokenRepository.save(verificationToken);

        return token;

    }
}
