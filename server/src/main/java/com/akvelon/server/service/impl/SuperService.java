package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.Dao;
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
public class SuperService<V extends Entity<Integer>> implements Service<Integer, V> {
    private Dao<Integer, V> dao;

    @Override
    public List<V> getAll() {
        return getRepository().getAll();
    }

    @Override
    public Integer create(V value) {
        return getRepository().create(value);
    }

    @Override
    public V read(Integer key) {
        return getRepository().read(key);
    }

    @Override
    public void update(V value) {
        getRepository().update(value);
    }

    @Override
    public void delete(Integer key) {
        getRepository().delete(key);
    }

    @Override
    public <T> V readBy(String fieldName, T value) {
        return getRepository().readBy(fieldName, value);
    }

    @Override
    public <T> List<V> readAllBy(String fieldName, T value) {
        return getRepository().readAllBy(fieldName, value);
    }

    @Override
    public Dao<Integer, V> getRepository() {
        return dao;
    }
}
