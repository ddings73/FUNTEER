package com.yam.funteer.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://i8e204.p.ssafy.io:3000", "http://localhost:3000")
                .allowedMethods("*")
                .allowCredentials(false)
                .maxAge(3000);
    }
}
