package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service;


import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.AdminDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Admin;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createAdmin(AdminDto adminDto) {
        // Het wachtwoord hashen voordat het wordt opgeslagen
        String hashedPassword = passwordEncoder.encode(adminDto.getPassword());

        // Admin object maken
        Admin newAdmin = new Admin();
        newAdmin.setUserName(adminDto.getUserName());
        newAdmin.setPassword(hashedPassword);
        newAdmin.setAuthorities(adminDto.getAuthority());

        adminRepository.save(newAdmin);

        // Terugkeren met een bevestigingsbericht
        return "Inschrijving succesvol! admin ID: " + newAdmin.getId();
    }

    public List<Admin> getAllParticulieren() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(long id) {
        Optional<Admin> adminOptional = adminRepository.findById(String.valueOf(id));
        return adminOptional.orElse(null);
    }

    public void updateAdmin(Long id, AdminDto adminDto) {
        Optional<Admin> existingAdminOptional = adminRepository.findById(String.valueOf(id));
        if (existingAdminOptional.isPresent()) {
            Admin existingAdmin = existingAdminOptional.get();
            existingAdmin.setUserName(adminDto.getUserName());
            existingAdmin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
            existingAdmin.setAuthorities(adminDto.getAuthority());

            adminRepository.save(existingAdmin);
        } else {
            throw new IllegalArgumentException("admin met ID " + id + " niet gevonden");
        }
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(String.valueOf(id));
    }


}
