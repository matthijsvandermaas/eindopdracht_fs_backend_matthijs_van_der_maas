package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model;

import jakarta.persistence.*;

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
    private Set<Particulier> particulierRoles;

    @OneToMany
    private Set<Producent> producentRoles;



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



    public void setId(Long id) {
        this.id = id;
    }

    public void setParticulierRoles(Set<Particulier> particulierRoles) {
        this.particulierRoles = particulierRoles;
    }

    public void setProducentRoles(Set<Producent> producentRoles) {
        this.producentRoles = producentRoles;
    }

    public String getRolename() {
        return roleName;
    }
}

