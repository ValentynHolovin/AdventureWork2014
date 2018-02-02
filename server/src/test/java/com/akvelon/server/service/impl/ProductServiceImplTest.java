package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.ProductDao;
import com.akvelon.server.dao.api.ProductPhotoDao;
import com.akvelon.server.domain.Product;
import com.akvelon.server.domain.ProductPhoto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        productPhotos = new ArrayList<>();

        product = new Product();
        productPhoto = new ProductPhoto();
        productPhoto1 = new ProductPhoto();

        productPhoto.setId(1);
        productPhoto1.setId(null);
        product.setId(1);

        productPhotos.add(productPhoto);
        productPhotos.add(productPhoto1);

        product.setProductPhotos(productPhotos);
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getTopFive() {
    }

    @Test
    public void searchProduct() {
    }
}