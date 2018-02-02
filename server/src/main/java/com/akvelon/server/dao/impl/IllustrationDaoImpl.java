package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.IllustrationDao;
import com.akvelon.server.domain.Illustration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class IllustrationDaoImpl extends SuperDao<Illustration> implements IllustrationDao {
    private static IllustrationDaoImpl illustrationDao;
    private static RowMapper<Illustration> rowMapper;

    private final String SQL_INSERT = "INSERT INTO illustration (Diagram) values (?) ON DUPLICATE KEY UPDATE Diagram = Diagram";
    private final String SQL_UPDATE = "UPDATE illustration SET Diagram = ? WHERE IllustrationID = ?";

    protected IllustrationDaoImpl() {
        super(new Illustration());
        if (illustrationDao == null) {
            illustrationDao = this;

            rowMapper = (ResultSet rs, int conNum) -> {
                Illustration illustration = new Illustration();

                illustration.setId(rs.getInt("IllustrationID"));
                illustration.setDiagram(rs.getString("Diagram"));

                return illustration;
            };
        }
    }

    @Override
    protected RowMapper getRowMapper() {
        return rowMapper;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Illustration value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, value.getDiagram());

        return ps;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Illustration value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setString(1, value.getDiagram());
        ps.setInt(2, value.getId());

        return ps;
    }
}
