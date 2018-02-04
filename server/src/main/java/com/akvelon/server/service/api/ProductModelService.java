package com.akvelon.server.service.api;

import com.akvelon.server.domain.ProductModel;

/**
 * Standard services operations with ProductModel objects through ProductModelDAO objects. The user of this interface
 * can create, read, update, delete domain objects from the database through ProductModelDAO objects.
 * @see com.akvelon.server.service.api.Service
 */
public interface ProductModelService extends Service<Integer, ProductModel> {
}
