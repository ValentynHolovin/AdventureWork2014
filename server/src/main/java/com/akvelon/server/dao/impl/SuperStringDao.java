package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public abstract class SuperStringDao<V extends Entity<String>> implements Dao<String, V> {
    private V obj;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_ALL = "SELECT * FROM %s";
    private static final String SQL_GET_BY = "SELECT * FROM %s WHERE %s = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM %s WHERE %s = ?";

    public SuperStringDao() {
    }

    public SuperStringDao(V obj) {
        this.obj = obj;
    }

    @Override
    public List<V> getAll() {
        String sql = String.format(SQL_SELECT_ALL, obj.getClass().getSimpleName().toLowerCase());

        return jdbcTemplate.query(sql, getRowMapper());
    }

    @Override
    public String create(V value) {
        jdbcTemplate.update((con) -> createInsertStatement(con, value));

        return value.getId();
    }

    @Override
    public V read(String key) {
        return readBy(obj.getClass().getSimpleName() + "ID", key);
    }

    @Override
    public void update(V value) {
        jdbcTemplate.update((con) -> createUpdateStatement(con, value));
    }

    @Override
    public void delete(String key) {
        String sql = String.format(SQL_DELETE_BY_ID, obj.getClass().getSimpleName().toLowerCase(), obj.getClass().getSimpleName() + "ID");

        jdbcTemplate.update(sql, key);
    }

    @Override
    public <T> V readBy(String fieldName, T value) {
        List<V> result = readAllBy(fieldName, value);

        if (result == null) {
            return null;
        }

        return result.get(0);
    }

    @Override
    public <T> List<V> readAllBy(String fieldName, T value) {
        String sql = String.format(SQL_GET_BY, obj.getClass().getSimpleName().toLowerCase(), fieldName);

        return jdbcTemplate.query(sql, new Object[]{value}, getRowMapper());
    }

    protected abstract RowMapper getRowMapper();

    protected abstract PreparedStatement createInsertStatement(Connection connection, V value) throws SQLException;

    protected abstract PreparedStatement createUpdateStatement(Connection connection, V value) throws SQLException;
}
