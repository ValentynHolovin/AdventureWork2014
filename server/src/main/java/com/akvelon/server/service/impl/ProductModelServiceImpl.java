package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.*;
import com.akvelon.server.domain.ProductModel;
import com.akvelon.server.service.api.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementation of the class of SuperService
 * and the interface ProductModelService for the domain object ProductModel.
 * @see com.akvelon.server.service.api.ProductModelService
 * @see com.akvelon.server.service.impl.SuperService
 */
@Service
public class ProductModelServiceImpl extends SuperService<Integer, ProductModel> implements ProductModelService {
    @Autowired
    ProductModelDao productModelDao;
    @Autowired
    private IllustrationDao illustrationDao;
    @Autowired
    private ProductDescriptionDao productDescriptionDao;

    @Override
    public Integer create(ProductModel value) {
        for (int i = 0; i < value.getIllustrations().size(); i++) {
            illustrationDao.create(value.getIllustrations().get(i));
        }
        for (int i = 0; i < value.getProductDescriptions().size(); i++) {
            productDescriptionDao.create(value.getProductDescriptions().get(i));
        }

        return super.create(value);
    }

    @Override
    public Integer update(ProductModel value) {
        for (int i = 0; i < value.getIllustrations().size(); i++) {
            if (value.getIllustrations().get(i).getId() != null) {
                illustrationDao.update(value.getIllustrations().get(i));
            } else {
                illustrationDao.create(value.getIllustrations().get(i));
            }
        }
        for (int i = 0; i < value.getProductDescriptions().size(); i++) {
            if (value.getProductDescriptions().get(i).getId() != null) {
                productDescriptionDao.update(value.getProductDescriptions().get(i));
            } else {
                productDescriptionDao.create(value.getProductDescriptions().get(i));
            }
        }
        return super.update(value);
    }

    @Override
    public Dao<Integer, ProductModel> getRepository() {
        return productModelDao;
    }
}
