package com.stephenmeaney.services.product.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stephenmeaney.services.product.data.entity.Product;
import com.stephenmeaney.services.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper mapper;

    private Product createMockProduct(long num) {
        Product mockProduct = new Product();
        mockProduct.setProductId(num);
        mockProduct.setName("name" + num);
        mockProduct.setDescription("description" + num);
        mockProduct.setImage("image" + num);
        mockProduct.setPrice(num + 0.02);

        return mockProduct;
    }

    @Test
    public void testGetAll() throws Exception{

        List<Product> mockProductList = new ArrayList<>();
        mockProductList.add(createMockProduct(1));
        mockProductList.add(createMockProduct(2));

        when(productService.getAll()).thenReturn(mockProductList);

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetById() throws Exception {

        Product mockProduct = createMockProduct(3);

        when(productService.getById(3)).thenReturn(mockProduct);

        mockMvc.perform(get("/api/v1/products/3"))
                .andExpect(status().isOk());
    }

    @Test
    public void testInsert() throws Exception {

        Product mockProduct = createMockProduct(4);

        when(productService.insert(any(Product.class))).thenReturn(mockProduct);

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockProduct)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {

        Product mockProduct = createMockProduct(1);

        when(productService.update(anyLong(), any(Product.class))).thenReturn(mockProduct);

        mockMvc.perform(put("/api/v1/products/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(mockProduct)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {

        mockMvc.perform(delete("/api/v1/products/3"))
                .andExpect(status().isNoContent());
    }


}