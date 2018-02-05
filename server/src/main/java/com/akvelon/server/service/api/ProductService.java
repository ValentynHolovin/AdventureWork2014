package com.akvelon.server.service.api;

import com.akvelon.server.domain.Product;

import java.util.List;

/**
 * Standard services operations with Product objects through ProductDAO objects. The user of this interface
 * can create, read, update, delete domain objects from the database through ProductDAO objects.
 * @see com.akvelon.server.service.api.Service
 */
public interface ProductService extends Service<Integer, Product> {
    /**
     * Read TOP FIVE bikes from the database through ProductDAO objects.
     * Top five bikes: five bikes for which the largest transaction is registered with type S(Sales).
     * @return read Product values in the List format.
     */
    List<Product> getTopFive();

    /**
     * Reads Product objects from a database through ProductDAO objects,
     * whose name contains a searchRequest.
     * @param searchRequest search request string.
     * @param count number of 20 items to return.
     * @return read Product values in the List format.
     */
    List<Product> searchProduct(String searchRequest, Integer count);
}
