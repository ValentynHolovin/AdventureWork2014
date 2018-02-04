package com.akvelon.server.dao.api;

import com.akvelon.server.domain.Culture;

/**
 * Standard CRUD operation with Culture objects. The user of this interface
 * can create, read, update, delete Culture objects from the database.
 * @see com.akvelon.server.dao.api.Dao
 */
public interface CultureDao extends Dao<String, Culture> {
}
