package com.akvelon.server.service.api;

import com.akvelon.server.domain.Product;

import java.util.List;

public interface ProductService extends Service<Integer, Product> {
    public List<Product> getTopFive();

    public List<Product> searchProduct(String searchRequest);
}
