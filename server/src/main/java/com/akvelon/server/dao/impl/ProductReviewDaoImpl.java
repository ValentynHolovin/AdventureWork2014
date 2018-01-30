package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.ProductReviewDao;
import com.akvelon.server.domain.ProductReview;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ProductReviewDaoImpl extends SuperDao<ProductReview> implements ProductReviewDao {
    private static ProductReviewDaoImpl productReviewDao;
    private static RowMapper<ProductReview> rowMapper;

    private final String SQL_INSERT = "INSERT INTO productreview (ProductID, ReviewerName, EmailAddress, Rating, Comments) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE ReviewerName = ReviewerName";
    private final String SQL_UPDATE = "UPDATE productreview SET ProductID = ?, ReviewerName = ?, EmailAddress = ?, Rating = ?, Comments = ? WHERE ProductReviewID = ?";

    protected ProductReviewDaoImpl() {
        super(new ProductReview());
        if (productReviewDao == null) {
            productReviewDao = this;

            rowMapper = (ResultSet rs, int conNum) -> {
                ProductReview productReview = new ProductReview();

                productReview.setId(rs.getInt("ProductReviewID"));
                productReview.setProductID(rs.getInt("ProductID"));
                productReview.setReviewerName(rs.getString("ReviewerName"));
                productReview.setReviewDate(rs.getDate("ReviewDate"));
                productReview.setEmailAddress(rs.getString("EmailAddress"));
                productReview.setRating(rs.getInt("Rating"));
                productReview.setComments(rs.getString("Comments"));

                return productReview;
            };
        }
    }

    public static synchronized ProductReviewDaoImpl getInstance() {
        if (productReviewDao == null) {
            productReviewDao = new ProductReviewDaoImpl();
        }

        return productReviewDao;
    }

    @Override
    protected RowMapper getRowMapper() {
        return rowMapper;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, ProductReview value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, value.getProductID());
        ps.setString(2, value.getReviewerName());
        ps.setString(3, value.getEmailAddress());
        ps.setInt(4, value.getRating());
        ps.setString(5, value.getComments());

        return ps;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, ProductReview value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setInt(1, value.getProductID());
        ps.setString(2, value.getReviewerName());
        ps.setString(3, value.getEmailAddress());
        ps.setInt(4, value.getRating());
        ps.setString(5, value.getComments());
        ps.setInt(6, value.getId());

        return ps;
    }
}
