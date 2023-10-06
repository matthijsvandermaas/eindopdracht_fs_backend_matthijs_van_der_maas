package com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.service;


import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.dto.ProductDto;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.model.Product;
import com.example.eindopdracht_fs_backend_matthijs_van_der_maas_3.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String createProduct(ProductDto productDto) {


        Product newProduct = new Product();
        {
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

    }
}