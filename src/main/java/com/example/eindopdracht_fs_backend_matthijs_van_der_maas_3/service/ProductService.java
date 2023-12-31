package com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.service;


import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.dto.ProductDto;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.model.Product;
import com.eindopdracht.eindopdracht_fs_matthijs_van_der_maas.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findAllProducts(){
        return (List<Product>) (productRepository.findAllProducts());
    }

    public Product createProduct(ProductDto product) {
        Product newProduct = new Product();
        newProduct.setName_Product(product.getName_Product());
        newProduct.setName_Producer(product.getName_Producer());
        newProduct.setPercentage(product.getPercentage());
        newProduct.setColor(product.getColor());
        newProduct.setTast(product.getTast());
        newProduct.setVolume(product.getVolume());
        newProduct.setLocation_Producent(product.getLocation_Producent());

        // Sla het nieuwe product op in de repository
        productRepository.save(newProduct);

        return newProduct;
    }
}
