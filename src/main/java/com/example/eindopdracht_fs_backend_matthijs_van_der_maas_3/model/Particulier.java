package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "particulieren")
public class Particulier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String first_Name;

    @NotBlank
    private String last_Name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String user_Name;

    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    public Particulier(String first_Name, String last_Name, String email, String user_Name, String password) {
        this.first_Name = first_Name;
        this.last_Name = last_Name;
        this.email = email;
        this.user_Name = user_Name;
        this.password = password;
    }
    // Lege constructor nodig voor JPA
    public Particulier() {

    }



    // Getter- en setter-methoden hier

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
