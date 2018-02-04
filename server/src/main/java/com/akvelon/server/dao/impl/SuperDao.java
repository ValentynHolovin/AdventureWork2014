package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the DAO interface. The class implements all CRUD operations for domain objects
 * whose ID type is Integer.
 * Child classes must implement three abstract methods from this class.
 * @param <V> type of domain object.
 */
@Repository
public abstract class SuperDao<V extends Entity<Integer>> implements Dao<Integer, V> {
    private V obj;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_ALL = "SELECT * FROM %s";
    private static final String SQL_GET_BY = "SELECT * FROM %s WHERE %s = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM %s WHERE %s = ?";

    public SuperDao() {
    }

    public SuperDao(V obj) {
        this.obj = obj;
    }

    @Override
    public List<V> getAll() {
        String sql = String.format(SQL_SELECT_ALL, obj.getClass().getSimpleName().toLowerCase());

        return jdbcTemplate.query(sql, getRowMapper());
    }

    @Override
    public Integer create(V value) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((con) -> createInsertStatement(con, value), keyHolder);

        int id = keyHolder.getKey().intValue();
        value.setId(id);

        return id;
    }

    @Override
    public V read(Integer key) {
        return readBy(obj.getClass().getSimpleName() + "ID", key);
    }

    @Override
    public void update(V value) {
        jdbcTemplate.update((con) -> createUpdateStatement(con, value));
    }

    @Override
    public void delete(Integer key) {
        String sql = String.format(SQL_DELETE_BY_ID, obj.getClass().getSimpleName().toLowerCase(), obj.getClass().getSimpleName() + "ID");

        jdbcTemplate.update(sql, key);
    }

    @Override
    public <T> V readBy(String fieldName, T value) {
        List<V> result = readAllBy(fieldName, value);

        if (result == null || result.size() == 0) {
            return null;
        }

        return result.get(0);
    }

    @Override
    public <T> List<V> readAllBy(String fieldName, T value) {
        String sql = String.format(SQL_GET_BY, obj.getClass().getSimpleName().toLowerCase(), fieldName);

        return jdbcTemplate.query(sql, new Object[]{value}, getRowMapper());
    }

    /**
     * Return RowMapper object for domain object.
     */
    protected abstract RowMapper<V> getRowMapper();

    /**
     * Create a PreparedStatement with an INSERT request for the domain object.
     * @param connection sql database connection
     * @param value domain object.
     * @return PreparedStatement with an INSERT request for the domain object.
     * @throws SQLException
     */
    protected abstract PreparedStatement createInsertStatement(Connection connection, V value) throws SQLException;

    /**
     * Create a PreparedStatement with an UPDATE request for the domain object.
     * @param connection sql database connection
     * @param value domain object.
     * @return PreparedStatement with an UPDATE request for the domain object.
     * @throws SQLException
     */
    protected abstract PreparedStatement createUpdateStatement(Connection connection, V value) throws SQLException;
}
