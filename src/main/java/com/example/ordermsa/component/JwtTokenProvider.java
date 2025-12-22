package com.example.ordermsa.component;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String secretKey = "very-secret-key-very-secret-key";
    private final long expirationMs = 1000 * 60 * 60; // 1시간

    public String createToken(Long userId, String role) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
