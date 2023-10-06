package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Authority;
import jakarta.validation.constraints.Email;

import java.util.List;

public class AdminDto {

    public String userName;


    public String password;
    private List<Authority> authority;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Authority> getAuthority() {
        return authority;
    }

    public void setAuthority(List<Authority> authority) {
        this.authority = authority;
    }
}