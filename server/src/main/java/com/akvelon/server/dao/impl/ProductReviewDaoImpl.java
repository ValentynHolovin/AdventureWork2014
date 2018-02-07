package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.ProductReviewDao;
import com.akvelon.server.domain.ProductReview;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;

/**
 * Implementation of the abstract class of SuperDao
 * and the interface ProductReviewDao for the domain object ProductReview.
 * @see com.akvelon.server.dao.impl.SuperDao
 * @see com.akvelon.server.dao.api.ProductReviewDao
 */
@Repository
public class ProductReviewDaoImpl extends SuperDao<Integer, ProductReview> implements ProductReviewDao {
    private final String SQL_INSERT = "INSERT INTO productreview (ProductID, ReviewerName, EmailAddress, Rating, Comments) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE ReviewerName = ReviewerName";
    private final String SQL_UPDATE = "UPDATE productreview SET ProductID = ?, ReviewerName = ?, EmailAddress = ?, Rating = ?, Comments = ? WHERE ProductReviewID = ?";

    @Override
    protected ProductReview getClassObject() {
        return new ProductReview();
    }

    @Override
    protected void setId(ProductReview value, KeyHolder keyHolder) {
        value.setId(keyHolder.getKey().intValue());
    }

    @Override
    protected RowMapper<ProductReview> getRowMapper() {
        return (ResultSet rs, int conNum) -> {
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
