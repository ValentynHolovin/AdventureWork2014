package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.ProductSubcategoryDao;
import com.akvelon.server.domain.ProductSubcategory;
import com.akvelon.server.service.api.ProductSubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductSubcategoryServiceImpl extends SuperService<ProductSubcategory> implements ProductSubcategoryService {
    private static ProductSubcategoryServiceImpl productSubcategoryService;

    @Autowired
    protected ProductSubcategoryServiceImpl(ProductSubcategoryDao dao) {
        super(dao);
        if (productSubcategoryService == null) {
            productSubcategoryService = this;
        }
    }
}
