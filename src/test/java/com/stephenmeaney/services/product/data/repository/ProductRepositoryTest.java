package com.stephenmeaney.services.product.data.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.stephenmeaney.services.product.data.entity.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:test-datasets.xml")
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testFindAll() {
        List<Product> productList = productRepository.findAll();

        assertThat(productList.get(0).getProductId()).isEqualTo(1);
        assertThat(productList.get(0).getName()).isEqualTo("name1");
        assertThat(productList.get(0).getDescription()).isEqualTo("description1");
        assertThat(productList.get(0).getImage()).isEqualTo("image1");
        assertThat(productList.get(0).getPrice()).isEqualTo(1.11);
    }

    @Test
    public void testFindById() {
        Product product = productRepository.findById(2);

        assertThat(product.getProductId()).isEqualTo(2);
        assertThat(product.getName()).isEqualTo("name2");
        assertThat(product.getDescription()).isEqualTo("description2");
        assertThat(product.getImage()).isEqualTo("image2");
        assertThat(product.getPrice()).isEqualTo(2.22);
    }

}