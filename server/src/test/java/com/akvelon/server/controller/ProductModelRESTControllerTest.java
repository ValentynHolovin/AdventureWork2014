package com.akvelon.server.controller;

import com.akvelon.server.domain.Illustration;
import com.akvelon.server.domain.ProductModel;
import com.akvelon.server.service.api.ProductModelService;
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
 * Unit tests for ProductModelRESTController class.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductModelRESTController.class)
public class ProductModelRESTControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductModelService productModelService;
    private ProductModel productModel;
    private ProductModel productModel1;
    private List<ProductModel> productModels;

    @Before
    public void setUp() {
        productModels = new ArrayList<>();
        productModel = new ProductModel();
        productModel1 = new ProductModel();

        productModel.setId(1);
        productModel.setName("ProductModel");
        productModel.setCatalogDescription("CatalogDescription");
        productModel.setInstruction("Instruction");
        productModel.setIllustrations(new ArrayList<>());
        productModel.setProductDescriptions(new ArrayList<>());
        productModel.setRowguid(UUID.randomUUID().toString().toUpperCase());
        productModel1.setId(2);
        productModel1.setName("ProductModel1");
        productModel1.setCatalogDescription("CatalogDescription1");
        productModel1.setInstruction("Instruction1");
        productModel1.setIllustrations(new ArrayList<>());
        productModel1.setProductDescriptions(new ArrayList<>());
        productModel1.setRowguid(UUID.randomUUID().toString().toUpperCase());
    }

    @Test
    public void getProductModels() {
    }

    @Test
    public void getProductModel() {
    }

    @Test
    public void createProductModel() {
    }

    @Test
    public void updateProductModel() {
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