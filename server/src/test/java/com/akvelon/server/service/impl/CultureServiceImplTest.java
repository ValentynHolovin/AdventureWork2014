package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.CultureDao;
import com.akvelon.server.domain.Culture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class CultureServiceImplTest {

    @Mock
    private CultureDao cultureDao;
    @InjectMocks
    private CultureServiceImpl cultureService;
    private Culture culture;
    private List<Culture> cultures;

    @Before
    public void initCulture() {
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
    public void getAll() {
        // given
        when(cultureDao.getAll()).thenReturn(cultures);

        // do
        List<Culture> cultureList = cultureService.getAll();

        // verify
        verify(cultureDao, times(1)).getAll();
        assertEquals(cultures, cultureList);

    }

    @Test
    public void create() {
        // given
        when(cultureDao.create(culture)).thenReturn("UA");

        // do
        String cultureID = cultureService.create(culture);

        // verify
        verify(cultureDao, times(1)).create(culture);
        assertEquals("UA", cultureID);

    }

    @Test
    public void read() {
        // given
        when(cultureDao.read("UA")).thenReturn(culture);

        // do
        Culture culture1 = cultureService.read("UA");

        // verify
        verify(cultureDao, times(1)).read("UA");
        assertEquals(culture, culture1);

    }

    @Test
    public void update() {
        // do
        cultureService.update(culture);

        // verify
        verify(cultureDao, times(1)).update(culture);
    }

    @Test
    public void delete() {
        // do
        cultureService.delete("UA");

        // verify
        verify(cultureDao, times(1)).delete("UA");
    }

    @Test
    public void readBy() {
        // given
        when(cultureDao.readBy("CultureID", "UA")).thenReturn(culture);

        // do
        Culture culture1 = cultureService.readBy("CultureID", "UA");

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
        List<Culture> cultureList = cultureService.readAllBy("CultureID", "UA");

        // verify
        verify(cultureDao, times(1)).readAllBy("CultureID", "UA");
        assertEquals(cultures, cultureList);
    }
}
