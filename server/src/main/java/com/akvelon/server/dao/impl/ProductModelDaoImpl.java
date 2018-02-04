package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.IllustrationDao;
import com.akvelon.server.dao.api.ProductDescriptionDao;
import com.akvelon.server.dao.api.ProductModelDao;
import com.akvelon.server.domain.Illustration;
import com.akvelon.server.domain.ProductDescription;
import com.akvelon.server.domain.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the abstract class of SuperDao
 * and the interface ProductModelDao for the domain object ProductModel.
 * @see com.akvelon.server.dao.impl.SuperDao
 * @see com.akvelon.server.dao.api.ProductModelDao
 */
@Repository
public class ProductModelDaoImpl extends SuperDao<ProductModel> implements ProductModelDao {
    private static ProductModelDaoImpl productModelDao;
    private static RowMapper<ProductModel> rowMapper;
    @Autowired
    private IllustrationDao illustrationDao;
    @Autowired
    private ProductDescriptionDao productDescriptionDao;

    private final String SQL_INSERT = "INSERT INTO productmodel (Name, CatalogDescription, Instructions, rowguid) values (?, ?, ?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE productmodel SET Name = ?, CatalogDescription = ?, Instructions = ?, rowguid = ? WHERE ProductModelID = ?";
    private final String SQL_INSERT_PRODUCTMODELPRODUCTILLUSTRATION = "INSERT INTO productmodelillustration (ProductModelID, IllustrationID) values (?, ?) ON DUPLICATE KEY UPDATE IllustrationID = IllustrationID";
    private final String SQL_INSERT_PRODUCTMODELPRODUCTDESCRIPTIONCULTURE = "INSERT INTO productmodelproductdescriptionculture (ProductModelID, ProductDescriptionID, CultureID) valuse (?, ?, ?) ON DUPLICATE KEY UPDATE ProductDescriptionID = ProductDescriptionID";
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

    @Override
    public Integer create(ProductModel value) {
        Integer id = super.create(value);

        for (Illustration illustration : value.getIllustrations()) {
            this.jdbcTemplate.update(SQL_INSERT_PRODUCTMODELPRODUCTILLUSTRATION, value.getId(), illustration.getId());
        }

        for (ProductDescription productDescription : value.getProductDescriptions()) {
            this.jdbcTemplate.update(SQL_INSERT_PRODUCTMODELPRODUCTDESCRIPTIONCULTURE, value.getId(), productDescription.getId(), productDescription.getCulture().getId());
        }

        return id;
    }

    @Override
    protected RowMapper<ProductModel> getRowMapper() {
        return rowMapper;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, ProductModel value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, value.getName());
        ps.setString(2, value.getCatalogDescription());
        ps.setString(3, value.getInstruction());
        ps.setString(4, value.getRowguid());

        return ps;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, ProductModel value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setString(1, value.getName());
        ps.setString(2, value.getCatalogDescription());
        ps.setString(3, value.getInstruction());
        ps.setString(4, value.getRowguid());
        ps.setInt(5, value.getId());

        return ps;
    }
}
