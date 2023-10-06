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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class GlobalCorsConfig {

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

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
            }
        };
    }
}
