package com.app.MyeFamilyDoctor.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private String secretKey = "secret";

    public String generateToken(String username) {
        long expirationTime = 1000 * 60 * 30; // Χρόνος λήξης 30 λεπτά
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Ορίζουμε τον χρόνο λήξης
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
