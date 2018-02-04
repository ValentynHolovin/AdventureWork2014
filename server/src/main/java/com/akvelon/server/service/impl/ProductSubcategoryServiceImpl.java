package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.dao.api.ProductSubcategoryDao;
import com.akvelon.server.domain.ProductSubcategory;
import com.akvelon.server.service.api.ProductSubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the class of SuperService
 * and the interface ProductSubcategoryService for the domain object ProductSubcategory.
 * @see com.akvelon.server.service.api.ProductSubcategoryService
 * @see com.akvelon.server.service.impl.SuperService
 */
@Service
public class ProductSubcategoryServiceImpl extends SuperService<ProductSubcategory> implements ProductSubcategoryService {
    @Autowired
    private ProductSubcategoryDao productSubcategoryDao;

    @Override
    public Dao<Integer, ProductSubcategory> getRepository() {
        return productSubcategoryDao;
    }
}
