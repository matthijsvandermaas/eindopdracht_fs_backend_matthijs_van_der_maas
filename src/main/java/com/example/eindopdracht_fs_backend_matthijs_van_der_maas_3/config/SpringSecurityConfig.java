package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.config;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.filter.JwtRequestFilter;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    public final CustomUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    // Je kunt dit ook in een aparte configuratie klasse zetten.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }

    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(authentication ->
                        authentication
                                .requestMatchers("/", "/productie_informatie", "/inschrijfformulier", "/inschrijfformulier_particulier", "/inschrijfformulier_producent", "/alle_bieren").permitAll()
                                .requestMatchers("/mijn_bieren", "/mijn_pagina").hasAnyRole("USER", "ADMIN", "BREWER")
                                .requestMatchers(HttpMethod.GET, "/inschrijfformulier_particulier").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/inschrijfformulier_particulier").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/mijn_bieren", "/mijn_pagina").hasAnyRole("USER", "ADMIN", "BREWER")
                                .requestMatchers(HttpMethod.GET, "/inschrijfformulier_particulier").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/inschrijfformulier_particulier").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/inschrijfformulier_particulier").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/inschrijfformulier_particulier").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/inschrijfformulier_particulier").hasAnyRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/inschrijfformulier_producent").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/inschrijfformulier_producent").hasAnyRole("BREWER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/inschrijfformulier_producent").hasAnyRole("BREWER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/inschrijfformulier_producent").hasAnyRole("BREWER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/inschrijfformulier_producent").hasAnyRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/inschrijfformulier_producent").hasAnyRole("USER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/inschrijfformulier_product").hasAnyRole("BREWER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/inschrijfformulier_product").hasAnyRole("BREWER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/inschrijfformulier_product").hasAnyRole("BREWER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/inschrijfformulier_product").hasAnyRole("ADMIN")
                                .requestMatchers("/login_page", "/inschrijfformulier",
                                        "/inschrijfformulier_particulier", "/inschrijfformulier_producent").permitAll()
                                .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
