package com.stephenmeaney.services.product.service;

import com.stephenmeaney.services.product.data.entity.Product;
import com.stephenmeaney.services.product.data.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProductService.class})
public class ProductServiceTest {

    @MockBean
    private ProductRepository mockProductRepository;

    @Autowired
    private ProductService productService;

    public Product createMockProduct(long num) {
        Product mockProduct = new Product();
        mockProduct.setProductId(num);
        mockProduct.setName("name" + num);
        mockProduct.setDescription("description" + num);
        mockProduct.setImage("image" + num);
        mockProduct.setPrice(num + 0.01);

        return mockProduct;
    }

    @Test
    public void testGetById() {
        Product mockProduct = createMockProduct(1L);

        when(mockProductRepository.findById(anyLong())).thenReturn(mockProduct);

        Product returnedProduct = productService.getById(1L);

        assertThat(returnedProduct.getProductId()).isEqualTo(1L);
        assertThat(returnedProduct.getName()).isEqualTo("name1");
        assertThat(returnedProduct.getDescription()).isEqualTo("description1");
        assertThat(returnedProduct.getImage()).isEqualTo("image1");
        assertThat(returnedProduct.getPrice()).isEqualTo(1.01);

    }

    @Test
    public void testGetAll() {
        Product mockProduct = createMockProduct(2L);
        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(mockProduct);

        when(mockProductRepository.findAll()).thenReturn(mockProductList);

        List<Product> returnedProductList = productService.getAll();

        assertThat(returnedProductList.get(0).getProductId()).isEqualTo(2L);
        assertThat(returnedProductList.get(0).getName()).isEqualTo("name2");
        assertThat(returnedProductList.get(0).getDescription()).isEqualTo("description2");
        assertThat(returnedProductList.get(0).getImage()).isEqualTo("image2");
        assertThat(returnedProductList.get(0).getPrice()).isEqualTo(2.01);
    }

    @Test
    public void testInsert() {
        Product mockProduct = createMockProduct(3L);

        when(mockProductRepository.save(any(Product.class))).thenReturn(mockProduct);

        Product returnedProduct = productService.insert(mockProduct);

        assertThat(returnedProduct.getProductId()).isEqualTo(3L);
        assertThat(returnedProduct.getName()).isEqualTo("name3");
        assertThat(returnedProduct.getDescription()).isEqualTo("description3");
        assertThat(returnedProduct.getImage()).isEqualTo("image3");
        assertThat(returnedProduct.getPrice()).isEqualTo(3.01);
    }

    @Test
    public void testUpdate() {
        Product mockNewProduct = new Product();
        mockNewProduct.setName("newname");
        mockNewProduct.setDescription("newdescription");

        when(mockProductRepository.save(any(Product.class))).then(returnsFirstArg());

        Product updatedProduct = productService.update(4L, mockNewProduct);

        assertThat(updatedProduct.getProductId()).isEqualTo(4L);
        assertThat(updatedProduct.getName()).isEqualTo("newname");
        assertThat(updatedProduct.getDescription()).isEqualTo("newdescription");
        assertThat(updatedProduct.getImage()).isNull();
        assertThat(updatedProduct.getPrice()).isEqualTo(0.0);
    }

    @Test
    public void testDelete() {
        productService.delete(1);
        verify(mockProductRepository, times(1)).deleteById(anyLong());
    }
}