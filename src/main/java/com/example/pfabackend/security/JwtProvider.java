package com.example.pfabackend.security;


import com.example.pfabackend.exceptions.SpringPfaException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static java.util.Date.from;

@Service
public class JwtProvider {

    private KeyStore keyStore;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;


    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = this.getClass().getResourceAsStream("/springpfa.jks");
            keyStore.load(resourceAsStream, "brahim10".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SpringPfaException("Exception occurred while loading keystore", e);
        }

    }

    public String generateToken(Authentication authentication){

        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();

    }
    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }


    public boolean validateToken(String jwt) {
            Jwts.parser().setSigningKey(getPublickey()).parse(jwt);
            return false;
    }


    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getPublickey())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springpfa", "brahim10".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringPfaException("Exception occurred while retrieving public key from keystore", e);
        }
    }
    private PublicKey getPublickey() {
        try {
            return keyStore.getCertificate("springpfa").getPublicKey();
        } catch (KeyStoreException e) {
            throw new SpringPfaException("Exception occurred while " +
                    "retrieving public key from keystore", e);
        }
    }
    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
