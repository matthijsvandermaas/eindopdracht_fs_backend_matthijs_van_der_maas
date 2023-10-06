package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ParticulierDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.UserRole;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.RolesRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ParticulierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/particulieren")
public class ParticulierController {

    private final PasswordEncoder passwordEncoder;
    private final ParticulierService particulierService;

    @Autowired
    private ParticulierRepository particulierRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    public ParticulierController(PasswordEncoder passwordEncoder, ParticulierService particulierService) {
        this.passwordEncoder = passwordEncoder;
        this.particulierService = particulierService;
    }

    @PostMapping("/inschrijving")
    public ResponseEntity<String> inschrijven(@RequestBody ParticulierDto particulierDto) {
        try {
            // Maak een nieuw Particulier object aan
            Particulier newParticulier = new Particulier();
            // Zet eigenschappen van particulierDto naar newParticulier

            // Hash het wachtwoord voordat het wordt opgeslagen
            newParticulier.setPassword(passwordEncoder.encode(particulierDto.getPassword()));

            // Converteer de rollen van String naar UserRole
            Set<UserRole> roles = Arrays.stream(particulierDto.getRoles())
                    .map(UserRole::valueOf)
                    .collect(Collectors.toSet());
            newParticulier.setRoles(roles);

            // Opslaan in de database
            particulierRepository.save(newParticulier);

            return ResponseEntity.ok("Inschrijving succesvol! Particulier ID: " + newParticulier.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }
    }
}
