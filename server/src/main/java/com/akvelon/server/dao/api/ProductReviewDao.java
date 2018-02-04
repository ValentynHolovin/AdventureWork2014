package com.akvelon.server.dao.api;

import com.akvelon.server.domain.ProductReview;

/**
 * Standard CRUD operation with ProductReview objects. The user of this interface
 * can create, read, update, delete ProductReview objects from the database.
 * @see com.akvelon.server.dao.api.Dao
 */
public interface ProductReviewDao extends Dao<Integer, ProductReview> {
}
