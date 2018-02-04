package com.akvelon.server.service.api;

import com.akvelon.server.dao.api.Dao;

import java.util.List;

/**
 * Standard services operations with domain objects through DAO objects. The user of this interface
 * can create, read, update, delete domain objects from the database through DAO objects.
 * @param <K> type of object ID (primary key).
 * @param <V> type of domain object.
 */
public interface Service<K, V> {

    /**
     * Reads all objects of type V from the database through DAO object.
     * @return read values in the List format.
     */
    List<V> getAll();

    /**
     * Create domain object in database table through DAO object.
     * @param value domain object.
     * @return generated key.
     */
    K create(V value);

    /**
     * Read domain object of type V from the database through DAO object.
     * @param key object ID (primary key).
     * @return read value.
     */
    V read(K key);

    /**
     * Update domain object of type V in database tables through DAO object.
     * @param value domain object.
     */
    void update(V value);

    /**
     * Delete domain object of type V from the database through DAO object.
     * @param key object ID (primary key).
     */
    void delete(K key);

    /**
     * Read the domain object of type V, which contains a value of type T in the field "fieldName".
     * @param fieldName field for search.
     * @param value sought value.
     * @param <T> type of sought value.
     * @return read value.
     */
    <T> V readBy(String fieldName, T value);

    /**
     * Reads all objects of type V from the database, which contains a value of type T in the field "fieldName".
     * @param fieldName field for search.
     * @param value sought value.
     * @param <T> type of sought value.
     * @return read values in the List format.
     */
    <T> List<V> readAllBy(String fieldName, T value);

    /**
     * Return DAO object for domain object of type V.
     */
    Dao<K, V> getRepository();
}
