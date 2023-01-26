package com.yam.funteer.common.security.service;

import com.yam.funteer.common.security.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private final String secretKey;

    public TokenService(@Value("${jwt.secret}") String secretKey) {
        this.secretKey = secretKey;
    }

    public Token generateToken(String email, String role) {
        long tokenPeriod = 1000L * 60L * 10L; // 10분
        long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role);

        Date now = new Date();
        return new Token(
                Jwts.builder() // accessToken
                        .setClaims(claims) // private claim
                        .setIssuedAt(now) // 발급날짜
                        .setExpiration(new Date(now.getTime() + tokenPeriod)) // 만료기간
                        .signWith(SignatureAlgorithm.HS512, secretKey) // signature
                        .compact(),
                Jwts.builder() // refreshToken
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime() + refreshPeriod))
                        .signWith(SignatureAlgorithm.HS512, secretKey)
                        .compact());
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
