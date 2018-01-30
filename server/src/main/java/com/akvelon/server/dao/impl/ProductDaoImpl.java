package com.akvelon.server.dao.impl;

import com.akvelon.server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl extends SuperDao<Product> {
    private static ProductDaoImpl productDao;
    private static RowMapper<Product> rowMapper;

    @Autowired
    private ProductPhotoDaoImpl productPhotoDao;
    @Autowired
    private UnitMeasureDaoImpl unitMeasureDao;
    @Autowired
    private ProductSubcategoryDaoImpl productSubcategoryDao;
    @Autowired
    private ProductModelDaoImpl productModelDao;
    @Autowired
    private ProductReviewDaoImpl productReviewDao;

    private final String SQL_INSERT = "INSERT INTO product (Name, ProductNumber, MakeFlag, FinishedGoodsFlag, Color, SafetyStockLevel, ReorderPoint, StandardCost, ListPrice, Size, SizeUnitMeasureCode, WeightUnitMeasureCode, Weight, DaysToManufacture, ProductLine, Class, Style, ProductSubcategoryID, ProductModelID, SellStartDate, SellEndDate, DiscontinuedDate, rowguid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE Name = Name";
    private final String SQL_UPDATE = "UPDATE product SET Name = ?, ProductNumber = ?, MakeFlag = ?, FinishedGoodsFlag = ?, Color = ?, SafetyStockLevel = ?, ReorderPoint = ?, StandardCost = ?, ListPrice = ?, Size = ?, SizeUnitMeasureCode = ?, WeightUnitMeasureCode = ?, Weight = ?, DaysToManufacture = ?, ProductLine = ?, Class = ?, Style = ?, ProductSubcategoryID = ?, ProductModelID = ?, SellStartDate = ?, SellEndDate = ?, DiscontinuedDate = ?, rowguid = ? WHERE ProductID = ?";
    private final String SQL_GET_PRODUCTPHOTO = "SELECT ProductPhotoID FROM productproductphoto WHERE ProductID = ?";
    private final String SQL_INSERT_PRODUCTPRODUCTPHOTO = "INSERT INTO productproductphoto (ProductID, ProductPhotoID, Primary) values (?, ?, ?) ON DUPLICATE KEY UPDATE ProdectID = ProductID";

    protected ProductDaoImpl() {
        super(new Product());
        if (productDao == null) {
            productDao = this;

            rowMapper = (ResultSet rs, int conNum) -> {
                Product product = new Product();

                product.setId(rs.getInt("ProductID"));
                product.setName(rs.getString("Name"));
                product.setProductNumber(rs.getString("ProductNumber"));
                product.setMakeFlag(rs.getInt("MakeFlag") == 1);
                product.setFinishedGoodsFlag(rs.getInt("FinishedGoodsFlag") == 1);
                product.setColor(rs.getString("Color"));
                product.setSafetyStockLevel(rs.getInt("SafetyStockLevel"));
                product.setReorderPoint(rs.getInt("ReorderPoint"));
                product.setStandardCost(rs.getDouble("StandardCost"));
                product.setListPrice(rs.getDouble("ListPrice"));
                product.setSize(rs.getString("Size"));
                product.setSizeUnitMeasureCode(unitMeasureDao.read(rs.getString("SizeUnitMeasureCode")));
                product.setWeightUnitMeasureCode(unitMeasureDao.read(rs.getString("WeightUnitMeasureCode")));
                product.setWeight(rs.getDouble("Weight"));
                product.setDaysToManufacture(rs.getInt("DaysToManufacture"));
                product.setProductLine(ProductLine.valueOf(rs.getString("ProductLine")));
                product.setProductClass(ProductClass.valueOf(rs.getString("Class")));
                product.setProductStyle(ProductStyle.valueOf(rs.getString("Style")));
                product.setProductSubcategory(productSubcategoryDao.read(rs.getInt("ProductSubcategoryID")));
                product.setProductModel(productModelDao.read(rs.getInt("ProductModelID")));
                product.setSellStartDate(rs.getDate("SellStartDate"));
                product.setSellEndDate(rs.getDate("SellEndDate"));
                product.setDiscontinuedDate(rs.getDate("DiscontinuedDate"));
                product.setRowguid(rs.getString("rowguid"));
                product.setProductReviews(productReviewDao.readAllBy("ProductID", product.getId()));

                List<Integer> productPhotoIDList = this.jdbcTemplate.queryForList(SQL_GET_PRODUCTPHOTO, new Object[] {product.getId()}, Integer.class);
                List<ProductPhoto> productPhotos = new ArrayList<>();

                for (Integer id : productPhotoIDList) {
                    productPhotos.add(productPhotoDao.read(id));
                }

                product.setProductPhotos(productPhotos);

                return product;
            };
        }
    }

    public static synchronized ProductDaoImpl getInstance() {
        if(productDao == null) {
            productDao = new ProductDaoImpl();
        }

        return productDao;
    }

    @Override
    public Integer create(Product value) {
        Integer id = super.create(value);

        for (ProductPhoto productPhoto : value.getProductPhotos()) {
            this.jdbcTemplate.update(SQL_INSERT_PRODUCTPRODUCTPHOTO, value.getId(), productPhoto.getId(), 1);
        }

        return id;
    }

    @Override
    protected RowMapper getRowMapper() {
        return rowMapper;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, Product value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, value.getName());
        ps.setString(2, value.getProductNumber());
        ps.setInt(3, value.getMakeFlag() ? 1 : 0);
        ps.setInt(4, value.getFinishedGoodsFlag() ? 1 : 0);
        ps.setString(5, value.getColor());
        ps.setInt(6, value.getSafetyStockLevel());
        ps.setInt(7, value.getReorderPoint());
        ps.setDouble(8, value.getStandardCost());
        ps.setDouble(9, value.getListPrice());
        ps.setString(10, value.getSize());
        ps.setString(11, value.getSizeUnitMeasureCode().getId());
        ps.setString(12, value.getWeightUnitMeasureCode().getId());
        ps.setDouble(13, value.getWeight());
        ps.setInt(14, value.getDaysToManufacture());
        ps.setString(15, value.getProductLine().name());
        ps.setString(16, value.getProductClass().name());
        ps.setString(17, value.getProductStyle().name());
        ps.setInt(18, value.getProductSubcategory().getId());
        ps.setInt(19, value.getProductModel().getId());
        ps.setDate(20, value.getSellStartDate());
        ps.setDate(21, value.getSellEndDate());
        ps.setDate(22, value.getDiscontinuedDate());
        ps.setString(23, value.getRowguid());

        return ps;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, Product value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setString(1, value.getName());
        ps.setString(2, value.getProductNumber());
        ps.setInt(3, value.getMakeFlag() ? 1 : 0);
        ps.setInt(4, value.getFinishedGoodsFlag() ? 1 : 0);
        ps.setString(5, value.getColor());
        ps.setInt(6, value.getSafetyStockLevel());
        ps.setInt(7, value.getReorderPoint());
        ps.setDouble(8, value.getStandardCost());
        ps.setDouble(9, value.getListPrice());
        ps.setString(10, value.getSize());
        ps.setString(11, value.getSizeUnitMeasureCode().getId());
        ps.setString(12, value.getWeightUnitMeasureCode().getId());
        ps.setDouble(13, value.getWeight());
        ps.setInt(14, value.getDaysToManufacture());
        ps.setString(15, value.getProductLine().name());
        ps.setString(16, value.getProductClass().name());
        ps.setString(17, value.getProductStyle().name());
        ps.setInt(18, value.getProductSubcategory().getId());
        ps.setInt(19, value.getProductModel().getId());
        ps.setDate(20, value.getSellStartDate());
        ps.setDate(21, value.getSellEndDate());
        ps.setDate(22, value.getDiscontinuedDate());
        ps.setString(23, value.getRowguid());
        ps.setInt(24, value.getId());

        return ps;
    }
}