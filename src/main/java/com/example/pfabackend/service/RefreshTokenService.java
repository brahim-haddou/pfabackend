package com.example.pfabackend.service;

import com.example.pfabackend.exceptions.SpringPfaException;
import com.example.pfabackend.model.RefreshToken;
import com.example.pfabackend.repository.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken(){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }

    public void validateRefreshToken(String token){
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringPfaException("Invalid refresh Token"));
    }
    public void deleteRefreshToken(String token){
        refreshTokenRepository.deleteByToken(token);
    }

}
