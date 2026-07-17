package com.example.springvault.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    
    private final String SECRET = "mySecret";
    private final long EXPIRY = 3600000; // one hour in ms

    public String generateToken(String email) {
        return Jwts.builder()
        .setSubject(email)
        .setExpiration(new Date (System.currentTimeMillis() + EXPIRY))
        .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
        .compact();

    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
        .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject(); // subject is the email as set above

    }

    public boolean isTokenValid(String token) {
        try {
        Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
            .build()
            .parseClaimsJws(token);
        return true;
        } catch (Exception e) {
            return false;
        }
    }

}
