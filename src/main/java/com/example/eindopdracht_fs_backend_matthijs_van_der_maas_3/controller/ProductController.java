package com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.controller;

import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.repository.ProductRepository;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.service.ProductService;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.dto.ProductDto;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDto product, BindingResult br) {
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
            Product createdProduct = productService.createProduct(product);

            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + createdProduct.getId()).toUriString());

            return ResponseEntity.created(uri).body(createdProduct);
        }
    }
}
