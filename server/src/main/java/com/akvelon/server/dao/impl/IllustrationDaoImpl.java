package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.IllustrationDao;
import com.akvelon.server.domain.Illustration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Implementation of the abstract class of SuperDao
 * and the interface IllustrationDao for the domain object Illustration.
 * @see com.akvelon.server.dao.impl.SuperDao
 * @see com.akvelon.server.dao.api.IllustrationDao
 */
@Repository
public class IllustrationDaoImpl extends SuperDao<Integer, Illustration> implements IllustrationDao {

    private final String SQL_INSERT = "INSERT INTO illustration (Diagram) values (?) ON DUPLICATE KEY UPDATE Diagram = Diagram";
    private final String SQL_UPDATE = "UPDATE illustration SET Diagram = ? WHERE IllustrationID = ?";

    @Override
    protected Illustration getClassObject() {
        return new Illustration();
    }

    @Override
    protected void setId(Illustration value, KeyHolder keyHolder) {
        value.setId(keyHolder.getKey().intValue());
    }

    @Override
    protected RowMapper<Illustration> getRowMapper() {
        return (ResultSet rs, int conNum) -> {
            Illustration illustration = new Illustration();

            illustration.setId(rs.getInt("IllustrationID"));
            illustration.setDiagram(rs.getString("Diagram"));

            return illustration;
        };
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
