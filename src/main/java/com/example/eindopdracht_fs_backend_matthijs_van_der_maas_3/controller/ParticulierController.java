package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ParticulierDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ParticulierRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.RolesRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ParticulierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/particulieren")
@CrossOrigin(origins = "*")
public class ParticulierController {

    private final ParticulierService particulierService;
    private final ParticulierRepository particulierRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public ParticulierController(ParticulierService particulierService, ParticulierRepository particulierRepository, RolesRepository rolesRepository, PasswordEncoder encoder) {
        this.particulierService = particulierService;
        this.particulierRepository = particulierRepository;
        this.rolesRepository = rolesRepository;
        this.encoder = encoder;
    }
    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<String> createParticulier(@RequestBody ParticulierDto particulierDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(particulierService.createParticulier(particulierDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<Particulier>> getAllParticulieren() {
        List<Particulier> particulieren = particulierService.getAllParticulieren();
        return ResponseEntity.ok(particulieren);
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Particulier> getParticulierById(@PathVariable Long id) {
        Particulier particulier = particulierService.getParticulierById(id);
        if (particulier != null) {
            return ResponseEntity.ok(particulier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateParticulier(@PathVariable Long id, @RequestBody ParticulierDto particulierDto) {
        try {
            particulierService.updateParticulier(id, particulierDto);
            return ResponseEntity.ok("Particulier met ID " + id + " is bijgewerkt.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens het bijwerken: " + e.getMessage());
        }
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParticulier(@PathVariable Long id) {
        try {
            particulierService.deleteParticulier(id);
            return ResponseEntity.ok("Particulier met ID " + id + " is verwijderd.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens het verwijderen: " + e.getMessage());
        }
    }
}