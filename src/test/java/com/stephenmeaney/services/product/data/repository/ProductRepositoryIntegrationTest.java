package com.stephenmeaney.services.product.data.repository;

import com.stephenmeaney.services.product.data.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryIntegrationTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testPersistData() {
        Product product = new Product();
        product.setName("name3");
        product.setDescription("description3");
        product.setImage("image3");
        product.setPrice(3.33);

        entityManager.persistAndFlush(product);

        Product foundProduct = productRepository.findById(1L);

        assertThat(foundProduct.getProductId()).isEqualTo(1);
        assertThat(foundProduct.getName()).isEqualTo("name3");
        assertThat(foundProduct.getDescription()).isEqualTo("description3");
        assertThat(foundProduct.getImage()).isEqualTo("image3");
        assertThat(foundProduct.getPrice()).isEqualTo(3.33);
    }
}
