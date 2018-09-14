package com.stephenmeaney.services.product.service;

import com.stephenmeaney.services.product.data.entity.Product;
import com.stephenmeaney.services.product.data.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    Logger logger = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product insert(Product product) {
        return productRepository.save(product);
    }

    public Product update(long id, Product newProduct) {
        // would normally check contract for "id not found" behavior and how to handle incomplete entity
        newProduct.setProductId(id);
        return productRepository.save(newProduct);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
