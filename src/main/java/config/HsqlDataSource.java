package config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import utilites.DBConfigUtil;
import utilites.DBConnectionParameters;

import javax.sql.DataSource;

@Profile("default | hsql")
@Component
public class HsqlDataSource {

    private static final String HSQL_DRIVER = "org.hsqldb.jdbcDriver";
    private static final int MAX_TOTAL_CONNECTIONS = 2;
    private static final int INITIAL_SIZE = 1;

    @Bean("dataSource")
    public DataSource createConnectionPoolViaHsql() {
        DBConnectionParameters connectionInfo = DBConfigUtil.readConnectionInfo();

        BasicDataSource pool = new BasicDataSource();

        try {
            pool.setDriverClassName(HSQL_DRIVER);
            pool.setUrl(connectionInfo.getHsqlUrl());
            pool.setMaxTotal(MAX_TOTAL_CONNECTIONS);
            pool.setInitialSize(INITIAL_SIZE);

            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
            DatabasePopulatorUtils.execute(resourceDatabasePopulator, pool);

        } catch (Exception e) {
            throw new RuntimeException("An error happened while setting up the HSQL datasource.", e);
        }

        return pool;
    }
}
