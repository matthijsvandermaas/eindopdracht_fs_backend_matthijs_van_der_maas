package com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ParticulierDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    public String First_Name;
    @NotBlank
    @Pattern(regexp = "^abAB$")
    @Size(min = 1, max = 128)
    public String Last_Name;

    @NotBlank
    @Email
    public String Email;

    @Size(min = 1, max = 128)
    @Pattern(regexp = "^([A-HK-PRSVWY][A-HJ-PR-Y])\\s?([0][2-9]|[1-9][0-9])\\s?[A-HJ-PR-Z]{3}$")
    public String User_Name;

    @Size(min = 1, max = 128)
    @Pattern(regexp = "^([A-HK-PRSVWY][A-HJ-PR-Y])\\s?([0][2-9]|[1-9][0-9])\\s?[A-HJ-PR-Z]{3}$")
    public String Password;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirst_Name() {
        return null;
    }

    public String getLast_Name() {
        return null;
    }

    public String getEmail() {
        return null;
    }

    public String getUser_Name() {
        return null;
    }

    public String getPassword() {
        return null;
    }
}
