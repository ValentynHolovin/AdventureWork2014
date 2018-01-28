package com.akvelon.server.dao.impl;

import com.akvelon.server.model.Culture;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class CultureDaoImpl extends SuperStringDao<Culture> {
    private static CultureDaoImpl cultureDao;
    private static RowMapper<Culture> rowMapper;

    private final String SQL_INSERT = "INSERT INTO culture (CultureID, Name) values (?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE culture SET CultureID = ?, Name = ? WHERE CultureID = ?";

    protected CultureDaoImpl() {
        super(new Culture());
        if (cultureDao == null) {
            cultureDao = this;

            rowMapper = (ResultSet rs, int conNum) -> {
                Culture culture = new Culture();

                culture.setId(rs.getString("CultureID"));
                culture.setName(rs.getString("Name"));

                return culture;
            };
        }
    }

    public static synchronized CultureDaoImpl getInstance() {
        if (cultureDao == null) {
            cultureDao = new CultureDaoImpl();
        }

        return cultureDao;
    }

    @Override
    protected RowMapper getRowMapper() {
        return rowMapper;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Culture value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT);

        ps.setString(1, value.getId());
        ps.setString(2, value.getName());

        return ps;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Culture value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setString(1, value.getId());
        ps.setString(2, value.getName());
        ps.setString(3, value.getId());

        return ps;
    }
}
