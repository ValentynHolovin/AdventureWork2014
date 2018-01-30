package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.CultureDao;
import com.akvelon.server.dao.impl.CultureDaoImpl;
import com.akvelon.server.domain.Culture;
import com.akvelon.server.service.api.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CultureServiceImpl implements CultureService {
    private static CultureServiceImpl cultureService;
    @Autowired
    private CultureDao cultureDao;

    protected CultureServiceImpl() {
        if (cultureService == null) {
            cultureService = this;
        }
    }

    public static synchronized CultureServiceImpl getInstance() {
        if (cultureService == null) {
            cultureService = new CultureServiceImpl();
        }

        return cultureService;
    }

    @Override
    public List<Culture> getAll() {
        return cultureDao.getAll();
    }

    @Override
    public String create(Culture value) {
        return cultureDao.create(value);
    }

    @Override
    public Culture read(String key) {
        return cultureDao.read(key);
    }

    @Override
    public void update(Culture value) {
        cultureDao.update(value);
    }

    @Override
    public void delete(String key) {
        cultureDao.delete(key);
    }

    @Override
    public <T> Culture readBy(String fieldName, T value) {
        return cultureDao.readBy(fieldName, value);
    }

    @Override
    public <T> List<Culture> readAllBy(String fieldName, T value) {
        return cultureDao.readAllBy(fieldName, value);
    }
}
