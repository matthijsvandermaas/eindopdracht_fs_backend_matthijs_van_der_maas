package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ProductRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ProductService;
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
            return getObjectResponseEntity(br);
        } else {
            Product createdProduct = productService.createProduct(product);

            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/" + createdProduct.getId()).toUriString());

            return ResponseEntity.created(uri).body(createdProduct);
        }
    }

    static ResponseEntity<Object> getObjectResponseEntity(BindingResult br) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : br.getFieldErrors()) {
            sb.append(fe.getField());
            sb.append(" : ");
            sb.append(fe.getDefaultMessage());
            sb.append("\n");
        }
        return ResponseEntity.badRequest().body(sb.toString());
    }
}
