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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/particulieren")
public class ParticulierController {

    private final SecurityConfig passwordEncoder;
    private final ParticulierService particulierService;
    @Autowired
    private ParticulierRepository particulierRepository;

    @Autowired
    private RolesRepository rolesRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public ParticulierController(SecurityConfig passwordEncoder, ParticulierService particulierService, ParticulierRepository particulierRepository, RolesRepository rolesRepository, PasswordEncoder encoder) {
        this.passwordEncoder = passwordEncoder;
        this.particulierService = particulierService;
        this.particulierRepository = particulierRepository;
        this.rolesRepository = rolesRepository;
        this.encoder = encoder;
    }

    @PostMapping
    public String createParticulier(@RequestBody ParticulierDto particulierDto) {
        try {
            Particulier newParticulier = new Particulier();
            newParticulier.setFirstName(particulierDto.getFirstName());
            newParticulier.setLastName(particulierDto.getLastName());
            newParticulier.setEmail(particulierDto.getEmail());
            newParticulier.setUserName(particulierDto.getUserName());
            newParticulier.setPassword(encoder.encode(particulierDto.getPassword()));
//            newParticulier.setRoles(particulierDto.getRoles());


            // Set wachtwoord en rollen
            List<Role> userRoles = newUser.getRoles();
            for (Role roleName : particulierDto.roles){
                Optional<Role> or = rolesRepository.findById("ROLE_" + roleName);
                if (or.isPresent()){
                    userRoles.add(or.get());
                }
            }


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
