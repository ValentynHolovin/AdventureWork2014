package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.ProductCategoryDao;
import com.akvelon.server.dao.impl.SuperStringDao;
import com.akvelon.server.domain.ProductCategory;
import com.akvelon.server.service.api.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryServiceImpl extends SuperService<ProductCategory> implements ProductCategoryService {
    private static ProductCategoryServiceImpl productCategoryService;
    @Autowired
    private ProductCategoryDao productCategoryDao;

    protected ProductCategoryServiceImpl() {
        super(productCategoryDao);
        if (productCategoryService == null) {
            productCategoryService = this;
        }
    }

    public static synchronized ProductCategoryService getInstance() {
        if (productCategoryService == null) {
            productCategoryService = new ProductCategoryServiceImpl();
        }

        return productCategoryService;
    }


}
