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
 * Unit tests for ProductRESTController class.
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
        products = new ArrayList<>();
        product = new Product();
        product1 = new Product();

        product.setId(1);
        product.setName("Product");
        product.setRowguid(UUID.randomUUID().toString().toUpperCase());
        product1.setId(2);
        product1.setName("Product1");
        product1.setRowguid(UUID.randomUUID().toString().toUpperCase());

        products.add(product);
        products.add(product1);
    }

    @Test
    public void searchProduct() throws Exception {
        // given
        when(productService.searchProduct("road", 0)).thenReturn(products);

        // do and verify
        mockMvc.perform(get("/search/{searchRequest}/{count}", "road", 0))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product")))
                .andExpect(jsonPath("$[0].rowguid", is(product.getRowguid())))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Product1")))
                .andExpect(jsonPath("$[1].rowguid", is(product1.getRowguid())));

        verify(productService, times(1)).searchProduct("road", 0);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void getTopFive() throws Exception {
        // given
        when(productService.getTopFive()).thenReturn(products);

        // do and verify
        mockMvc.perform(get("/get_top_five"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Product")))
                .andExpect(jsonPath("$[0].rowguid", is(product.getRowguid())))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Product1")))
                .andExpect(jsonPath("$[1].rowguid", is(product1.getRowguid())));

        verify(productService, times(1)).getTopFive();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void getProduct() throws Exception {
        // given
        when(productService.read(1)).thenReturn(product);

        // do and verify
        mockMvc.perform(get("/products/{productID}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Product")))
                .andExpect(jsonPath("$.rowguid", is(product.getRowguid())));

        verify(productService, times(1)).read(1);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void deleteProduct() throws Exception {
        // given
        when(productService.delete(1)).thenReturn(1);

        // do and verify
        MvcResult result = mockMvc.perform(get("/products/delete/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(productService, times(1)).delete(1);
        verifyNoMoreInteractions(productService);
        assertEquals(1, Integer.parseInt(content));
    }

    @Test
    public void createProduct() throws Exception {
        // given
        when(productService.create(product)).thenReturn(1);

        // do and verify
        MvcResult result = mockMvc.perform(post("/products/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(productService, times(1)).create(product);
        verifyNoMoreInteractions(productService);
        assertEquals(1, Integer.parseInt(content));
    }

    @Test
    public void updateProduct() throws Exception {
        // do and verify
        mockMvc.perform(post("/products/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(product)))
                .andExpect(status().isOk());

        verify(productService, times(1)).update(product);
        verifyNoMoreInteractions(productService);
    }

    /**
     * Converts a Java object into JSON representation
     * @param obj java object
     * @return JSON object as String
     */
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}