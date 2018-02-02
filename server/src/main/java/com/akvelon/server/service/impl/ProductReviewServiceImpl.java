package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.dao.api.ProductReviewDao;
import com.akvelon.server.domain.ProductReview;
import com.akvelon.server.service.api.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewServiceImpl extends SuperService<ProductReview> implements ProductReviewService {
    @Autowired
    private ProductReviewDao productReviewDao;

    @Override
    public Dao<Integer, ProductReview> getRepository() {
        return productReviewDao;
    }
}
