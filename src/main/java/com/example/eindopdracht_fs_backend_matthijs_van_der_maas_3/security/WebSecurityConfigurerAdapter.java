package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class WebSecurityConfigurerAdapter {
    protected abstract void configure(HttpSecurity http) throws Exception;

    protected AuthenticationManager authenticationManagerBean() {
        return authenticationManagerBean();
    }

    @Bean
    public abstract ParticulierDetailsService particulierDetailsService();

    public abstract BCryptPasswordEncoder bCryptPasswordEncoder();

    public abstract PasswordEncoder passwordEncoder();

    public abstract JwtRequestFilter customJwtRequestFilter();
}
