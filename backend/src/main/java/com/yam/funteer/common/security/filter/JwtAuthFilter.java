package com.yam.funteer.common.security.filter;

import com.yam.funteer.common.security.service.TokenService;
import com.yam.funteer.user.UserType;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {

    private final TokenService tokenService;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader("Auth");
        if(tokenService.verifyToken(token)){
            String email = tokenService.getEmail(token);

            Authentication auth = new UsernamePasswordAuthenticationToken(null, "",
                    Arrays.asList(new SimpleGrantedAuthority(UserType.ROLES.USER)));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
