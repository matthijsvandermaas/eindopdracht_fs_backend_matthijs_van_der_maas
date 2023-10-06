package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Authority;
import jakarta.validation.constraints.Email;


import java.util.List;

public class ParticulierDto {


    public String firstName;

    public String lastName;


    @Email
    public String email;


    public String userName;


    public String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
        return getAuthority();
    }
}