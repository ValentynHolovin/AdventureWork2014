package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.ProductCategoryDao;
import com.akvelon.server.domain.ProductCategory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;


@Repository
public class ProductCategoryDaoImpl extends SuperDao<ProductCategory> implements ProductCategoryDao {
    private static ProductCategoryDaoImpl productCategoryDao;
    private static RowMapper<ProductCategory> rowMapper;

    private final String SQL_INSERT = "INSERT INTO productcategory (Name, rowguid) values (?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE productcategory SET Name = ?, rowguid = ? WHERE ProductCategoryID = ?";

    protected ProductCategoryDaoImpl() {
        super(new ProductCategory());
        if (productCategoryDao == null) {
            productCategoryDao = this;

            rowMapper = (ResultSet rs, int conNum) -> {
                ProductCategory productCategory = new ProductCategory();

                productCategory.setId(rs.getInt("ProductCategoryID"));
                productCategory.setName(rs.getString("Name"));
                productCategory.setRowguid(rs.getString("rowguid"));

                return productCategory;
            };
        }
    }

    @Override
    protected RowMapper getRowMapper() {
        return rowMapper;
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
