package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.ProductCategoryDao;
import com.akvelon.server.domain.ProductCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for SuperService class.
 */
@RunWith(SpringRunner.class)
public class SuperServiceTest {

    @Mock
    private ProductCategoryDao productCategoryDao;
    @InjectMocks
    private SuperService<Integer, ProductCategory> productCategorySuperService = new ProductCategoryServiceImpl();
    private ProductCategory productCategory;
    private List<ProductCategory> productCategories;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productCategories = new ArrayList<>();
        productCategory = new ProductCategory();
        ProductCategory productCategory1 = new ProductCategory();

        productCategory.setId(1);
        productCategory.setName("Trololo");
        productCategory.setRowguid(UUID.randomUUID().toString().toUpperCase());

        productCategory1.setId(2);
        productCategory1.setName("Trututu");
        productCategory1.setRowguid(UUID.randomUUID().toString().toUpperCase());

        productCategories.add(productCategory);
        productCategories.add(productCategory1);
    }

    @Test
    public void getAll() {
        // given
        when(productCategoryDao.getAll()).thenReturn(productCategories);

        // do
        List<ProductCategory> productCategoryList = productCategorySuperService.getAll();

        // verify
        verify(productCategoryDao, times(1)).getAll();
        assertEquals(productCategories, productCategoryList);
    }

    @Test
    public void create() {
        // given
        when(productCategoryDao.create(productCategory)).thenReturn(1);

        // do
        Integer productCategoryID = productCategorySuperService.create(productCategory);

        // verify
        verify(productCategoryDao, times(1)).create(productCategory);
        assertEquals((Integer) 1, productCategoryID);
    }

    @Test
    public void read() {
        // given
        when(productCategoryDao.read(1)).thenReturn(productCategory);

        // do
        ProductCategory productCategory2 = productCategorySuperService.read(1);

        // verify
        verify(productCategoryDao, times(1)).read(1);
        assertEquals(productCategory, productCategory2);
    }

    @Test
    public void update() {
        // do
        productCategorySuperService.update(productCategory);

        // verify
        verify(productCategoryDao, times(1)).update(productCategory);
    }

    @Test
    public void delete() {
        // do
        productCategorySuperService.delete(1);

        // verify
        verify(productCategoryDao, times(1)).delete(1);
    }

    @Test
    public void readBy() {
        // given
        when(productCategoryDao.readBy("ProductCategoryID", 1)).thenReturn(productCategory);

        // do
        ProductCategory productCategory2 = productCategorySuperService.readBy("ProductCategoryID", 1);

        // verify
        verify(productCategoryDao, times(1)).readBy("ProductCategoryID", 1);
        assertEquals(productCategory, productCategory2);
    }

    @Test
    public void readAllBy() {
        // given
        productCategories.remove(1);
        when(productCategoryDao.readAllBy("ProductCategoryID", 1)).thenReturn(productCategories);

        // do
        List<ProductCategory> productCategoryList = productCategorySuperService.readAllBy("ProductCategoryID", 1);

        // verify
        verify(productCategoryDao, times(1)).readAllBy("ProductCategoryID", 1);
        assertEquals(productCategories, productCategoryList);
    }
}