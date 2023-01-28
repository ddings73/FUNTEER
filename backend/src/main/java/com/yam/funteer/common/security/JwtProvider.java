package com.yam.funteer.common.security;


import com.yam.funteer.user.entity.User;
import io.jsonwebtoken.*;

import io.jsonwebtoken.security.SignatureException;
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
public class JwtProvider {

    private String secretKey = "jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk-jwt-E204-funteerbuk";

    private final long accessPeriod = 1000L * 60L * 60L; // 10분
    private final long refreshPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;
    @PostConstruct
    protected void init(){
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }

    public Token generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities  = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = new Date().getTime();

        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName()) // 이름으로 서명
                .claim("auth", authorities) // 권한
                .claim("id", user.getId())
                .setExpiration(new Date(now + accessPeriod)) // 만료기간
                .signWith(SignatureAlgorithm.HS512, secretKey) // 서명
                .compact();
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + refreshPeriod)) // 만료기간
                .signWith(SignatureAlgorithm.HS512, secretKey) // 서명
                .compact();

        return new Token(accessToken, refreshToken);
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
        UserDetails principal = new org.springframework.security.core.userdetails.User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }


    // claim 파싱
    private Claims parseClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    // 토큰 검증
    public boolean verifyToken(String token) {
        if(token == null) return false;
        return parseClaims(token).getExpiration()
                    .after(new Date());
    }


    public boolean verifyById(Long id, String bearerToken){
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            String token = resolveToken(bearerToken);
            Integer tkId = (Integer) parseClaims(token).get("id");

            return id.longValue() == tkId.longValue();
        }

        return false;
    }

    public String resolveToken(String bearerToken){
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            String token = bearerToken.substring(7);
            try {
                verifyToken(token);
                return token;
            } catch (IllegalArgumentException e) {
                log.error("an error occured during getting username from token", e);
                // JwtException (custom exception) 예외 발생시키기
                throw new JwtException("유효하지 않은 토큰");
            } catch (ExpiredJwtException e) {
                log.warn("the token is expired and not valid anymore", e);
                throw new JwtException("토큰 기한 만료");
            } catch(SignatureException e){
                log.error("Authentication Failed. Username or Password not valid.");
                throw new JwtException("사용자 인증 실패");
            }
        }
        return null;
    }
}
