package com.akvelon.server.controller;

import com.akvelon.server.domain.UnitMeasure;
import com.akvelon.server.service.api.UnitMeasureService;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

/**
 * Unit tests for UnitMeasureController class.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UnitMeasureController.class)
public class UnitMeasureControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UnitMeasureService unitMeasureService;
    private UnitMeasure unitMeasure;
    private UnitMeasure unitMeasure1;
    private List<UnitMeasure> unitMeasures;

    @Before
    public void setUp() {
        unitMeasures = new ArrayList<>();
        unitMeasure = new UnitMeasure();
        unitMeasure1 = new UnitMeasure();

        unitMeasure.setId("Bla");
        unitMeasure.setName("BlaBlaBla");
        unitMeasure1.setId("Bro");
        unitMeasure1.setName("BroBroBro");

        unitMeasures.add(unitMeasure);
        unitMeasures.add(unitMeasure1);
    }

    @Test
    public void getUnitMeasureCodes() throws Exception {
        // given
        when(unitMeasureService.getAll()).thenReturn(unitMeasures);

        // do and verify
        mockMvc.perform(get("/get_unitmeasurecode"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("Bla")))
                .andExpect(jsonPath("$[0].name", is("BlaBlaBla")))
                .andExpect(jsonPath("$[1].id", is("Bro")))
                .andExpect(jsonPath("$[1].name", is("BroBroBro")));

        verify(unitMeasureService, times(1)).getAll();
        verifyNoMoreInteractions(unitMeasureService);
    }

    @Test
    public void getUnitMeasure() throws Exception {
        // given
        when(unitMeasureService.read("Bla")).thenReturn(unitMeasure);

        // do and verify
        mockMvc.perform(get("/unitmeasures/{id}", "Bla"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is("Bla")))
                .andExpect(jsonPath("$.name", is("BlaBlaBla")));

        verify(unitMeasureService, times(1)).read("Bla");
        verifyNoMoreInteractions(unitMeasureService);
    }

    @Test
    public void createUnitMeasure() throws Exception {
        // given
        when(unitMeasureService.create(unitMeasure)).thenReturn("Bla");

        // do and verify
        MvcResult result = mockMvc.perform(post("/unitmeasures/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(unitMeasure)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andReturn();

        String content = result.getResponse().getContentAsString();

        verify(unitMeasureService, times(1)).create(unitMeasure);
        verifyNoMoreInteractions(unitMeasureService);
        assertEquals("Bla", content);
    }

    @Test
    public void updateUnitMeasure() throws Exception {
        // do and verify
        mockMvc.perform(post("/unitmeasures/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(unitMeasure)))
                .andExpect(status().isOk());

        verify(unitMeasureService, times(1)).update(unitMeasure);
        verifyNoMoreInteractions(unitMeasureService);
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