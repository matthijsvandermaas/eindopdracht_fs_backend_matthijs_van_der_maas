package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleName(String roleName);
}