package com.akvelon.server.dao.impl;

import com.akvelon.server.model.Illustration;
import com.akvelon.server.model.ProductDescription;
import com.akvelon.server.model.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductModelDaoImpl extends SuperDao<ProductModel> {
    private static ProductModelDaoImpl productModelDao;
    private static RowMapper<ProductModel> rowMapper;
    @Autowired
    private IllustrationDaoImpl illustrationDao;
    @Autowired
    private ProductDescriptionDaoImpl productDescriptionDao;

    private final String SQL_INSERT = "INSERT INTO productmodel (Name, CatalogDescription, Instructions, rowguid) values (?, ?, ?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE productdescription SET ProductCategoryID = ?, Name = ?, rowguid = ? WHERE ProductDescriptionID = ?";
    private final String SQL_GET_ILLUSTRATIONS = "SELECT IllustrationID FROM productmodelillustration WHERE ProductModelID = ?";
    private final String SQL_GET_DESCRIPTIONS = "SELECT ProductDescriptionID FROM productmodelproductdescriptionculture WHERE ProductModelID = ?";

    protected ProductModelDaoImpl() {
        super(new ProductModel());
        if (productModelDao == null) {
            productModelDao = this;

            rowMapper = (ResultSet rs, int conNum) -> {
                ProductModel productModel = new ProductModel();

                productModel.setId(rs.getInt("ProductModelID"));
                productModel.setName(rs.getString("Name"));
                productModel.setCatalogDescription(rs.getString("CatalogDescription"));
                productModel.setInstruction(rs.getString("Instructions"));
                productModel.setRowguid(rs.getString("rowguid"));

                List<Integer> illustrationIDList = this.jdbcTemplate.queryForList(SQL_GET_ILLUSTRATIONS, new Object[] {productModel.getId()}, Integer.class);
                List<Integer> descriptionIDList = this.jdbcTemplate.queryForList(SQL_GET_DESCRIPTIONS, new Object[] {productModel.getId()}, Integer.class);
                List<Illustration> illustrations = new ArrayList<>();
                List<ProductDescription> productDescriptions = new ArrayList<>();

                for (Integer id : illustrationIDList) {
                    illustrations.add(illustrationDao.read(id));
                }

                for (Integer id : descriptionIDList) {
                    productDescriptions.add(productDescriptionDao.read(id));
                }

                productModel.setIllustrations(illustrations);
                productModel.setProductDescriptions(productDescriptions);

                return productModel;
            };
        }
    }

    public static synchronized ProductModelDaoImpl getInstance() {
        if (productModelDao == null) {
            productModelDao = new ProductModelDaoImpl();
        }

        return productModelDao;
    }

    @Override
    protected RowMapper getRowMapper() {
        return rowMapper;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, ProductModel value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        return null;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, ProductModel value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
        return null;
    }
}
