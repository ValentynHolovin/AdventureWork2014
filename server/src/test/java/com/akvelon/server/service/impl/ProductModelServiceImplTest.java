package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.IllustrationDao;
import com.akvelon.server.dao.api.ProductDescriptionDao;
import com.akvelon.server.dao.api.ProductModelDao;
import com.akvelon.server.domain.Culture;
import com.akvelon.server.domain.Illustration;
import com.akvelon.server.domain.ProductDescription;
import com.akvelon.server.domain.ProductModel;
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

@RunWith(SpringRunner.class)
public class ProductModelServiceImplTest {

    @Mock
    private ProductModelDao productModelDao;
    @Mock
    private IllustrationDao illustrationDao;
    @Mock
    private ProductDescriptionDao productDescriptionDao;
    @InjectMocks
    private ProductModelServiceImpl productModelService;

    private ProductModel productModel;
    private Illustration illustration;
    private Illustration illustration1;
    private List<Illustration> illustrations;
    private ProductDescription productDescription;
    private ProductDescription productDescription1;
    private List<ProductDescription> productDescriptions;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        illustrations = new ArrayList<>();
        productDescriptions = new ArrayList<>();

        productModel = new ProductModel();
        illustration = new Illustration();
        productDescription = new ProductDescription();

        illustration1 = new Illustration();
        productDescription1 = new ProductDescription();

        Culture culture = new Culture("Ukrainian");
        culture.setId("UA");

        illustration.setId(1);
        illustration.setDiagram("diagram");
        illustration1.setId(null);
        illustration1.setDiagram("diagram1");

        productDescription.setId(1);
        productDescription.setCulture(culture);
        productDescription.setDescription("description");
        productDescription.setRowguid(UUID.randomUUID().toString().toUpperCase());
        productDescription1.setId(null);
        productDescription1.setCulture(culture);
        productDescription1.setDescription("description1");
        productDescription1.setRowguid(UUID.randomUUID().toString().toUpperCase());

        illustrations.add(illustration);
        illustrations.add(illustration1);

        productDescriptions.add(productDescription);
        productDescriptions.add(productDescription1);

        productModel.setId(1);
        productModel.setCatalogDescription("Trololo");
        productModel.setInstruction("Tratata");
        productModel.setIllustrations(illustrations);
        productModel.setProductDescriptions(productDescriptions);
        productModel.setName("Name");
        productModel.setRowguid(UUID.randomUUID().toString().toUpperCase());

    }

    @Test
    public void create() {
        // given
        when(productModelDao.create(productModel)).thenReturn(1);

        // do
        Integer productModelID = productModelService.create(productModel);

        // verify
        verify(illustrationDao, times(1)).create(illustration);
        verify(illustrationDao, times(1)).create(illustration1);
        verify(productDescriptionDao, times(1)).create(productDescription);
        verify(productDescriptionDao, times(1)).create(productDescription1);
        verify(productModelDao, times(1)).create(productModel);

        assertEquals((Integer) 1, productModelID);
    }

    @Test
    public void update() {
        // given
        when(productModelDao.create(productModel)).thenReturn(1);

        // do
        productModelService.update(productModel);

        // verify
        verify(illustrationDao, times(1)).update(illustration);
        verify(illustrationDao, times(1)).create(illustration1);
        verify(productDescriptionDao, times(1)).update(productDescription);
        verify(productDescriptionDao, times(1)).create(productDescription1);
        verify(productModelDao, times(1)).update(productModel);
    }
}