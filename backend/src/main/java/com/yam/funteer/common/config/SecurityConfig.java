package com.yam.funteer.common.config;

import com.yam.funteer.common.security.JwtProvider;
import com.yam.funteer.common.security.filter.JwtAuthFilter;
import com.yam.funteer.common.security.handler.OAuth2SuccessHandler;
import com.yam.funteer.common.security.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig{
    private final JwtProvider jwtProvider;
    private final OAuth2SuccessHandler successHandler;
    private final CustomOAuth2UserService oAuth2UserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 사용하니 session 생성 X
                .and()
            .authorizeRequests()
            .mvcMatchers(HttpMethod.POST, "/member", "/team").permitAll()
            .antMatchers("/admin", "/member", "/team").authenticated()
            .anyRequest().permitAll()
                .and()
            .cors()
            .and()
            .addFilterBefore(new JwtAuthFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
            // .oauth2Login()
            // .successHandler(successHandler) // oAuth 로그인 성공 시 동작할 핸들러
            // .userInfoEndpoint().userService(oAuth2UserService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedOrigin("https://localhost:3000");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(WebSecurity web) {
//        return (web) -> web.ignoring().antMatchers("/resources/**");
//    }
}
