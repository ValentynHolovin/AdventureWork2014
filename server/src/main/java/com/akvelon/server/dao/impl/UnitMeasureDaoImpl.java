package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.UnitMeasureDao;
import com.akvelon.server.domain.UnitMeasure;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Implementation of the abstract class of SuperDao
 * and the interface UnitMeasureDao for the domain object UnitMeasure.
 * @see com.akvelon.server.dao.impl.SuperDao
 * @see com.akvelon.server.dao.api.UnitMeasureDao
 */
@Repository
public class UnitMeasureDaoImpl extends SuperDao<String, UnitMeasure> implements UnitMeasureDao {
    private final String SQL_INSERT = "INSERT INTO unitmeasure (UnitMeasureCode, Name) VALUES (?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE unitmeasure SET UnitMeasureCode = ?, Name = ? WHERE UnitMeasureCode = ?";

    @Override
    protected UnitMeasure getClassObject() {
        return new UnitMeasure();
    }

    @Override
    protected void setId(UnitMeasure value, KeyHolder keyHolder) {
        // do noting
    }

    @Override
    public UnitMeasure read(String key) {
        return readBy("UnitMeasureCode", key);
    }

    @Override
    protected RowMapper<UnitMeasure> getRowMapper() {
        return (ResultSet rs, int conNum) -> {
            UnitMeasure unitMeasure = new UnitMeasure();

            unitMeasure.setId(rs.getString("UnitMeasureCode"));
            unitMeasure.setName(rs.getString("Name"));

            return unitMeasure;
        };
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, UnitMeasure value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, value.getId());
        ps.setString(2, value.getName());

        return ps;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, UnitMeasure value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setString(1, value.getId());
        ps.setString(2, value.getName());
        ps.setString(3, value.getId());

        return ps;
    }
}
