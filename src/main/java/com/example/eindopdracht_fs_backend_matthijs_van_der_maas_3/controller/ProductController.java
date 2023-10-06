package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.controller;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ProductRepository;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/producten")
public class ProductController {

    private final ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> createParticulier(@RequestBody ProductDto productDto) {
        try {
            Product newProduct = new Product();
            newProduct.setProductName(productDto.getProductName());
            newProduct.setNameProducer(productDto.getNameProducer());
            newProduct.setType(productDto.getType());
            newProduct.setPercentage(productDto.getPercentage());
            newProduct.setColor(productDto.getColor());
            newProduct.setTast(productDto.getTast());
            newProduct.setVolume(productDto.getVolume());
            newProduct.setProducentLocation(productDto.getProducerLocation());
            newProduct.setPhoto(productDto.getPhoto());
            newProduct.setPhoto2(productDto.getPhoto2());
            newProduct.setPhoto3(productDto.getPhoto3());

            // Opslaan in de database
            productRepository.save(newProduct);


            return ResponseEntity.ok("Inschrijving succesvol! Particulier ID: " + newProduct.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Er is een fout opgetreden tijdens de inschrijving: " + e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            ProductDto productDto = mapProductToDto(product);
            return ResponseEntity.ok(productDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private ProductDto mapProductToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setProductName(product.getProductName());
        dto.setNameProducer(product.getNameProducer());
        dto.setType(product.getType());
        dto.setPercentage(product.getPercentage());
        dto.setColor(product.getColor());
        dto.setTast(product.getTast());
        dto.setVolume(product.getVolume());
        dto.setProducerLocation(product.getProducentLocation());
        dto.setPhoto(product.getPhoto());
        dto.setPhoto2(product.getPhoto2());
        dto.setPhoto3(product.getPhoto3());

        return dto;
    }
}
