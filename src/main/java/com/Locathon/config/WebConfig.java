package com.Locathon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://192.168.219.106:8081", "exp://192.168.219.106:8081") // Expo 주소 포함
            .allowedMethods("*")
            .allowedHeaders("*")
            .allowCredentials(true);
    }
}