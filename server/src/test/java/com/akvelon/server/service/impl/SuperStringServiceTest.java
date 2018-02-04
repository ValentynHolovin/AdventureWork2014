package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.CultureDao;
import com.akvelon.server.domain.Culture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for SuperStringService class.
 */
@RunWith(SpringRunner.class)
public class SuperStringServiceTest {

    @Mock
    private CultureDao cultureDao;
    @InjectMocks
    private SuperStringService<Culture> cultureSuperStringService;
    private Culture culture;
    private List<Culture> cultures;

    @Before
    public void setUp() throws Exception {
        cultures = new ArrayList<>();
        Culture culture1 = new Culture();
        culture = new Culture();

        culture.setId("UA");
        culture.setName("Ukrainian");

        culture1.setId("RU");
        culture1.setName("Russian");

        cultures.add(culture);
        cultures.add(culture1);
    }

    @Test
    public void create() {
        // given
        when(cultureDao.create(culture)).thenReturn("UA");

        // do
        String cultureID = cultureSuperStringService.create(culture);

        // verify
        verify(cultureDao, times(1)).create(culture);
        assertEquals("UA", cultureID);

    }

    @Test
    public void read() {
        // given
        when(cultureDao.read("UA")).thenReturn(culture);

        // do
        Culture culture1 = cultureSuperStringService.read("UA");

        // verify
        verify(cultureDao, times(1)).read("UA");
        assertEquals(culture, culture1);

    }

    @Test
    public void update() {
        // do
        cultureSuperStringService.update(culture);

        // verify
        verify(cultureDao, times(1)).update(culture);
    }

    @Test
    public void delete() {
        // do
        cultureSuperStringService.delete("UA");

        // verify
        verify(cultureDao, times(1)).delete("UA");
    }

    @Test
    public void readBy() {
        // given
        when(cultureDao.readBy("CultureID", "UA")).thenReturn(culture);

        // do
        Culture culture1 = cultureSuperStringService.readBy("CultureID", "UA");

        // verify
        verify(cultureDao, times(1)).readBy("CultureID", "UA");
        assertEquals(culture, culture1);
    }

    @Test
    public void readAllBy() {
        // given
        cultures.remove(1);
        when(cultureDao.readAllBy("CultureID", "UA")).thenReturn(cultures);

        // do
        List<Culture> cultureList = cultureSuperStringService.readAllBy("CultureID", "UA");

        // verify
        verify(cultureDao, times(1)).readAllBy("CultureID", "UA");
        assertEquals(cultures, cultureList);
    }
}