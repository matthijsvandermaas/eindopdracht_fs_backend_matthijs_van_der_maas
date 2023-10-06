package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public abstract class WebSecurityConfigurerAdapter {
    protected abstract void configure(HttpSecurity http) throws Exception;

    protected AuthenticationManager authenticationManagerBean() {
        return authenticationManagerBean();
    }
}
