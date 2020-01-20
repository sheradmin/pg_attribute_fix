package com.mkyong.pg;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//private Integer oid;
//private String attname;
//private Integer atttypid;
//private Integer attstattarget;
//private Integer attlen;
//private Integer attnum;
//private Integer attndims;
//private Integer attcacheoff;
//private Integer atttypmod;
//private Boolean attbyval;
//private String attstorage;
//private String attalign;
//private Boolean attnotnull;
//private Boolean atthasdef;
//private String attidentity;
//private Boolean attisdropped;
//private Boolean attislocal;
//private Integer attinhcount;
//private Integer attcollation;
public class PgAttributeMapper implements RowMapper<PgAttribute> {

    @Override
    public PgAttribute mapRow(ResultSet rs, int rowNum) throws SQLException {

        PgAttribute pgAttribute = new PgAttribute();
        pgAttribute.setAttrelid(rs.getInt("attrelid"));
        pgAttribute.setAttname(rs.getString("attname"));
        pgAttribute.setAtttypid(rs.getInt("atttypid"));
        pgAttribute.setAttstattarget(rs.getInt("attstattarget"));
        pgAttribute.setAttlen(rs.getInt("attlen"));
        pgAttribute.setAttnum(rs.getInt("attnum"));
        pgAttribute.setAttndims(rs.getInt("attndims"));
        pgAttribute.setAttcacheoff(rs.getInt("attcacheoff"));
        pgAttribute.setAtttypmod(rs.getInt("atttypmod"));
        pgAttribute.setAttbyval(rs.getBoolean("attbyval"));
        pgAttribute.setAttstorage(rs.getString("attstorage"));
        pgAttribute.setAttalign(rs.getString("attalign"));
        pgAttribute.setAttnotnull(rs.getBoolean("attnotnull"));
        pgAttribute.setAtthasdef(rs.getBoolean("atthasdef"));
        pgAttribute.setAttidentity(rs.getString("attidentity"));
        pgAttribute.setAttisdropped(rs.getBoolean("attisdropped"));
        pgAttribute.setAttislocal(rs.getBoolean("attislocal"));
        pgAttribute.setAttinhcount(rs.getInt("attinhcount"));
        pgAttribute.setAttcollation(rs.getInt("attcollation"));

        return pgAttribute;

    }
}
