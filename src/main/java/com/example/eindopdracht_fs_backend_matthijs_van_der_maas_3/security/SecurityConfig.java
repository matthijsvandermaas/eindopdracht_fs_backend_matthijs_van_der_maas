package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtService jwtService;
    private final ParticulierRepository particulierRepository;

    public SecurityConfig(JwtService service, ParticulierRepository particulierRepository) {
        this.jwtService = service;
        this.particulierRepository = particulierRepository;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, ParticulierDetailsService pdService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .particulierDetailsService(pdService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(ParticulierDetailsService pdService, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setParticulierDetailsService(pdService);
        return new ProviderManager(auth);
    }
    @Bean
    public ParticulierDetailsService particulierDetailsService() {
        return new MyUserDetailsService(this.userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //TOTDO fixen
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/").permitall()
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers("/secret").hasRole("ADMIN")
                        .requestMatchers("/hello").authenticated()
                        .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new nl.novi.les16jwt.security.JwtRequestFilter(Service, particulierDetailsService()), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}



