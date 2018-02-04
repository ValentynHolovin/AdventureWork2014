package com.akvelon.server.dao.api;

import com.akvelon.server.domain.Product;

import java.util.List;

/**
 * Standard CRUD operation with Product objects. The user of this interface
 * can create, read, update, delete Product objects from the database.
 * @see com.akvelon.server.dao.api.Dao
 */
public interface ProductDao extends Dao<Integer, Product> {
    /**
     * Read TOP FIVE bikes from the database.
     * Top five bikes: five bikes for which the largest transaction is registered with type S(Sales).
     * @return read Product values in the List format.
     */
    public List<Product> getTopFive();

    /**
     * Reads Product objects from a database
     * whose name contains a searchRequest.
     * @param searchRequest search request string.
     * @return read Product values in the List format.
     */
    public List<Product> searchProduct(String searchRequest);
}
