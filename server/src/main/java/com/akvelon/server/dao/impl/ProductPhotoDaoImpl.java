package com.akvelon.server.dao.impl;

import com.akvelon.server.dao.api.ProductPhotoDao;
import com.akvelon.server.domain.ProductPhoto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ProductPhotoDaoImpl extends SuperDao<ProductPhoto> implements ProductPhotoDao {
    private static ProductPhotoDaoImpl productPhotoDao;
    private static RowMapper<ProductPhoto> rowMapper;

    private final String SQL_INSERT = "INSERT INTO productphoto (ThumbNailPhoto, ThumbnailPhotoFileName, LargePhoto, LargePhotoFileName) values (?, ?, ?, ?) ON DUPLICATE KEY UPDATE ThumbnailPhotoFileName = ThumbnailPhotoFileName";
    private final String SQL_UPDATE = "UPDATE productphoto SET ThumbNailPhoto = ?, ThumbnailPhotoFileName = ?, LargePhoto = ?, LargePhotoFileName = ? WHERE ProductPhotoID = ?";

    public ProductPhotoDaoImpl() {
        super(new ProductPhoto());
        if (productPhotoDao == null) {
            productPhotoDao = this;

            rowMapper = (ResultSet rs, int conNum) -> {
                ProductPhoto productPhoto = new ProductPhoto();

                productPhoto.setId(rs.getInt("ProductPhotoID"));
                productPhoto.setThumbNailPhoto(rs.getBlob("ThumbNailPhoto"));
                productPhoto.setThumbnailPhotoFileName(rs.getString("ThumbnailPhotoFileName"));
                productPhoto.setLargePhoto(rs.getBlob("LargePhoto"));
                productPhoto.setLargePhotoFileName(rs.getString("LargePhotoFileName"));

                return productPhoto;
            };
        }
    }

    public static synchronized ProductPhotoDaoImpl getInstance() {
        if (productPhotoDao == null) {
            productPhotoDao = new ProductPhotoDaoImpl();
        }

        return productPhotoDao;
    }

    @Override
    protected RowMapper getRowMapper() {
        return rowMapper;
    }

    @Override
    protected PreparedStatement createInsertStatement(Connection connection, ProductPhoto value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

        ps.setBlob(1, value.getThumbNailPhoto());
        ps.setString(2, value.getLargePhotoFileName());
        ps.setBlob(3, value.getLargePhoto());
        ps.setString(4, value.getLargePhotoFileName());

        return ps;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Connection connection, ProductPhoto value) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);

        ps.setBlob(1, value.getThumbNailPhoto());
        ps.setString(2, value.getLargePhotoFileName());
        ps.setBlob(3, value.getLargePhoto());
        ps.setString(4, value.getLargePhotoFileName());
        ps.setInt(5, value.getId());

        return ps;
    }
}
