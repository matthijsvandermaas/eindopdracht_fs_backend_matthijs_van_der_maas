package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;







@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtService jwtService;
    private final ParticulierRepository particulierRepository;

    public SecurityConfig(JwtService jwtService, ParticulierRepository particulierRepository) {
        this.jwtService = jwtService;
        this.particulierRepository = particulierRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(customJwtRequestFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "/productie_informatie", "/inschrijfformulier",
                        "/inschrijfformulier_particulier", "/inschrijfformulier_producent").permitAll()
                .antMatchers("/mijn_bieren", "/mijn_pagina").hasAnyRole("USER", "ADMIN", "BREWER")
                .antMatchers(HttpMethod.POST, "/inschrijfformulier_particulier").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/inschrijfformulier_particulier").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/inschrijfformulier_particulier").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/inschrijfformulier_particulier").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/inschrijfformulier_producent").hasAnyRole("BREWER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/inschrijfformulier_producent").hasAnyRole("BREWER", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/inschrijfformulier_producent").hasAnyRole("BREWER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/inschrijfformulier_producent").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/inschrijfformulier_product").hasAnyRole("BREWER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/inschrijfformulier_product").hasAnyRole("BREWER", "ADMIN")
                .antMatchers(HttpMethod.PATCH, "/inschrijfformulier_product").hasAnyRole("BREWER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/inschrijfformulier_product").hasAnyRole("ADMIN")
                .antMatchers("/login_page", "/inschrijfformulier",
                        "/inschrijfformulier_particulier", "/inschrijfformulier_producent").permitAll()
                .anyRequest().denyAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable();
    }




    @Bean
    @Override
    public ParticulierDetailsService particulierDetailsService() {
        return new ParticulierDetailsService(particulierRepository);
    }

    @Override
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public JwtRequestFilter customJwtRequestFilter() {
        return new JwtRequestFilter(jwtService, particulierDetailsService());
    }
}
