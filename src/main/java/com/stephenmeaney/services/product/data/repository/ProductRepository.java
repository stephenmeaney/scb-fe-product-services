package com.stephenmeaney.services.product.data.repository;

import com.stephenmeaney.services.product.data.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    Product findById(long id);
}
