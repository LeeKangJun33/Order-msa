package com.example.ordermsa.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey = "very-secret-key-very-secret-key";
    private final long EXPIRATION = 1000 * 60 * 60; // 1시간

   public String generateToken(String username){
       return Jwts.builder()
               .setSubject(username)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
               .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
               .compact();
   }

   public String getUsername(String token){
    return Jwts.parserBuilder()
            .setSigningKey(secretKey.getBytes())
            .build()
            .parseClaimsJwt(token)
            .getBody()
            .getSubject();
   }

   public boolean validateToken(String token){
       try {
           Jwts.parserBuilder()
                   .setSigningKey(secretKey.getBytes())
                   .build()
                   .parseClaimsJwt(token);
           return true;
       }catch (Exception e){
           return false;
       }
   }
}
