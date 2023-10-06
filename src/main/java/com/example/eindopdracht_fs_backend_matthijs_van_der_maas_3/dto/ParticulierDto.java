package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Set;

public class ParticulierDto {

    @NotBlank
    public String firstName;
    @NotBlank
    public String lastName;

    @NotBlank
    @Email
    public String email;

    @NotBlank
    public String userName;

   @NotBlank
    public String password;

    @NotEmpty
    public List<Role> roles = new List<>();


    public String encode(String password) {
        return password;
    }


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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}