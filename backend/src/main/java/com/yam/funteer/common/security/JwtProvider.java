package com.yam.funteer.common.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yam.funteer.common.code.UserType;
import com.yam.funteer.user.dto.response.TokenInfo;
import com.yam.funteer.user.entity.User;
import io.jsonwebtoken.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Component @Slf4j
@RequiredArgsConstructor
public class JwtProvider {
    private String secretKey = "jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk";
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private final long accessPeriod = 1000L * 60L * 60L; // 10분
    private final long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;
    @PostConstruct
    protected void init(){
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }

    public TokenInfo generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities  = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();

        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities) // 권한
                .setExpiration(new Date(now.getTime() + accessPeriod)) // 만료기간
                .signWith(SignatureAlgorithm.HS512, secretKey) // 서명
                .compact();
        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(now.getTime() + refreshPeriod)) // 만료기간
                .signWith(SignatureAlgorithm.HS512, secretKey) // 서명
                .compact();

        return TokenInfo.of(BEARER_TYPE, accessToken, refreshToken);
    }

    public TokenInfo generateTokenForOAuth(String userId){
        String authorities = UserType.KAKAO.getAuthority();

        Date now = new Date();

        String accessToken = Jwts.builder()
            .setSubject(userId)
            .claim(AUTHORITIES_KEY, authorities) // 권한
            .setExpiration(new Date(now.getTime() + accessPeriod)) // 만료기간
            .signWith(SignatureAlgorithm.HS512, secretKey) // 서명
            .compact();
        String refreshToken = Jwts.builder()
            .setSubject(userId)
            .setExpiration(new Date(now.getTime() + refreshPeriod)) // 만료기간
            .signWith(SignatureAlgorithm.HS512, secretKey) // 서명
            .compact();

        return TokenInfo.of(BEARER_TYPE, accessToken, refreshToken);
    }


    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new JwtException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    // claim 파싱
    private Claims parseClaims(String accessToken){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(accessToken)
                .getBody();
    }
}
