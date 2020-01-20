package com.mkyong.pg;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PgClassMapper implements RowMapper<PgClass> {

    @Override
    public PgClass mapRow(ResultSet rs, int rowNum) throws SQLException {

        PgClass pgClass = new PgClass();
        pgClass.setOid(rs.getInt("oid"));
        pgClass.setRelname(rs.getString("relname"));
        pgClass.setRelnatts(rs.getInt("relnatts"));

        return pgClass;

    }
}

