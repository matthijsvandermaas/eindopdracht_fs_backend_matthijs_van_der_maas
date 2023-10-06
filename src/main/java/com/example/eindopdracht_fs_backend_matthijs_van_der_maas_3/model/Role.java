package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
    @Table(name = "Roles")
    public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rolename")
    private String roleName;
    @ManyToMany
    @JoinTable(
            name = "particulieren_roles",
            joinColumns = @JoinColumn(name = "roles_id"),
            inverseJoinColumns = @JoinColumn(name = "particulieren_id")
    )
    private Set<Particulier> roles = new HashSet<>();


    public Role() {

    }

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;

    }

    public Set<Particulier> getRoles() {
        return roles;
    }

    public void setRoles(Set<Particulier> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}

