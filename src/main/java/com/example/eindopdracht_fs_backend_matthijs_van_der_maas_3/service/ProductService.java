package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service;

import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String createProduct(ProductDto productDto) {
        Product newProduct = new Product();
        newProduct.setProductName(productDto.getProductName());
        newProduct.setNameProducer(productDto.getNameProducer());
        newProduct.setType(productDto.getType());
        newProduct.setPercentage(productDto.getPercentage());
        newProduct.setColor(productDto.getColor());
        newProduct.setTast(productDto.getTast());
        newProduct.setVolume(productDto.getVolume());
        newProduct.setProducentLocation(productDto.getProducerLocation());

        productRepository.save(newProduct);

        return "Inschrijving succesvol! Product ID: " + newProduct.getId();
    }

    public ProductDto getProductById(Long id) {
        // Logica om product op te halen uit de repository en om te zetten naar ProductDto
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            ProductDto productDto = new ProductDto();
            productDto.setProductName(product.getProductName());
            productDto.setNameProducer(product.getNameProducer());
            productDto.setType(product.getType());
            productDto.setPercentage(product.getPercentage());
            productDto.setColor(product.getColor());
            productDto.setTast(product.getTast());
            productDto.setVolume(product.getVolume());
            productDto.setProducerLocation(product.getProducentLocation());
            return productDto;
        } else {
            return null;
        }
    }
}
