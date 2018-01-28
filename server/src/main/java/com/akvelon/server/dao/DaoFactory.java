package com.akvelon.server.dao;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.dao.impl.*;
import com.akvelon.server.model.*;


public class DaoFactory {
    private static DaoFactory daoFactory;

    private Dao<Integer, ProductCategory> productCategoryDao;
    private Dao<String, Culture> cultureDao;
    private Dao<Integer, ProductPhoto> productPhotoDao;
    private Dao<Integer, Illustration> illustrationDao;
    private Dao<Integer, ProductSubcategory> productSubcategoryDao;

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
        illustrationDao = IllustrationDaoImpl.getInstance();
        productSubcategoryDao = ProductSubcategoryDaoImpl.getInstance();
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

    public Dao<Integer, Illustration> getIllustrationDao() {
        return illustrationDao;
    }

    public Dao<Integer, ProductSubcategory> getProductSubcategoryDao() {
        return productSubcategoryDao;
    }
}
