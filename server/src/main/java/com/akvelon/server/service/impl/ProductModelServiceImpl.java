package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.IllustrationDao;
import com.akvelon.server.dao.api.ProductDescriptionDao;
import com.akvelon.server.dao.api.ProductModelDao;
import com.akvelon.server.domain.ProductModel;
import com.akvelon.server.service.api.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductModelServiceImpl extends SuperService<ProductModel> implements ProductModelService {
    private static ProductModelServiceImpl productModelService;
    @Autowired
    private IllustrationDao illustrationDao;
    @Autowired
    private ProductDescriptionDao productDescriptionDao;

    @Autowired
    protected ProductModelServiceImpl(ProductModelDao dao) {
        super(dao);
        if (productModelService == null) {
            productModelService = this;
        }
    }

    public static synchronized ProductModelServiceImpl getInstance() {
        return productModelService;
    }

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
    public void update(ProductModel value) {
        for (int i = 0; i < value.getIllustrations().size(); i++) {
            if (value.getIllustrations().get(i).getId() != null) {
                illustrationDao.update(value.getIllustrations().get(i));
            } else {
                illustrationDao.create(value.getIllustrations().get(i));
            }
        }
        for (int i = 0; i < value.getProductDescriptions().size(); i++) {
            if (value.getProductDescriptions().get(i) != null) {
                productDescriptionDao.update(value.getProductDescriptions().get(i));
            } else {
                productDescriptionDao.create(value.getProductDescriptions().get(i));
            }
        }
        super.update(value);
    }
}
