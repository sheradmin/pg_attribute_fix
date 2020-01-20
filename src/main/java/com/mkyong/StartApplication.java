package com.mkyong;

import com.mkyong.pg.PgAttribute;
import com.mkyong.pg.PgClass;
import com.mkyong.pg.PgRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class StartApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartApplication.class);

    @Qualifier("jdbcTemplateLive")
    @Autowired
    JdbcTemplate jdbcTemplateLive;

    @Autowired
    PgRepository pgRepository;

    @Bean
    public LobHandler lobHandler() {
        return new DefaultLobHandler();
    }

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("StartApplication...");

        if (args.length < 1) {
            log.info("Error no arrguments;");
            log.info("Example1: --schemas=1");
            log.info("Example2: --schema=1,--table=clinic_patient_appointment");
            log.info("Example3: --schema=public,--table=company");
            return;
        }

        String schema = "1";
        String table = null;

        for (String param : args) {
            if (param.startsWith("-Dschema=")) {
                schema = param.replace("-Dschema=", "").trim();
            } else if (param.startsWith("-Dtable=")) {
                table = param.replace("-Dtable=", "").trim();
            }
        }

        log.info("Schema - {}", schema);
        log.info("Table  - {}", table);

        if (schema.isEmpty()) {
            return;
        }

        if (table == null || table.isEmpty()) {
            runForSchema(schema);
        } else {
            runForSchemaTable(schema, table);
        }
    }

    void runForSchema(String schema) {
        List<PgClass> classes = pgRepository.findAllBySchema(schema);
        for (PgClass pgClass : classes) {
            List<PgAttribute> pgAttributes = pgRepository.findPgAttributeByRelname(pgClass.getRelname(), schema);
            if (CollectionUtils.isEmpty(pgAttributes)) {
                //dont delete old data
                log.warn("Warning: Attribute count was " + pgClass.getRelnatts() + " found 0, for relname =" + pgClass.getRelname());
            } else {
                //delete pg_attribute
                pgRepository.deletePgAttributeByPgClassOid(pgClass.getOid());

                //insert new data
                for (PgAttribute pgAttribute : pgAttributes) {
                    pgAttribute.setAttrelid(pgClass.getOid());
                }


                int[] result = new int[0];
                try {
                    result = pgRepository.savePgAttributes(pgAttributes);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("Cannot insert attributes: pg_class: {}; pg_attribute: {}", pgClass, pgAttributes);
                }
//                log.info("Result count:" + result.length);
            }
        }
    }

    void runForSchemaTable(String schema, String table) {
        List<PgClass> classes = pgRepository.findAllBySchemaAndTable(schema, table);
        for (PgClass pgClass : classes) {
            List<PgAttribute> pgAttributes = pgRepository.findPgAttributeByRelname(pgClass.getRelname(), schema);
            if (CollectionUtils.isEmpty(pgAttributes)) {
                //dont delete old data
                log.warn("Warning: Attribute count was " + pgClass.getRelnatts() + " found 0, for relname =" + pgClass.getRelname());
            } else {
                //delete pg_attribute
                pgRepository.deletePgAttributeByPgClassOid(pgClass.getOid());

                //insert new data
                for (PgAttribute pgAttribute : pgAttributes) {
                    pgAttribute.setAttrelid(pgClass.getOid());
                }

                System.out.println(pgAttributes);

                int[] result = pgRepository.savePgAttributes(pgAttributes);
                log.info("Result count:" + result.length);
            }
        }
    }
}
