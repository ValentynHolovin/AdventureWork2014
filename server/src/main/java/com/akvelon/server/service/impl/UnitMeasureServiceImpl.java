package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.Dao;
import com.akvelon.server.dao.api.UnitMeasureDao;
import com.akvelon.server.domain.UnitMeasure;
import com.akvelon.server.service.api.UnitMeasureService;
import org.springframework.beans.factory.annotation.Autowired;

public class UnitMeasureServiceImpl extends SuperStringService<UnitMeasure> implements UnitMeasureService {
    private static UnitMeasureServiceImpl unitMeasureService;

    @Autowired
    protected UnitMeasureServiceImpl(UnitMeasureDao dao) {
        super(dao);
        if (unitMeasureService == null) {
            unitMeasureService = this;
        }
    }

    public static synchronized UnitMeasureServiceImpl getInstance() {
        return unitMeasureService;
    }
}
