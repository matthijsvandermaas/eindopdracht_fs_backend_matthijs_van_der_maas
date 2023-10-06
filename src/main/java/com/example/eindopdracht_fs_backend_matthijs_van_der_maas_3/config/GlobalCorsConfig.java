package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Voeg hier je front-end URL toe
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

}
