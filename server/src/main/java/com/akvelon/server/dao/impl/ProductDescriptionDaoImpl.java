package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.CultureDao;
import com.akvelon.server.dao.api.ProductDescriptionDao;
import com.akvelon.server.domain.Culture;
import com.akvelon.server.domain.ProductDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Implementation of the abstract class of SuperDao
 * and the interface ProductDescriptionDao for the domain object ProductDescription.
 * @see com.akvelon.server.dao.impl.SuperDao
 * @see com.akvelon.server.dao.api.ProductDescriptionDao
 */
@Repository
public class ProductDescriptionDaoImpl extends SuperDao<ProductDescription> implements ProductDescriptionDao {
    @Autowired
    private CultureDao cultureDao;

    private final String SQL_INSERT = "INSERT INTO productdescription (Description, rowguid) values (?, ?) ON DUPLICATE KEY UPDATE Description = Description";
    private final String SQL_UPDATE = "UPDATE productdescription SET ProductCategoryID = ?, Name = ?, rowguid = ? WHERE ProductDescriptionID = ?";
    private final String SQL_GET_CULTURE = "SELECT CultureID FROM productmodelproductdescriptionculture WHERE ProductDescriptionID = ?";

    @Override
    protected ProductDescription getClassObject() {
        return new ProductDescription();
    }

    @Override
    protected RowMapper<ProductDescription> getRowMapper() {
        return (ResultSet rs, int conNum) -> {
            ProductDescription productDescription = new ProductDescription();

            productDescription.setId(rs.getInt("ProductDescriptionID"));
            productDescription.setDescription(rs.getString("Description"));
            productDescription.setRowguid(rs.getString("rowguid"));
            Culture culture = cultureDao.read(this.jdbcTemplate.queryForObject(SQL_GET_CULTURE, new Object[] {productDescription.getId()}, String.class));
            productDescription.setCulture(culture);

            return productDescription;
        };
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, ProductDescription value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, value.getDescription());
        ps.setString(2, value.getRowguid());

        return ps;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, ProductDescription value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setString(1, value.getDescription());
        ps.setString(2, value.getRowguid());
        ps.setInt(3, value.getId());

        return ps;
    }
}
