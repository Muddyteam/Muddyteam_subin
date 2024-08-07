package com.example.muddyteam_subin.security;

import com.example.muddyteam_subin.common.property.JwtProperty;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final JwtProperty jwtProperty;

    public JwtUtil(JwtProperty jwtProperty) {
        this.jwtProperty = jwtProperty;
        secretKey = new SecretKeySpec(jwtProperty.getSecret().getBytes(
            StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getToken(String authHeader){
        return authHeader.split(" ")[1];
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
            .get("role", String.class);
    }

    public Long getUserId(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
            .get("user_id", Long.class);
    }

    public boolean isExpired(String token) throws IOException {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
            .getExpiration().before(new Date());
    }

    public String createAccessToken(Long userId, String role) {
        return Jwts.builder()
            .claim("user_id", userId)
            .claim("role", role)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + jwtProperty.getAccess().getExpiration()))
            .signWith(secretKey)
            .compact();
    }

    public String createRefreshToken(Long userId, String role){
        return Jwts.builder()
            .claim("user_id", userId)
            .claim("role", role)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + jwtProperty.getRefresh().getExpiration()))
            .signWith(secretKey)
            .compact();
    }
}