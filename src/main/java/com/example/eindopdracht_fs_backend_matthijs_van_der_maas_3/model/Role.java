package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


import java.util.List;


@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public String getRoleName() {
        return roleName;
    }
}
