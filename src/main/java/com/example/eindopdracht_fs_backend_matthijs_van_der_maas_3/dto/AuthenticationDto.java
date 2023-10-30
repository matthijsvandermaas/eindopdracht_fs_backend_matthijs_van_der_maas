package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto;

public class AuthenticationDto {
    private String username;
    private String password;

    // constructors, getters, and setters

    public AuthenticationDto() {
    }

    public AuthenticationDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
