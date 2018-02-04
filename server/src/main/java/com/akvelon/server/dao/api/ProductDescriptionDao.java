package com.akvelon.server.dao.api;

import com.akvelon.server.domain.ProductDescription;

/**
 * Standard CRUD operation with ProductDescription objects. The user of this interface
 * can create, read, update, delete ProductDescription objects from the database.
 * @see com.akvelon.server.dao.api.Dao
 */
public interface ProductDescriptionDao extends Dao<Integer, ProductDescription> {
}
