package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.dao.api.UnitMeasureDao;
import com.akvelon.server.domain.UnitMeasure;
import com.akvelon.server.service.api.UnitMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the class of SuperStringService
 * and the interface UnitMeasureService for the domain object UnitMeasure.
 * @see com.akvelon.server.service.api.UnitMeasureService
 * @see com.akvelon.server.service.impl.SuperStringService
 */
@Service
public class UnitMeasureServiceImpl extends SuperStringService<UnitMeasure> implements UnitMeasureService {
    @Autowired
    private UnitMeasureDao unitMeasureDao;

    @Override
    public Dao<String, UnitMeasure> getRepository() {
        return unitMeasureDao;
    }
}
