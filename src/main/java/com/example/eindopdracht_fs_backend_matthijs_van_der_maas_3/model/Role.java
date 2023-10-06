package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
    @Table(name = "Roles")
    public class Role{
    @Id
    private String rolename;
    @ManyToMany(mappedBy = "roles")
        private Set<com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier> particulier;
    public String getRolename() {
        return rolename;
    }
    public void setRolename(String rolename) {
        this.rolename = rolename;

    }
    public Set<com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier> getParticulier() {
        return particulier;
    }
    public void setParticulier(Set<com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier> particulier) {
        this.particulier = particulier;
    }


}

