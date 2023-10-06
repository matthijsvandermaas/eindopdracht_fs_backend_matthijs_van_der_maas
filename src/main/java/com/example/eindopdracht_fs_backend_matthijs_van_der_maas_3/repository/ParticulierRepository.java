package com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.repository;

import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.model.Particulier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ParticulierRepository extends JpaRepository<Particulier, Long>  {
    List<Particulier> findAllParticulier();
}

