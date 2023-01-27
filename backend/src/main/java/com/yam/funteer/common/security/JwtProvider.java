package com.yam.funteer.common.security;

import com.yam.funteer.common.security.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

@Component
public class JwtProvider {

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

    private Claims parseClaims(String token){
        return Jwts.parser().setSigningKey(secretKey)
            .parseClaimsJws(token).getBody();
    }
    public boolean verifyToken(String token) {
        if(token == null) return false;
        try {
            return parseClaims(token).getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
            Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
    public String getEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
