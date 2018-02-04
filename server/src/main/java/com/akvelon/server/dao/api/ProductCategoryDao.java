package com.akvelon.server.dao.api;

import com.akvelon.server.domain.ProductCategory;

/**
 * Standard CRUD operation with ProductCategory objects. The user of this interface
 * can create, read, update, delete ProductCategory objects from the database.
 * @see com.akvelon.server.dao.api.Dao
 */
public interface ProductCategoryDao extends Dao<Integer, ProductCategory> {
}
