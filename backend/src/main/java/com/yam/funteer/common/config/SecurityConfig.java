package com.yam.funteer.common.config;

import com.yam.funteer.common.security.handler.OAuth2SuccessHandler;
import com.yam.funteer.common.security.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig{

    private final OAuth2SuccessHandler successHandler;
    private final CustomOAuth2UserService oAuth2UserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .logout().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable() // csrf 공격관련 옵션 비활성
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // JWT 사용하니 session 생성 X

        http
                .authorizeRequests()
                    .anyRequest().permitAll(); // 임시

        http
                .oauth2Login()
                    .successHandler(successHandler) // oAuth 로그인 성공 시 동작할 핸들러
                    .userInfoEndpoint().userService(oAuth2UserService);
        // ...
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(WebSecurity web) {
//        return (web) -> web.ignoring().antMatchers("/resources/**");
//    }
}
