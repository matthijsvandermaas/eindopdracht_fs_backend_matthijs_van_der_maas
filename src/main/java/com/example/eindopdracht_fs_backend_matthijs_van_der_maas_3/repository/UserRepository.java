package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository;


import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
