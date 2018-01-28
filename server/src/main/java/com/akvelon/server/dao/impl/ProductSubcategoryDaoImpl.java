package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.DaoFactory;
import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.model.ProductCategory;
import com.akvelon.server.model.ProductSubcategory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ProductSubcategoryDaoImpl extends SuperDao<ProductSubcategory> {
    private static ProductSubcategoryDaoImpl productSubcategoryDao;
    private static RowMapper<ProductSubcategory> rowMapper;
    private Dao<Integer, ProductCategory> productCategoryDao;

    private final String SQL_INSERT = "INSERT INTO productsubcategory (ProductCategoryID, Name, rowguid) values (?, ?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE productsubcategory SET ProductCategoryID = ?, Name = ?, rowguid = ? WHERE ProductSubcategoryID = ?";

    protected ProductSubcategoryDaoImpl() {
        super(new ProductSubcategory());
        if (productSubcategoryDao == null) {
            productSubcategoryDao = this;
            productCategoryDao = DaoFactory.getInstance().getProductCategoryDao();

            rowMapper = (ResultSet rs, int conNum) -> {
                ProductSubcategory productSubcategory = new ProductSubcategory();

                productSubcategory.setId(rs.getInt("ProductSubcategoryID"));
                productSubcategory.setCategory(productCategoryDao.read(rs.getInt("ProductCategoryID")));
                productSubcategory.setName(rs.getString("Name"));
                productSubcategory.setRowguid(rs.getString("rowguid"));

                return productSubcategory;
            };
        }
    }

    public static synchronized ProductSubcategoryDaoImpl getInstance() {
        if (productSubcategoryDao == null) {
            productSubcategoryDao = new ProductSubcategoryDaoImpl();
        }

        return productSubcategoryDao;
    }

    @Override
    protected RowMapper getRowMapper() {
        return rowMapper;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, ProductSubcategory value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, value.getCategory().getId());
        ps.setString(2, value.getName());
        ps.setString(3, value.getRowguid());

        return ps;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, ProductSubcategory value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setInt(1, value.getCategory().getId());
        ps.setString(2, value.getName());
        ps.setString(3, value.getRowguid());
        ps.setInt(4, value.getId());

        return ps;
    }
}
