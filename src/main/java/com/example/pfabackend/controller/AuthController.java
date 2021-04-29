package com.example.pfabackend.controller;

import com.example.pfabackend.dto.AuthenticationResponse;
import com.example.pfabackend.dto.LoginRequest;
import com.example.pfabackend.dto.RegisterRequest;
import com.example.pfabackend.model.VerificationToken;
import com.example.pfabackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequest registerRequest){
        authService.signUp(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successful", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse logIn(@RequestBody LoginRequest loginRequest){
        return authService.logIn(loginRequest);
    }

}
