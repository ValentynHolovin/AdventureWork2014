package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.domain.Entity;
import com.akvelon.server.service.api.Service;

import java.util.List;

@org.springframework.stereotype.Service
public abstract class SuperStringService<V extends Entity<String>> implements Service<String, V> {
    protected Dao<String, V> dao;

    public SuperStringService() {
    }

    public SuperStringService(Dao<String, V> dao) {
        this.dao = dao;
    }

    @Override
    public List<V> getAll() {
        return dao.getAll();
    }

    @Override
    public String create(V value) {
        return dao.create(value);
    }

    @Override
    public V read(String key) {
        return dao.read(key);
    }

    @Override
    public void update(V value) {
        dao.update(value);
    }

    @Override
    public void delete(String key) {
        dao.delete(key);
    }

    @Override
    public <T> V readBy(String fieldName, T value) {
        return dao.readBy(fieldName, value);
    }

    @Override
    public <T> List<V> readAllBy(String fieldName, T value) {
        return dao.readAllBy(fieldName, value);
    }
}
