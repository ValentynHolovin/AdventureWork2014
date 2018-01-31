package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.ProductCategoryDao;
import com.akvelon.server.domain.ProductCategory;
import com.akvelon.server.service.api.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductCategoryServiceImpl extends SuperService<ProductCategory> implements ProductCategoryService {
    private static ProductCategoryServiceImpl productCategoryService;

    @Autowired
    protected ProductCategoryServiceImpl(ProductCategoryDao dao) {
        super(dao);
        if (productCategoryService == null) {
            productCategoryService = this;
        }
    }

    public static synchronized ProductCategoryService getInstance() {
        return productCategoryService;
    }


}
