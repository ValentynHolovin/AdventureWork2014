package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.ProductPhotoDao;
import com.akvelon.server.domain.ProductPhoto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.*;

/**
 * Implementation of the abstract class of SuperDao
 * and the interface ProductPhotoDao for the domain object ProductPhoto.
 * @see com.akvelon.server.dao.impl.SuperDao
 * @see com.akvelon.server.dao.api.ProductPhotoDao
 */
@Repository
public class ProductPhotoDaoImpl extends SuperDao<Integer, ProductPhoto> implements ProductPhotoDao {
    private final String SQL_INSERT = "INSERT INTO productphoto (ThumbNailPhoto, ThumbnailPhotoFileName, LargePhoto, LargePhotoFileName) values (?, ?, ?, ?) ON DUPLICATE KEY UPDATE ThumbnailPhotoFileName = ThumbnailPhotoFileName";
    private final String SQL_UPDATE = "UPDATE productphoto SET ThumbNailPhoto = ?, ThumbnailPhotoFileName = ?, LargePhoto = ?, LargePhotoFileName = ? WHERE ProductPhotoID = ?";

    @Override
    protected ProductPhoto getClassObject() {
        return new ProductPhoto();
    }

    @Override
    protected void setId(ProductPhoto value, KeyHolder keyHolder) {
        value.setId(keyHolder.getKey().intValue());
    }

    @Override
    protected RowMapper<ProductPhoto> getRowMapper() {
        return (ResultSet rs, int conNum) -> {
            ProductPhoto productPhoto = new ProductPhoto();

            productPhoto.setId(rs.getInt("ProductPhotoID"));
            productPhoto.setThumbNailPhoto(rs.getBlob("ThumbNailPhoto").getBytes(1, (int)rs.getBlob("ThumbNailPhoto").length()));
            productPhoto.setThumbnailPhotoFileName(rs.getString("ThumbnailPhotoFileName"));
            productPhoto.setLargePhoto(rs.getBlob("LargePhoto").getBytes(1, (int)rs.getBlob("LargePhoto").length()));
            productPhoto.setLargePhotoFileName(rs.getString("LargePhotoFileName"));

            return productPhoto;
        };
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, ProductPhoto value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setBlob(1, new SerialBlob(value.getThumbNailPhoto()));
        ps.setString(2, value.getLargePhotoFileName());
        ps.setBlob(3, new SerialBlob(value.getLargePhoto()));
        ps.setString(4, value.getLargePhotoFileName());

        return ps;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, ProductPhoto value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setBlob(1, new SerialBlob(value.getThumbNailPhoto()));
        ps.setString(2, value.getLargePhotoFileName());
        ps.setBlob(3, new SerialBlob(value.getLargePhoto()));
        ps.setString(4, value.getLargePhotoFileName());
        ps.setInt(5, value.getId());

        return ps;
    }
}
