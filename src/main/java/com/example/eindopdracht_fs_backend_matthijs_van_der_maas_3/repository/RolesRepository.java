package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository;

import org.apache.catalina.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, String> {
    Optional<javax.management.relation.Role> findByRolename(String roleName);
}
