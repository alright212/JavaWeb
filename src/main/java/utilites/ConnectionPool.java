package utilites;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ConnectionPool {

    public DataSource createConnectionPool(int maxTotal, int innitialSize) {
        DBConnectionParameters connectionInfo = DBConfigUtil.readConnectionInfo();

        BasicDataSource pool = new BasicDataSource();
        pool.setDriverClassName("org.postgresql.Driver");
        pool.setUrl(connectionInfo.getUrl());
        pool.setUsername(connectionInfo.getUser());
        pool.setPassword(connectionInfo.getPass());
        pool.setMaxTotal(maxTotal); // 2
        pool.setInitialSize(innitialSize); // 1

        try {
            // has the side effect of initializing the connection pool
            pool.getLogWriter();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pool;
    }

    public DataSource createConnectionPool() {
        return createConnectionPool(2, 1);
    }
}
