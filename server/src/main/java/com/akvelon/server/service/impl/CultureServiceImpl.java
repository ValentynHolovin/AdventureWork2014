package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.CultureDao;
import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.domain.Culture;
import com.akvelon.server.service.api.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CultureServiceImpl extends SuperStringService<Culture> implements CultureService {

    @Autowired
    private CultureDao cultureDao;

    @Override
    public Dao<String, Culture> getRepository() {
        return cultureDao;
    }
}
