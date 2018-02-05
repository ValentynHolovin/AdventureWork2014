package com.akvelon.server.controller;

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

        productModels.add(productModel);
        productModels.add(productModel1);
    }

    @Test
    public void getProductModels() throws Exception {
        // given
        when(productModelService.getAll()).thenReturn(productModels);

        // do and verify
        mockMvc.perform(get("/get_models"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("ProductModel")))
                .andExpect(jsonPath("$[0].catalogDescription", is("CatalogDescription")))
                .andExpect(jsonPath("$[0].instruction", is("Instruction")))
                .andExpect(jsonPath("$[0].rowguid", is(productModel.getRowguid())))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("ProductModel1")))
                .andExpect(jsonPath("$[1].catalogDescription", is("CatalogDescription1")))
                .andExpect(jsonPath("$[1].instruction", is("Instruction1")))
                .andExpect(jsonPath("$[1].rowguid", is(productModel1.getRowguid())));

        verify(productModelService, times(1)).getAll();
        verifyNoMoreInteractions(productModelService);
    }

    @Test
    public void getProductModel() throws Exception {
        // given
        when(productModelService.read(1)).thenReturn(productModel);

        // do and verify
        mockMvc.perform(get("/models/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("ProductModel")))
                .andExpect(jsonPath("$.catalogDescription", is("CatalogDescription")))
                .andExpect(jsonPath("$.instruction", is("Instruction")))
                .andExpect(jsonPath("$.rowguid", is(productModel.getRowguid())));

        verify(productModelService, times(1)).read(1);
        verifyNoMoreInteractions(productModelService);
    }

    @Test
    public void createProductModel() throws Exception {
        // given
        when(productModelService.create(productModel)).thenReturn(1);

        // do and verify
        MvcResult result = mockMvc.perform(post("/models/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productModel)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(productModelService, times(1)).create(productModel);
        verifyNoMoreInteractions(productModelService);
        assertEquals(1, Integer.parseInt(content));
    }

    @Test
    public void updateProductModel() throws Exception {
        // do and verify
        mockMvc.perform(post("/models/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productModel)))
                .andExpect(status().isOk());

        verify(productModelService, times(1)).update(productModel);
        verifyNoMoreInteractions(productModelService);
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