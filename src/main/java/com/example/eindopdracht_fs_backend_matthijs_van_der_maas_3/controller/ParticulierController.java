package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ParticulierDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Particulier;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ParticulierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/particulier")
public class ParticulierController {



    private final ParticulierService particulierService;


    public ParticulierController(ParticulierService particulierService) {
        this.particulierService = particulierService;
    }

    @PostMapping("/inschrijving")
    public ResponseEntity<Object> createParticulier(@Valid @RequestBody ParticulierDto particulier, BindingResult br) {
        if (br.hasErrors()) {
            List<String> errors = br.getFieldErrors().stream()
                    .map(fe -> fe.getField() + " : " + fe.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        Particulier createdParticulier = particulierService.createParticulier(particulier);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + createdParticulier.getId())
                .toUriString());

        return ResponseEntity.created(uri).body(createdParticulier);
    }

    public ParticulierDto mapToDto(Particulier particulier ){
        ParticulierDto dto = new ParticulierDto();
        dto.setId(particulier.getId());
        dto.setFirst_Name(particulier.getFirst_Name());
        dto.setLast_Name(particulier.getLast_Name());
        dto.setEmail(particulier.getEmail());
        // Andere velden ook mappen indien nodig
        return dto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticulierDto> getParticulierById(@PathVariable Long id) {
        Particulier particulier = particulierService.findById(id);

        if (particulier != null) {
            ParticulierDto dto = mapToDto(particulier);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}












