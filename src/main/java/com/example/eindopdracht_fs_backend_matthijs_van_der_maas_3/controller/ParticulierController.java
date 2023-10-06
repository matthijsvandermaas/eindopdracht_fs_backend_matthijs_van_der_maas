package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ParticulierDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ParticulierService;
import jakarta.validation.Valid;
import org.apache.catalina.Role;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/particulieren")
public class ParticulierController {

    @Autowired
    private  final ParticulierRepository particulierRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder encoder;

    public ParticulierController(ParticulierRepository particulierRepository, RolesRepository rolesRepository, PasswordEncoder encoder) {
        this.particulierRepository = particulierRepository;
        this.rolesRepository = rolesRepository;
        this.encoder = encoder;
    }

    @PostMapping ("/particulier")
    public String createParticulier(@RequestBody ParticulierDto particulierDto){
        Particulier newParticulier = new Particulier();

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
