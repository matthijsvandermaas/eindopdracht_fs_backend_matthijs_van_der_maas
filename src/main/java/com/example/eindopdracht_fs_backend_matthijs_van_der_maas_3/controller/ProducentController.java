package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ProducentDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Producent;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Role;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ProducentRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.RolesRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.security.SecurityConfig;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ParticulierService;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ProducentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import static com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller.ProductController.getObjectResponseEntity;

@RestController
@RequestMapping("/producenten")
public class ProducentController {
    private final SecurityConfig passwordEncoder;
    private final ProducentService producentService;
    @Autowired
    private ProducentRepository producentRepository;

    @Autowired
    private RolesRepository rolesRepository;


    public ProducentController(ProducentRepository producentRepository, ProducentService producentService, ProducentService productService, SecurityConfig passwordEncoder, ParticulierService particulierService, ProducentService particulierService1, ProducentService producentService1) {
        this.passwordEncoder = passwordEncoder;
        this.producentService = producentService;
    }


    @PostMapping
    public ResponseEntity<Object> createProducent(@Valid @RequestBody ProducentDto producentDto) {
        try {
            Producent newProducent = new Producent();
            newProducent.setFirstName(producentDto.getFirstName());
            newProducent.setLastName(producentDto.getLastName());
            newProducent.setOwner(producentDto.getOwner());
            newProducent.setNameBrewery(producentDto.getNameBrewery());
            newProducent.setSaleLocation(producentDto.getSaleLocation());
            newProducent.setStreet(producentDto.getStreet());
            newProducent.setHouseNumber(producentDto.getHouseNumber());
            newProducent.setZipcode(producentDto.getZipcode());
            newProducent.setCity(producentDto.getCity());
            newProducent.setBrands(producentDto.getBrands());
            newProducent.setEmail(producentDto.getEmail());
            newProducent.setUserName(producentDto.getUserName());
            newProducent.setPassword(producentDto.getPassword());
            newProducent.setRoles(producentDto.getRoles());

            // Set wachtwoord en rollen
            newProducent.setPassword(passwordEncoder.encode(producentDto.getPassword()));
            Set<Role> userRoles = new HashSet<>();
            Role userRole = rolesRepository.findByRoleName("User");
            userRoles.add(userRole);
            newProducent.setRoles(userRoles);

            // Opslaan in de database
            producentRepository.save(newProducent);


            return ResponseEntity.ok("Inschrijving succesvol! Particulier ID: " + newParticulier.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }
    }
}