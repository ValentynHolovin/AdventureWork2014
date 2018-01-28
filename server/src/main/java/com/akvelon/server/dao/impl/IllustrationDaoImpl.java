package com.akvelon.server.dao.impl;

import com.akvelon.server.model.Illustration;
import org.springframework.stereotype.Repository;

import javax.swing.tree.RowMapper;
import java.sql.ResultSet;

@Repository
public class IllustrationDaoImpl extends SuperDao<Illustration> {
    private static IllustrationDaoImpl illustrationDao;
    private static RowMapper rowMapper;

    protected IllustrationDaoImpl() {
        super(new Illustration());
        if (illustrationDao == null) {
            illustrationDao = this;

            rowMapper = (ResultSet rs, int conNum) -> {
                Illustration illustration = new Illustration();
            };
        }
    }
}
