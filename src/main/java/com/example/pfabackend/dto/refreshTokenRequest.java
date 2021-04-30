package com.example.pfabackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class refreshTokenRequest {
    private String refreshToken;
    private String username;
}
