package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

}

