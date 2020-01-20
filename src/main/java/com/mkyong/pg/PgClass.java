package com.mkyong.pg;

//       Table "pg_catalog.pg_class"
// Column        |     Type     | Collation | Nullable | Default
// ---------------------+--------------+-----------+----------+---------
// relname             | name         |           | not null |
// relnamespace        | oid          |           | not null |
// reltype             | oid          |           | not null |
// reloftype           | oid          |           | not null |
// relowner            | oid          |           | not null |
// relam               | oid          |           | not null |
// relfilenode         | oid          |           | not null |
// reltablespace       | oid          |           | not null |
// relpages            | integer      |           | not null |
// reltuples           | real         |           | not null |
// relallvisible       | integer      |           | not null |
// reltoastrelid       | oid          |           | not null |
// relhasindex         | boolean      |           | not null |
// relisshared         | boolean      |           | not null |
// relpersistence      | "char"       |           | not null |
// relkind             | "char"       |           | not null |
// relnatts            | smallint     |           | not null |
// relchecks           | smallint     |           | not null |
// relhasoids          | boolean      |           | not null |
// relhaspkey          | boolean      |           | not null |
// relhasrules         | boolean      |           | not null |
// relhastriggers      | boolean      |           | not null |
// relhassubclass      | boolean      |           | not null |
// relrowsecurity      | boolean      |           | not null |
// relforcerowsecurity | boolean      |           | not null |
// relispopulated      | boolean      |           | not null |
// relreplident        | "char"       |           | not null |
// relispartition      | boolean      |           | not null |
// relfrozenxid        | xid          |           | not null |
// relminmxid          | xid          |           | not null |
// relacl              | aclitem[]    |           |          |
// reloptions          | text[]       |           |          |
// relpartbound        | pg_node_tree |           |          |
// Indexes:
// "pg_class_oid_index" UNIQUE, btree (oid)
// "pg_class_relname_nsp_index" UNIQUE, btree (relname, relnamespace)
// "pg_class_tblspc_relfilenode_index" btree (reltablespace, relfilenode)


public class PgClass {

    private Integer oid;
    private String relname;
    private int relnatts;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getRelname() {
        return relname;
    }

    public void setRelname(String relname) {
        this.relname = relname;
    }

    public int getRelnatts() {
        return relnatts;
    }

    public void setRelnatts(int relnatts) {
        this.relnatts = relnatts;
    }

    @Override
    public String toString() {
        return "PgClass{" +
                "oid=" + oid +
                ", relname='" + relname + '\'' +
                ", relnatts=" + relnatts +
                '}';
    }
}
