package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.config;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.filter.JwtRequestFilter;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.CustomeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    // PasswordEncoderBean.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService UserDetailsService(PasswordEncoder Encoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails liefhebber = User
                .withUsername("liefhebber")
                .password(Encoder.encode("password"))
                .roles("USER")
                .build();
        manager.createUser(liefhebber);

        UserDetails brouwer = User
                .withUsername("brouwer")
                .password(Encoder.encode("password"))
                .roles("BREWER")
                .build();
        manager.createUser(liefhebber);
        manager.createUser(brouwer);
        UserDetails admin = User
         .withUsername("admin")
                .password(Encoder.encode("password"))
                .roles("ADMIN")
                .build();
        manager.createUser(admin);
        return manager;
    }

    public final CustomeUserDetailsService customUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomeUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    // Authenticatie met customUserDetailsService en passwordEncoder
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }


}
