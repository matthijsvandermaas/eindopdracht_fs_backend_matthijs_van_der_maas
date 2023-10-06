package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig<PasswordEncoder> {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/particulieren/**").permitAll() // Sta GET-verzoeken toe op /particulieren/**
//                .antMatchers(HttpMethod.POST, "/particulieren/**").authenticated() // Sta alleen geauthenticeerde POST-verzoeken toe op /particulieren/**
//                .anyRequest().authenticated() // Alle andere verzoeken vereisen authenticatie
//                .and()
//                .httpBasic(); // Gebruik HTTP Basic Authentication
//    }
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return (PasswordEncoder) new BCryptPasswordEncoder();
    }

    public String encode(String password) {
        return password;
    }
}
