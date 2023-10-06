package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto;

public class AuthenticationResponse {

    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}