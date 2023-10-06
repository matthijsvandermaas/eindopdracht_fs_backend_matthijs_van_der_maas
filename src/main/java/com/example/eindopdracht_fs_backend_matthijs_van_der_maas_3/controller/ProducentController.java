package com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.controller;

import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.dto.ProducentDto;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.model.Producent;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.repository.ProducentRepository;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.service.ProducentService;
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

@RestController
@RequestMapping("/producent")
public class ProducentController {

    final ProducentRepository producentRepository;
    private final ProducentService producentService;

    public ProducentController(ProducentRepository producentRepository, ProducentService producentService, ProducentService productService) {
        this.producentService = producentService;
        this.producentRepository = producentRepository;
    }


    @PostMapping
    public ResponseEntity<Object> createProducent(@Valid @RequestBody Producent producent, BindingResult br) {
        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
            ProducentDto producentDto = new ProducentDto();
            Producent createdProducent = producentService.createProducent(producentDto);

            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + createdProducent.getId()).toUriString());

            return ResponseEntity.created(uri).body(createdProducent);
        }
    }
}
