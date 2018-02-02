package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.dao.api.ProductCategoryDao;
import com.akvelon.server.domain.ProductCategory;
import com.akvelon.server.service.api.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductCategoryServiceImpl extends SuperService<ProductCategory> implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public Dao<Integer, ProductCategory> getRepository() {
        return productCategoryDao;
    }
}
