package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
@EnableWebSecurity
public class GlobalCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .exposedHeaders("Access-Control-Allow-Origin");
            }
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder encoder) {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

        UserDetails brouwer = User
                .withUsername("brouwer")
                .password(encoder.encode("password"))
                .roles("BREWER")
                .build();

        UserDetails liefhebber = User
                .withUsername("liefhebber")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .build();

        userDetailsManager.createUser(brouwer);
        userDetailsManager.createUser(liefhebber);
        userDetailsManager.createUser(admin);
        return userDetailsManager;
    }
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
            return http.build();

    }

}
