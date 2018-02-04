package com.akvelon.server.dao.api;

import com.akvelon.server.domain.ProductPhoto;

/**
 * Standard CRUD operation with ProductPhoto objects. The user of this interface
 * can create, read, update, delete ProductPhoto objects from the database.
 * @see com.akvelon.server.dao.api.Dao
 */
public interface ProductPhotoDao extends Dao<Integer, ProductPhoto> {
}
