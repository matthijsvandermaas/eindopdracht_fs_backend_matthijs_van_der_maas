package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.config.SpringSecurityConfig;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ProducentDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Producent;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ProducentRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.RolesRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ParticulierService;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ProducentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/producenten")
public class ProducentController {
    private final ProducentService producentService;
    private ProducentRepository producentRepository;
    private RolesRepository rolesRepository;
    private final PasswordEncoder encoder;
    public ProducentController(ProducentRepository producentRepository, ProducentService producentService, ProducentService productService, SpringSecurityConfig passwordEncoder, ParticulierService particulierService, ProducentService particulierService1, ProducentService producentService1, ProducentRepository producentRepository1, RolesRepository rolesRepository, PasswordEncoder encoder, PasswordEncoder encoder1) {
        this.producentService = producentService;
        this.producentRepository = producentRepository;
        this.rolesRepository = rolesRepository;
        this.encoder = encoder;
    }


    @PostMapping
    public ResponseEntity<String> createProducent(@RequestBody ProducentDto producentDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(producentService.createProducent(producentDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProducentById(@PathVariable Long id) {
        Producent producent = producentService.getProducentById(id);
        if (producent != null) {
            return ResponseEntity.ok(producent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProducent(@PathVariable Long id, @RequestBody ProducentDto producentDto) {
        try {
            producentService.updateProducent(id, producentDto);
            return ResponseEntity.ok("Producent met ID " + id + " is bijgewerkt.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens het bijwerken: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducent(@PathVariable Long id) {
        try {
            producentService.deleteProducent(id);
            return ResponseEntity.ok("Producent met ID " + id + " is verwijderd.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens het verwijderen: " + e.getMessage());
        }
    }
}