package com.akvelon.server.dao.api;

import com.akvelon.server.domain.ProductModel;

/**
 * Standard CRUD operation with ProductModel objects. The user of this interface
 * can create, read, update, delete ProductModel objects from the database.
 * @see com.akvelon.server.dao.api.Dao
 */
public interface ProductModelDao extends Dao<Integer, ProductModel> {
}
