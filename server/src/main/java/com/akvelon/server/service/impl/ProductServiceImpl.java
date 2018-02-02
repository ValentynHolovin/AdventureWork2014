package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.ProductDao;
import com.akvelon.server.dao.api.ProductPhotoDao;
import com.akvelon.server.domain.Product;
import com.akvelon.server.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends SuperService<Product> implements ProductService{
    private static ProductServiceImpl productService;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductPhotoDao productPhotoDao;

    @Autowired
    protected ProductServiceImpl(ProductDao dao) {
        super(dao);
        if (productService == null) {
            productService = this;
        }
    }

    @Override
    public Integer create(Product value) {
        if (value.getProductPhotos().size() == 0) {
            value.setProductPhotos(productPhotoDao.readAllBy("ProductPhotoID", 1));
        }

        for (int i = 0; i < value.getProductPhotos().size(); i++) {
            productPhotoDao.create(value.getProductPhotos().get(i));
        }

        return super.create(value);
    }

    @Override
    public void update(Product value) {
        for (int i = 0; i < value.getProductPhotos().size(); i++) {
            if (value.getProductPhotos().get(i).getId() != null) {
                productPhotoDao.update(value.getProductPhotos().get(i));
            } else {
                productPhotoDao.create(value.getProductPhotos().get(i));
            }
        }

        super.update(value);
    }

    @Override
    public List<Product> getTopFive() {
        return productDao.getTopFive();
    }

    @Override
    public List<Product> searchProduct(String searchRequest) {
        return productDao.searchProduct(searchRequest);
    }
}
