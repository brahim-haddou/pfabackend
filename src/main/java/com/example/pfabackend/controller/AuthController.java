package com.example.pfabackend.controller;

import com.example.pfabackend.dto.RegisterRequest;
import com.example.pfabackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
