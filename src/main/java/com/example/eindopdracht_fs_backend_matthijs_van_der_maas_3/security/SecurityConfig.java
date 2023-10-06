package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig<PasswordEncoder> {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return (PasswordEncoder) new BCryptPasswordEncoder();
    }

    public String encode(String password) {
        return password;
    }
}
