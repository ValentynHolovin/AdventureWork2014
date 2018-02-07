package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.ProductCategoryDao;
import com.akvelon.server.dao.api.ProductSubcategoryDao;
import com.akvelon.server.domain.ProductCategory;
import com.akvelon.server.domain.ProductSubcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;


/**
 * Implementation of the abstract class of SuperDao
 * and the interface ProductCategoryDao for the domain object ProductCategory.
 * @see com.akvelon.server.dao.impl.SuperDao
 * @see com.akvelon.server.dao.api.ProductCategoryDao
 */
@Repository
public class ProductCategoryDaoImpl extends SuperDao<Integer, ProductCategory> implements ProductCategoryDao {

    private final String SQL_INSERT = "INSERT INTO productcategory (Name, rowguid) values (?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE productcategory SET Name = ?, rowguid = ? WHERE ProductCategoryID = ?";

    @Override
    protected ProductCategory getClassObject() {
        return new ProductCategory();
    }

    @Override
    protected void setId(ProductCategory value, KeyHolder keyHolder) {
        value.setId(keyHolder.getKey().intValue());
    }

    @Override
    protected RowMapper<ProductCategory> getRowMapper() {
        return (ResultSet rs, int conNum) -> {
            ProductCategory productCategory = new ProductCategory();

            productCategory.setId(rs.getInt("ProductCategoryID"));
            productCategory.setName(rs.getString("Name"));
            productCategory.setRowguid(rs.getString("rowguid"));

            return productCategory;
        };
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, ProductCategory value) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, value.getName());
        preparedStatement.setString(2, value.getRowguid());

        return preparedStatement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, ProductCategory value) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);

        preparedStatement.setString(1, value.getName());
        preparedStatement.setString(2, value.getRowguid());
        preparedStatement.setInt(3, value.getId());

        return preparedStatement;
    }
}
