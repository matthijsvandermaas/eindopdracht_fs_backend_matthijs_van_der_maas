package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ParticulierDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.RolesRepository;
import org.apache.catalina.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/particulieren")
public class ParticulierController {

    @Autowired
    private ParticulierRepository particulierRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder encoder;

    public ParticulierController(ParticulierRepository particulierRepository, RolesRepository rolesRepository, PasswordEncoder encoder) {
        this.particulierRepository = particulierRepository;
        rolesRepository = rolesRepository;
        this.encoder = encoder;
    }

    @PostMapping("/particulier")
    public String createParticulier(@RequestBody ParticulierDto particulierDto) {
        Particulier newParticulier = new Particulier();
        newParticulier.setPassword(encoder.encode(particulierDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        for (String roleName : particulierDto.getRoles()) {
            Optional<Role> roleOptional = rolesRepository.findById("ROLE" + roleName);
            roleOptional.ifPresent(roles::add);
        }
        newParticulier.setRoles(roles);

        particulierRepository.save(newParticulier);

        return "yes één bierliefhebber erbij";
    }


    @PostMapping("/particulieren/inschrijving")
    public ResponseEntity<String> inschrijven(@RequestBody Particulier particulier) {
        try {
            particulierRepository.save(particulier);
            return ResponseEntity.ok("Inschrijving succesvol!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }
    }
}
