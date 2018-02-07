package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.dao.api.ProductDao;
import com.akvelon.server.dao.api.ProductPhotoDao;
import com.akvelon.server.domain.Product;
import com.akvelon.server.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the class of SuperService
 * and the interface ProductService for the domain object Product.
 * @see com.akvelon.server.service.api.ProductService
 * @see com.akvelon.server.service.impl.SuperService
 */
@Service
public class ProductServiceImpl extends SuperService<Integer, Product> implements ProductService{
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductPhotoDao productPhotoDao;

    @Override
    public Integer create(Product value) {
        if (value.getProductPhotos() == null) {
            value.setProductPhotos(productPhotoDao.readAllBy("ProductPhotoID", 1));
        } else {
            for (int i = 0; i < value.getProductPhotos().size(); i++) {
                productPhotoDao.create(value.getProductPhotos().get(i));
            }
        }


        return super.create(value);
    }

    @Override
    public Integer update(Product value) {
        if (value.getProductPhotos() != null) {
            for (int i = 0; i < value.getProductPhotos().size(); i++) {
                if (value.getProductPhotos().get(i).getId() != null) {
                    productPhotoDao.update(value.getProductPhotos().get(i));
                } else {
                    productPhotoDao.create(value.getProductPhotos().get(i));
                }
            }
        } else {
            value.setProductPhotos(productPhotoDao.readAllBy("ProductPhotoID", 1));
        }

        return super.update(value);
    }

    @Override
    public List<Product> getTopFive() {
        return productDao.getTopFive();
    }

    @Override
    public List<Product> searchProduct(String searchRequest, Integer count) {
        List<Product> products = productDao.searchProduct(searchRequest);
        List<Product> result = new ArrayList<>();

        for (int i = 0; i < (count + 1) * 20; i++) {
            if (i < products.size()) {
                result.add(products.get(i));
            } else {
                break;
            }
        }

        return result;
    }

    @Override
    public Dao<Integer, Product> getRepository() {
        return productDao;
    }
}
