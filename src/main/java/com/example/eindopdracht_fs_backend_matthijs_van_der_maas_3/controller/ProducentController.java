package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ProducentDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Producent;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ProducentRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ProducentService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller.ProductController.getObjectResponseEntity;

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
            return getObjectResponseEntity(br);
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
