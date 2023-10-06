package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.AdminDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Admin;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.AdminRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.AuthorityRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;
    private final AdminRepository adminRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public AdminController(AdminService adminService, AdminRepository adminRepository, AuthorityRepository authorityRepository, PasswordEncoder encoder) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
        this.authorityRepository = authorityRepository;
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity<String> createParticulier(@RequestBody AdminDto adminDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(adminService.createAdmin(adminDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllParticulieren();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAdmin(@PathVariable Long id, @RequestBody AdminDto adminDto) {
        try {
            adminService.updateAdmin(id, adminDto);
            return ResponseEntity.ok("Admin met ID " + id + " is bijgewerkt.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens het bijwerken: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        try {
            adminService.deleteAdmin(id);
            return ResponseEntity.ok("Admin met ID " + id + " is verwijderd.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens het verwijderen: " + e.getMessage());
        }
    }
}