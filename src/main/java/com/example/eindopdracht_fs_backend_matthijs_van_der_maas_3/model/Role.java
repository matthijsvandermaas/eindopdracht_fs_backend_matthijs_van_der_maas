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


    @ManyToMany(mappedBy = "roles")
    private Set<Particulier> particulierRoles = new HashSet<>();

    @OneToMany
    private Set<Producent> producentRoles = new HashSet<>();



    public Role() {

    }

    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;

    }

    public Set<Particulier> getParticulierRoles() {
        return particulierRoles;
    }
    public Set<Producent> getProducentRoles() {
        return producentRoles;
    }

    public void setParticulierRoles(Set<Particulier> roles) {
        this.particulierRoles = roles;
    }
    public void setProducentRoles(Set<Producent> roles) {
        this.producentRoles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}

