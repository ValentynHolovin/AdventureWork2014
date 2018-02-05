package com.akvelon.server.controller;

import com.akvelon.server.domain.Product;
import com.akvelon.server.service.api.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

/**
 * Unit tests for ProductSubcategoryRESTController class.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductRESTController.class)
public class ProductRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    private Product product;
    private Product product1;
    private List<Product> products;

    @Before
    public void setUp() {
        // TODO write tests
    }

    @Test
    public void searchProduct() {
        // TODO write tests
    }

    @Test
    public void getTopFive() {
        // TODO write tests
    }

    @Test
    public void getProduct() {
        // TODO write tests
    }

    @Test
    public void deleteProduct() {
        // TODO write tests
    }

    @Test
    public void createProduct() {
        // TODO write tests
    }

    @Test
    public void updateProduct() {
        // TODO write tests
    }
}