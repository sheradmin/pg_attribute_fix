package com.mkyong.pg;

//Table"pg_catalog.pg_attribute"
// Column     |   Type    | Collation | Nullable | Default
// ---------------+-----------+-----------+----------+---------
// Attrelid      | oid       |           | not null |
// Attname       | name      |           | not null |
// Atttypid      | oid       |           | not null |
// Attstattarget | integer   |           | not null |
// Attlen        | smallint  |           | not null |
// Attnum        | smallint  |           | not null |
// Attndims      | integer   |           | not null |
// Attcacheoff   | integer   |           | not null |
// Atttypmod     | integer   |           | not null |
// Attbyval      | boolean   |           | not null |
// Attstorage    | "char"    |           | not null |
// Attalign      | "char"    |           | not null |
// Attnotnull    | boolean   |           | not null |
// Atthasdef     | boolean   |           | not null |
// Attidentity   | "char"    |           | not null |
// Attisdropped  | boolean   |           | not null |
// Attislocal    | boolean   |           | not null |
// Attinhcount   | integer   |           | not null |
// Attcollation  | oid       |           | not null |
// Attacl        | aclitem[] |           |          |
// Attoptions    | text[]    |           |          |
// Attfdwoptions | text[]    |           |          |
// Indexes:
// "pg_attribute_relid_attnam_index" UNIQUE, btree (attrelid, attname)
// "pg_attribute_relid_attnum_index" UNIQUE, btree (attrelid, attnum)


public class PgAttribute {

    private Integer attrelid;
    private String attname;
    private Integer atttypid;
    private Integer attstattarget;
    private Integer attlen;
    private Integer attnum;
    private Integer attndims;
    private Integer attcacheoff;
    private Integer atttypmod;
    private Boolean attbyval;
    private String attstorage;
    private String attalign;
    private Boolean attnotnull;
    private Boolean atthasdef;
    private String attidentity;
    private Boolean attisdropped;
    private Boolean attislocal;
    private Integer attinhcount;
    private Integer attcollation;

    public Integer getAttrelid() {
        return attrelid;
    }

    public void setAttrelid(Integer attrelid) {
        this.attrelid = attrelid;
    }

    public String getAttname() {
        return attname;
    }

    public void setAttname(String attname) {
        this.attname = attname;
    }

    public Integer getAtttypid() {
        return atttypid;
    }

    public void setAtttypid(Integer atttypid) {
        this.atttypid = atttypid;
    }

    public Integer getAttstattarget() {
        return attstattarget;
    }

    public void setAttstattarget(Integer attstattarget) {
        this.attstattarget = attstattarget;
    }

    public Integer getAttlen() {
        return attlen;
    }

    public void setAttlen(Integer attlen) {
        this.attlen = attlen;
    }

    public Integer getAttnum() {
        return attnum;
    }

    public void setAttnum(Integer attnum) {
        this.attnum = attnum;
    }

    public Integer getAttndims() {
        return attndims;
    }

    public void setAttndims(Integer attndims) {
        this.attndims = attndims;
    }

    public Integer getAttcacheoff() {
        return attcacheoff;
    }

    public void setAttcacheoff(Integer attcacheoff) {
        this.attcacheoff = attcacheoff;
    }

    public Integer getAtttypmod() {
        return atttypmod;
    }

    public void setAtttypmod(Integer atttypmod) {
        this.atttypmod = atttypmod;
    }

    public Boolean getAttbyval() {
        return attbyval;
    }

    public void setAttbyval(Boolean attbyval) {
        this.attbyval = attbyval;
    }

    public String getAttstorage() {
        return attstorage;
    }

    public void setAttstorage(String attstorage) {
        this.attstorage = attstorage;
    }

    public String getAttalign() {
        return attalign;
    }

    public void setAttalign(String attalign) {
        this.attalign = attalign;
    }

    public Boolean getAttnotnull() {
        return attnotnull;
    }

    public void setAttnotnull(Boolean attnotnull) {
        this.attnotnull = attnotnull;
    }

    public Boolean getAtthasdef() {
        return atthasdef;
    }

    public void setAtthasdef(Boolean atthasdef) {
        this.atthasdef = atthasdef;
    }

    public String getAttidentity() {
        return attidentity;
    }

    public void setAttidentity(String attidentity) {
        this.attidentity = attidentity;
    }

    public Boolean getAttisdropped() {
        return attisdropped;
    }

    public void setAttisdropped(Boolean attisdropped) {
        this.attisdropped = attisdropped;
    }

    public Boolean getAttislocal() {
        return attislocal;
    }

    public void setAttislocal(Boolean attislocal) {
        this.attislocal = attislocal;
    }

    public Integer getAttinhcount() {
        return attinhcount;
    }

    public void setAttinhcount(Integer attinhcount) {
        this.attinhcount = attinhcount;
    }

    public Integer getAttcollation() {
        return attcollation;
    }

    public void setAttcollation(Integer attcollation) {
        this.attcollation = attcollation;
    }

    @Override
    public String toString() {
        return "PgAttribute{" +
                "attrelid=" + attrelid +
                ", attname='" + attname + '\'' +
                ", atttypid=" + atttypid +
                ", attstattarget=" + attstattarget +
                ", attlen=" + attlen +
                ", attnum=" + attnum +
                ", attndims=" + attndims +
                ", attcacheoff=" + attcacheoff +
                ", atttypmod=" + atttypmod +
                ", attbyval=" + attbyval +
                ", attstorage='" + attstorage + '\'' +
                ", attalign='" + attalign + '\'' +
                ", attnotnull=" + attnotnull +
                ", atthasdef=" + atthasdef +
                ", attidentity='" + attidentity + '\'' +
                ", attisdropped=" + attisdropped +
                ", attislocal=" + attislocal +
                ", attinhcount=" + attinhcount +
                ", attcollation=" + attcollation +
                '}';
    }
}
