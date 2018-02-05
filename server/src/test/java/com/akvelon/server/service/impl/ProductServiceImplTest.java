package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.ProductDao;
import com.akvelon.server.dao.api.ProductPhotoDao;
import com.akvelon.server.domain.Product;
import com.akvelon.server.domain.ProductPhoto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for ProductServiceImpl class.
 */
@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;
    @Mock
    private ProductPhotoDao productPhotoDao;
    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductPhoto productPhoto;
    private ProductPhoto productPhoto1;
    private List<ProductPhoto> productPhotos;
    private List<Product> products;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productPhotos = new ArrayList<>();
        products = new ArrayList<>();

        product = new Product();
        productPhoto = new ProductPhoto();
        productPhoto1 = new ProductPhoto();

        productPhoto.setId(1);
        productPhoto1.setId(null);
        product.setId(1);

        productPhoto.setLargePhotoFileName("trololo.gif");

        productPhotos.add(productPhoto);
        productPhotos.add(productPhoto1);

        product.setProductPhotos(productPhotos);

        for (int i = 0; i < 5; i++) {
            products.add(new Product());
        }
    }

    @Test
    public void create() {
        // given
        when(productDao.create(product)).thenReturn(1);

        // do
        Integer productID = productService.create(product);

        // verify
        verify(productPhotoDao, times(1)).create(productPhoto);
        verify(productPhotoDao, times(1)).create(productPhoto1);
        verify(productDao, times(1)).create(product);

        assertEquals((Integer) 1, productID);
    }

    @Test
    public void create2() {
        // given
        product.setProductPhotos(null);
        when(productPhotoDao.readAllBy("ProductPhotoID", 1)).thenReturn(productPhotos);

        // do
        productService.create(product);

        // verify
        verify(productPhotoDao, times(1)).readAllBy("ProductPhotoID", 1);
    }

    @Test
    public void update() {
        // given
        when(productDao.create(product)).thenReturn(1);

        // do
        productService.update(product);

        // verify
        verify(productPhotoDao, times(1)).update(productPhoto);
        verify(productPhotoDao, times(1)).create(productPhoto1);
        verify(productDao, times(1)).update(product);
    }

    @Test
    public void getTopFive() {
        // given
        when(productDao.getTopFive()).thenReturn(products);

        // do
        List<Product> productList = productService.getTopFive();

        // verify
        verify(productDao, times(1)).getTopFive();

        assertEquals(products, productList);
    }

    @Test
    public void searchProduct() {
        // given
        when(productDao.searchProduct("BestBikes")).thenReturn(products);

        // do
        List<Product> productList = productService.searchProduct("BestBikes", 0);

        // verify
        verify(productDao, times(1)).searchProduct("BestBikes");

        assertEquals(products, productList);
    }
}