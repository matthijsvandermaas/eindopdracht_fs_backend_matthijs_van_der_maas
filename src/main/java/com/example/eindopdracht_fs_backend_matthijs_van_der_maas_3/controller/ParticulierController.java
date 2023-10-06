package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ParticulierDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Role;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.RolesRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security.SecurityConfig;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ParticulierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

            // Set wachtwoord en rollen
            newParticulier.setPassword(passwordEncoder.encode(particulierDto.getPassword()));
            Set<Role> userRoles = new HashSet<>();
            Role userRole = rolesRepository.findByRoleName("User");
            userRoles.add(userRole);
            newParticulier.setRoles(userRoles);

            // Opslaan in de database
            particulierRepository.save(newParticulier);


            return ResponseEntity.ok("Inschrijving succesvol! Particulier ID: " + newParticulier.getId());
        } catch (Exception e) {
                       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticulierDto> getParticulierById(@PathVariable Long id) {
        Optional<Particulier> particulierOptional = particulierRepository.findById(id);
        if (particulierOptional.isPresent()) {
            Particulier particulier = particulierOptional.get();
            ParticulierDto particulierDto = mapParticulierToDto(particulier);
            return ResponseEntity.ok(particulierDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ParticulierDto mapParticulierToDto(Particulier particulier) {
        ParticulierDto dto = new ParticulierDto();
        dto.setFirstName(particulier.getFirstName());
        dto.setLastName(particulier.getLastName());
        dto.setEmail(particulier.getEmail());
        dto.setUserName(particulier.getUserName());
        dto.setRoles(particulier.getRoles());
        return dto;
    }
}
