package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.ProductReviewDao;
import com.akvelon.server.domain.ProductReview;
import com.akvelon.server.service.api.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReviewServiceImpl extends SuperService<ProductReview> implements ProductReviewService {
    private static ProductReviewServiceImpl productReviewService;

    @Autowired
    protected ProductReviewServiceImpl(ProductReviewDao dao) {
        super(dao);
        if (productReviewService == null) {
            productReviewService = this;
        }
    }
}
