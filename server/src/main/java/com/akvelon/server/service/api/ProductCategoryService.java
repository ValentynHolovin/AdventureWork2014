package com.akvelon.server.service.api;

import com.akvelon.server.domain.ProductCategory;

/**
 * Standard services operations with ProductCategory objects through ProductCategoryDAO objects.
 * The user of this interface can create, read, update, delete domain objects from the database
 * through ProductCategoryDAO objects.
 * @see com.akvelon.server.service.api.Service
 */
public interface ProductCategoryService extends Service<Integer, ProductCategory> {
}
