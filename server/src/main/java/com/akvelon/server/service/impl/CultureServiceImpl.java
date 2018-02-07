package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.CultureDao;
import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.domain.Culture;
import com.akvelon.server.service.api.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Implementation of the class of SuperService
 * and the interface CultureService for the domain object Culture.
 * @see com.akvelon.server.service.api.CultureService
 * @see com.akvelon.server.service.impl.SuperService
 */
@Service
public class CultureServiceImpl extends SuperService<String, Culture> implements CultureService {

    @Autowired
    private CultureDao cultureDao;

    @Override
    public Dao<String, Culture> getRepository() {
        return cultureDao;
    }
}
