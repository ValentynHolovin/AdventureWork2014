package com.akvelon.server.controller;

import com.akvelon.server.domain.ProductCategory;
import com.akvelon.server.domain.ProductSubcategory;
import com.akvelon.server.service.api.ProductSubcategoryService;
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
@WebMvcTest(ProductSubcategoryRESTController.class)
public class ProductSubcategoryRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductSubcategoryService productSubcategoryService;
    private ProductSubcategory productSubcategory;
    private ProductSubcategory productSubcategory1;
    private List<ProductSubcategory> productSubcategories;

    @Before
    public void setUp() {
        productSubcategories = new ArrayList<>();
        productSubcategory = new ProductSubcategory();
        productSubcategory1 = new ProductSubcategory();

        productSubcategory.setId(1);
        productSubcategory.setCategory(new ProductCategory());
        productSubcategory.setName("Subcategory");
        productSubcategory.setRowguid(UUID.randomUUID().toString().toUpperCase());
        productSubcategory1.setId(2);
        productSubcategory1.setCategory(new ProductCategory());
        productSubcategory1.setName("Subcategory1");
        productSubcategory1.setRowguid(UUID.randomUUID().toString().toUpperCase());

        productSubcategories.add(productSubcategory);
        productSubcategories.add(productSubcategory1);
    }

    @Test
    public void getProductSubcategories() throws Exception {
        // given
        when(productSubcategoryService.getAll()).thenReturn(productSubcategories);

        // do and verify
        mockMvc.perform(get("/get_subcategories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Subcategory")))
                .andExpect(jsonPath("$[0].category.rowguid", is(productSubcategory.getCategory().getRowguid())))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Subcategory1")))
                .andExpect(jsonPath("$[1].category.rowguid", is(productSubcategory1.getCategory().getRowguid())));

        verify(productSubcategoryService, times(1)).getAll();
        verifyNoMoreInteractions(productSubcategoryService);
    }

    @Test
    public void getProductSubcategory() throws Exception {
        // given
        when(productSubcategoryService.read(1)).thenReturn(productSubcategory);

        // do nd verify
        mockMvc.perform(get("/subcategories/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Subcategory")))
                .andExpect(jsonPath("$.category.rowguid", is(productSubcategory.getCategory().getRowguid())));

        verify(productSubcategoryService, times(1)).read(1);
        verifyNoMoreInteractions(productSubcategoryService);
    }

    @Test
    public void createProductSubcategory() throws Exception {
        // given
        when(productSubcategoryService.create(productSubcategory)).thenReturn(1);

        // do and verify
        MvcResult result = mockMvc.perform(post("/subcategories/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productSubcategory)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(productSubcategoryService, times(1)).create(productSubcategory);
        verifyNoMoreInteractions(productSubcategoryService);
        assertEquals(1, Integer.parseInt(content));
    }

    @Test
    public void updateProductSubcategory() throws Exception {
        // do and verify
        mockMvc.perform(post("/subcategories/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productSubcategory)))
                .andExpect(status().isOk());

        verify(productSubcategoryService, times(1)).update(productSubcategory);
        verifyNoMoreInteractions(productSubcategoryService);
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