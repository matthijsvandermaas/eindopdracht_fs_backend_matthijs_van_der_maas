package com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.repository;

import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.model.Producent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProducentRepository extends JpaRepository<Producent, Long>  {
List<Producent> findAllProducer();
}

