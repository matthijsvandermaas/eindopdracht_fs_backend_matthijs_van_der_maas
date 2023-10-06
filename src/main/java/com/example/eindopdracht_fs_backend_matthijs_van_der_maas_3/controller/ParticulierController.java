package com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.controller;

import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.dto.ParticulierDto;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.model.Particulier;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.repository.ParticulierRepository;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.service.ParticulierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/particulier")
public class ParticulierController {

    private final ParticulierService particulierService;
    private final ParticulierRepository particulierRepository;

    public ParticulierController(ParticulierRepository particulierRepository, ParticulierService particulierService) {
        this.particulierService = particulierService;
        this.particulierRepository = particulierRepository;
    }


        @PostMapping
        public ResponseEntity<Object> createParticulier(@Valid @RequestBody ParticulierDto particulier, BindingResult br) {
            if (br.hasFieldErrors()) {
                List<String> errors = br.getFieldErrors().stream()
                        .map(fe -> fe.getField() + " : " + fe.getDefaultMessage())
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errors);
            } else {
                Particulier createdParticulier = particulierService.createParticulier(particulier);

                URI uri = URI.create(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/" + createdParticulier.getId())
                        .toUriString());

                return ResponseEntity.created(uri).body(createdParticulier);
            }
        }
    }

