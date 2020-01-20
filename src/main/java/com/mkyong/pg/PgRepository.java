package com.mkyong.pg;

import com.mkyong.customer.Customer;
import com.mkyong.customer.CustomerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PgRepository {

    @Qualifier("jdbcTemplateLive")
    @Autowired
    private JdbcTemplate jdbcTemplateLive;

    @Qualifier("jdbcTemplateSt")
    @Autowired
    private JdbcTemplate jdbcTemplateSt;

    public List<PgClass> findAllBySchema(String schema) {
        String sql = "SELECT c.oid as oid,c.relname as relname, c.relnatts as relnatts FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = ?";

        return jdbcTemplateLive.query(
                sql, new Object[]{schema},
                new PgClassMapper());
    }

    public List<PgClass> findAllBySchemaAndTable(String schema, String table) {
        String sql = "SELECT c.oid as oid,c.relname as relname, c.relnatts as relnatts FROM pg_catalog.pg_class c JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = ? and c.relname =?";

        return jdbcTemplateLive.query(
                sql, new Object[]{schema, table},
                new PgClassMapper());
    }

    public int deletePgAttributeByPgClassOid(Integer oid) {
        return jdbcTemplateLive.update("delete from pg_attribute where attrelid = ?", oid);
    }

    /**
     * Get PgAttribute from ST
     *
     * @param relname pg_class relname
     * @param schema Default schema
     * @return List<PgAttribute>
     */
    public List<PgAttribute> findPgAttributeByRelname(String relname, String schema) {
        String nspname = "74";
        if ("public".equals(schema)) {
            nspname = "public";
        }
        String sql = "SELECT a.* FROM pg_catalog.pg_attribute a " +
                "left join pg_catalog.pg_class c on c.oid = a.attrelid " +
                "JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace WHERE n.nspname = ? and c.relname =?";

        List<PgAttribute> pgAttributes = jdbcTemplateSt.query(
                sql, new Object[]{nspname, relname},
                new PgAttributeMapper());

        return pgAttributes;
    }

    public int[] savePgAttributes(List<PgAttribute> pgAttributes) {
        return this.jdbcTemplateLive.batchUpdate(
                "INSERT INTO pg_catalog.pg_attribute (attrelid, attname, atttypid, attstattarget, attlen, attnum, attndims, attcacheoff, atttypmod, attbyval, attstorage, attalign, attnotnull, atthasdef, attidentity, attisdropped, attislocal, attinhcount, attcollation) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?)",
                new BatchPreparedStatementSetter() {

                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        PgAttribute pgAttribute = pgAttributes.get(i);
                        ps.setInt(1, pgAttribute.getAttrelid());
                        ps.setString(2, pgAttribute.getAttname());
                        ps.setInt(3, pgAttribute.getAtttypid());
                        ps.setInt(4, pgAttribute.getAttstattarget());
                        ps.setInt(5, pgAttribute.getAttlen());
                        ps.setInt(6, pgAttribute.getAttnum());
                        ps.setInt(7, pgAttribute.getAttndims());
                        ps.setInt(8, pgAttribute.getAttcacheoff());
                        ps.setInt(9, pgAttribute.getAtttypmod());
                        ps.setBoolean(10, pgAttribute.getAttbyval());
                        ps.setString(11, pgAttribute.getAttstorage());
                        ps.setString(12, pgAttribute.getAttalign());
                        ps.setBoolean(13, pgAttribute.getAttnotnull());
                        ps.setBoolean(14, pgAttribute.getAtthasdef());
                        ps.setString(15, pgAttribute.getAttidentity());
                        ps.setBoolean(16, pgAttribute.getAttisdropped());
                        ps.setBoolean(17, pgAttribute.getAttislocal());
                        ps.setInt(18, pgAttribute.getAttinhcount());
                        ps.setInt(19, pgAttribute.getAttcollation());
                    }

                    public int getBatchSize() {
                        return pgAttributes.size();
                    }

                });
    }

    public int save(Customer customer) {
        return jdbcTemplateLive.update(
                "insert into customer (name, age, created_date) values(?,?,?)",
                customer.getName(), customer.getAge(), LocalDateTime.now());
    }

    public Customer findByCustomerId(Long id) {

        String sql = "SELECT * FROM CUSTOMER WHERE ID = ?";

        return jdbcTemplateLive.queryForObject(sql, new Object[]{id}, new CustomerRowMapper());

    }

    public Customer findByCustomerId2(Long id) {

        String sql = "SELECT * FROM CUSTOMER WHERE ID = ?";

        return (Customer) jdbcTemplateLive.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Customer.class));

    }

    public Customer findByCustomerId3(Long id) {

        String sql = "SELECT * FROM CUSTOMER WHERE ID = ?";

        return jdbcTemplateLive.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Customer(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getTimestamp("created_date").toLocalDateTime()
                ));

    }

    public List<Customer> findAll() {

        String sql = "SELECT * FROM CUSTOMER";

        List<Customer> customers = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplateLive.queryForList(sql);

        for (Map row : rows) {
            Customer obj = new Customer();

            obj.setID(((Integer) row.get("ID")).longValue());
            //obj.setID(((Long) row.get("ID"))); no, ClassCastException
            obj.setName((String) row.get("NAME"));
            obj.setAge(((BigDecimal) row.get("AGE")).intValue()); // Spring returns BigDecimal, need convert
            obj.setCreatedDate(((Timestamp) row.get("CREATED_DATE")).toLocalDateTime());
            customers.add(obj);
        }

        return customers;
    }

    public List<Customer> findAll3() {

        String sql = "SELECT * FROM CUSTOMER";

        List<Customer> customers = jdbcTemplateLive.query(
                sql,
                new BeanPropertyRowMapper(Customer.class));

        return customers;
    }

    public List<Customer> findAll4() {

        String sql = "SELECT * FROM CUSTOMER";

        return jdbcTemplateLive.query(
                sql,
                (rs, rowNum) ->
                        new Customer(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getTimestamp("created_date").toLocalDateTime()
                        )
        );
    }

    public String findCustomerNameById(Long id) {

        String sql = "SELECT NAME FROM CUSTOMER WHERE ID = ?";

        return jdbcTemplateLive.queryForObject(
                sql, new Object[]{id}, String.class);

    }

    public int count() {

        String sql = "SELECT COUNT(*) FROM CUSTOMER";

        // queryForInt() is Deprecated
        // https://www.mkyong.com/spring/jdbctemplate-queryforint-is-deprecated/
        //int total = jdbcTemplate.queryForInt(sql);

        return jdbcTemplateLive.queryForObject(sql, Integer.class);

    }
}
