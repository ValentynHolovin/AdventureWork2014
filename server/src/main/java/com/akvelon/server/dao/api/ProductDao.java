package com.akvelon.server.dao.api;

import com.akvelon.server.domain.Product;

import java.util.List;

public interface ProductDao extends Dao<Integer, Product> {
    public List<Product> getTopFive();

    public List<Product> searchProduct(String searchRequest);
}
