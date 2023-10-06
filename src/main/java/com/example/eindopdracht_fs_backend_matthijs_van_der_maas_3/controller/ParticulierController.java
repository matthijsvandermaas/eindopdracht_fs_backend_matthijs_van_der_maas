package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ParticulierDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.RolesRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security.SecurityConfig;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ParticulierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/particulieren")
public class ParticulierController {

    private final SecurityConfig passwordEncoder;
    private final ParticulierService particulierService;

    @Autowired
    private ParticulierRepository particulierRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    public ParticulierController(SecurityConfig passwordEncoder, ParticulierService particulierService) {
        this.passwordEncoder = passwordEncoder;
        this.particulierService = particulierService;
    }

    @PostMapping
    public ResponseEntity<String> createParticulier(@RequestBody ParticulierDto particulierDto) {
        try {
            Particulier newParticulier = new Particulier();
            newParticulier.setFirstName(particulierDto.getFirstName());
            newParticulier.setLastName(particulierDto.getLastName());
            newParticulier.setEmail(particulierDto.getEmail());
            newParticulier.setUserName(particulierDto.getUserName());
           //TODO roles toevoegen

            // Hier set je het versleutelde wachtwoord van de particulier
            newParticulier.setPassword(passwordEncoder.encode(particulierDto.getPassword()));
            // Opslaan in de database
            particulierRepository.save(newParticulier);

            return ResponseEntity.ok("Inschrijving succesvol! Particulier ID: " + newParticulier.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }
    }
}
