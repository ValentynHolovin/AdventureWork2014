package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.UnitMeasureDao;
import com.akvelon.server.domain.UnitMeasure;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UnitMeasureDaoImpl extends SuperStringDao<UnitMeasure> implements UnitMeasureDao {
    private static UnitMeasureDaoImpl unitMeasureDao;
    private static RowMapper<UnitMeasure> rowMapper;

    private final String SQL_INSERT = "INSERT INTO unitmeasure (UnitMeasureCode, Name) VALUES (?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE unitmeasure SET UnitMeasureCode = ?, Name = ? WHERE UnitMeasureCode = ?";

    protected UnitMeasureDaoImpl() {
        super(new UnitMeasure());
        if (unitMeasureDao == null) {
            unitMeasureDao = this;

            rowMapper = (ResultSet rs, int conNum) -> {
                UnitMeasure unitMeasure = new UnitMeasure();

                unitMeasure.setId(rs.getString("UnitMeasureCode"));
                unitMeasure.setName(rs.getString("Name"));

                return unitMeasure;
            };
        }
    }

    @Override
    public UnitMeasure read(String key) {
        return readBy("UnitMeasureCode", key);
    }

    @Override
    protected RowMapper getRowMapper() {
        return rowMapper;
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
