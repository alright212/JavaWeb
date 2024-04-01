package config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import utilites.DBConfigUtil;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import utilites.DBConnectionParameters;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Profile("default | postgres")
@Component
public class PostgresDataSource {

    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
    private static final int MAX_TOTAL_CONNECTIONS = 2;
    private static final int INITIAL_SIZE = 1;

    private static final Logger LOGGER = Logger.getLogger(PostgresDataSource.class.getName());

    @Bean("dataSource")
    public DataSource createConnectionPoolViaPostgres() {
        DBConnectionParameters connectionInfo = DBConfigUtil.readConnectionInfo();

        BasicDataSource pool = new BasicDataSource();
        pool.setDriverClassName(POSTGRES_DRIVER);
        pool.setUrl(connectionInfo.getUrl());
        pool.setUsername(connectionInfo.getUser());
        pool.setPassword(connectionInfo.getPass());
        pool.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        pool.setInitialSize(INITIAL_SIZE);
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(new ClassPathResource("schema.sql"));
        DatabasePopulatorUtils.execute(resourceDatabasePopulator, pool);

        try {
            pool.getLogWriter();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "An error happened while creating connection pool.", e);
            throw new RuntimeException(e);
        }

        return pool;
    }
}
