package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.DaoFactory;
import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.model.Culture;
import com.akvelon.server.model.ProductDescription;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProductDescriptionDaoImpl extends SuperDao<ProductDescription> {
    private static ProductDescriptionDaoImpl productDescriptionDao;
    private static RowMapper<ProductDescription> rowMapper;
    private Dao<String, Culture> cultureDao;

    private final String SQL_INSERT = "INSERT INTO productsubcategory (ProductCategoryID, Name, rowguid) values (?, ?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE productsubcategory SET ProductCategoryID = ?, Name = ?, rowguid = ? WHERE ProductSubcategoryID = ?";

    protected ProductDescriptionDaoImpl() {
        super(new ProductDescription());
        if (productDescriptionDao == null) {
            productDescriptionDao = this;
            cultureDao = DaoFactory.getInstance().getCultureDao();

            rowMapper = (ResultSet rs, int conNum) -> {
                ProductDescription productDescription = new ProductDescription();

                productDescription.setId(rs.getInt("ProductDescriptionID"));
                productDescription.setDescription(rs.getString("Description"));
                productDescription.setRowguid(rs.getString("rowguid"));

                return productDescription;
            };
        }
    }

    public static synchronized ProductDescriptionDaoImpl getInstance() {
        if (productDescriptionDao == null) {
            productDescriptionDao = new ProductDescriptionDaoImpl();
        }

        return productDescriptionDao;
    }

    @Override
    protected RowMapper getRowMapper() {
        return rowMapper;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, ProductDescription value) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, ProductDescription value) throws SQLException {
        return null;
    }
}
