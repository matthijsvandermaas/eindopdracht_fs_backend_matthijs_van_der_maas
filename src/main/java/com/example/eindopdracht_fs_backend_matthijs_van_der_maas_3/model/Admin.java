package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String userName;

    @NotBlank
    private String password;

 @ManyToMany(fetch =  FetchType.EAGER)
    private List<Authority> authorities;

    public static boolean isEnabled() {
        return isEnabled();
    }


    // Getters, setters
    public Long getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
    public List<Authority> getAuthorities() {
        return authorities;
    }

}
