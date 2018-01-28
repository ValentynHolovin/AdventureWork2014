package com.akvelon.server.dao;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.dao.impl.CultureDaoImpl;
import com.akvelon.server.dao.impl.ProductCategoryDaoImpl;
import com.akvelon.server.dao.impl.ProductPhotoDaoImpl;
import com.akvelon.server.model.Culture;
import com.akvelon.server.model.ProductCategory;
import com.akvelon.server.model.ProductPhoto;


public class DaoFactory {
    private static DaoFactory daoFactory;

    private Dao<Integer, ProductCategory> productCategoryDao;
    private Dao<String, Culture> cultureDao;
    private Dao<Integer, ProductPhoto> productPhotoDao;

    private DaoFactory() {
        loadDao();
    }

    public static synchronized DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }

        return daoFactory;
    }

    private void loadDao() {
        productCategoryDao = ProductCategoryDaoImpl.getInstance();
        cultureDao = CultureDaoImpl.getInstance();
        productPhotoDao = ProductPhotoDaoImpl.getInstance();
    }

    public Dao<Integer, ProductCategory> getProductCategoryDao() {
        return productCategoryDao;
    }

    public Dao<String, Culture> getCultureDao() {
        return cultureDao;
    }

    public Dao<Integer, ProductPhoto> getProductPhotoDao() {
        return productPhotoDao;
    }
}
