package com.akvelon.server.service.impl;

import com.akvelon.server.domain.Entity;
import com.akvelon.server.service.api.Service;

import java.util.List;

/**
 * Implementation of the Service interface. The class implements all CRUD operations for domain objects
 * through DAO objects, whose ID type is Integer.
 * Child classes must implement one abstract method from Service interface.
 * @param <V> type of domain object.
 */
@org.springframework.stereotype.Service
public abstract class SuperService<K, V extends Entity<K>> implements Service<K, V> {

    @Override
    public List<V> getAll() {
        return getRepository().getAll();
    }

    @Override
    public K create(V value) {
        return getRepository().create(value);
    }

    @Override
    public V read(K key) {
        return getRepository().read(key);
    }

    @Override
    public K update(V value) {
        getRepository().update(value);
        return value.getId();
    }

    @Override
    public K delete(K key) {
        getRepository().delete(key);
        return key;
    }

    @Override
    public <T> V readBy(String fieldName, T value) {
        return getRepository().readBy(fieldName, value);
    }

    @Override
    public <T> List<V> readAllBy(String fieldName, T value) {
        return getRepository().readAllBy(fieldName, value);
    }

}
