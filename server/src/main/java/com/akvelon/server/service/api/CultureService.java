package com.akvelon.server.service.api;

import com.akvelon.server.domain.Culture;

/**
 * Standard services operations with Culture objects through CultureDAO objects. The user of this interface
 * can create, read, update, delete domain objects from the database through CultureDAO objects.
 * @see com.akvelon.server.service.api.Service
 */
public interface CultureService extends Service<String, Culture> {
}
