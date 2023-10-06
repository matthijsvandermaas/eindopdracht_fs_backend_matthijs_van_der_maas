package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.config;


import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.Security.JwtRequestFilter;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.Security.JwtService;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.Security.UserDetailsService;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
private JwtRequestFilter jwtRequestFilter;
    private JwtService jwtService;
    private UserRepository userRepository;

    public void SecurityConfig(JwtService jwtservice, UserRepository userRepository) {
        this.jwtService = jwtservice;
        this.userRepository = userRepository;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    public SpringSecurityConfig(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Bean
    public AuthenticationManager authenticationManager(org.springframework.security.core.userdetails.UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(userDetailsService());
        return new ProviderManager(auth);
    }

    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
        return new UserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // Authorizatie met jwt
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authentication -> authentication
                        .requestMatchers("/", "/productie_informatie", "/inschrijfformulier", "/inschrijfformulier_particulier", "/inschrijfformulier_producent").permitAll()
                        .requestMatchers( "/mijn_pagina").hasAnyRole("USER", "ADMIN", "BREWER")
                        .requestMatchers("/mijn_bieren", "/mijn_pagina").hasAnyRole("USER", "ADMIN", "BREWER")
                        .requestMatchers("/login_page", "/inschrijfformulier").permitAll()
                        .requestMatchers("/authenticate").permitAll()
                        .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
