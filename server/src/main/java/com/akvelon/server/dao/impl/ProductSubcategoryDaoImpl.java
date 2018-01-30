package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.ProductCategoryDao;
import com.akvelon.server.dao.api.ProductSubcategoryDao;
import com.akvelon.server.domain.ProductSubcategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ProductSubcategoryDaoImpl extends SuperDao<ProductSubcategory> implements ProductSubcategoryDao {
    private static ProductSubcategoryDaoImpl productSubcategoryDao;
    private static RowMapper<ProductSubcategory> rowMapper;
    @Autowired
    private ProductCategoryDao productCategoryDao;

    private final String SQL_INSERT = "INSERT INTO productsubcategory (ProductCategoryID, Name, rowguid) values (?, ?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE productsubcategory SET ProductCategoryID = ?, Name = ?, rowguid = ? WHERE ProductSubcategoryID = ?";

    protected ProductSubcategoryDaoImpl() {
        super(new ProductSubcategory());
        if (productSubcategoryDao == null) {
            productSubcategoryDao = this;

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
