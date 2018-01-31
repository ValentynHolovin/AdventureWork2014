package com.akvelon.server.service.impl;

import com.akvelon.server.dao.api.CultureDao;
import com.akvelon.server.domain.Culture;
import com.akvelon.server.service.api.CultureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CultureServiceImpl extends SuperStringService<Culture> implements CultureService {
    private static CultureServiceImpl cultureService;

    @Autowired
    public CultureServiceImpl(CultureDao dao) {
        super(dao);

        if (cultureService == null) {
            cultureService = this;
        }
    }

    public static synchronized CultureServiceImpl getInstance() {
        return cultureService;
    }
}
