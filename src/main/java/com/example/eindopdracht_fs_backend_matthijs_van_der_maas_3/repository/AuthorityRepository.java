package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Authority findByRoleName(String roleName);


}
