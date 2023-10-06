package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository;



import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.FileDocument;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface DocFileRepository extends JpaRepository<FileDocument, Long> {
    FileDocument findByFileName(String fileName);
}
