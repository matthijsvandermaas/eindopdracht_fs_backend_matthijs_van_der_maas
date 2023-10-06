package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByUserName(String username);
}
