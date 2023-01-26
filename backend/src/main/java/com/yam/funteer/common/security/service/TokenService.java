package com.yam.funteer.common.security.service;

import com.yam.funteer.common.security.Token;
import com.yam.funteer.user.UserType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;

@Service
public class TokenService {

    private String secretKey = "jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk";

    private final long accessPeriod = 1000L * 60L * 10L; // 10분
    private final long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;
    @PostConstruct
    protected void init(){
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }

    public Token generateToken(String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);

        claims.put("role", role); // 권한

        String accessToken = createToken(claims, accessPeriod);
        String refreshToken = createToken(claims, refreshPeriod);

        return new Token(accessToken, refreshToken);
    }
    
    public String createToken(Claims claims, long period){
        Date now = new Date();
        return Jwts.builder()
            .setClaims(claims) // private claim
            .setIssuedAt(now) // 발급 날짜
            .setExpiration(new Date(now.getTime() + period)) // 만료기간
            .signWith(SignatureAlgorithm.HS512, secretKey) // 서명
            .compact();
    }

    public boolean verifyToken(String token) {
        if(token == null) return false;
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token);
            return claims.getBody().getExpiration()
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
