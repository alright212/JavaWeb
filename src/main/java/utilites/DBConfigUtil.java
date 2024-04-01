package utilites;

import java.util.Properties;

public class DBConfigUtil {

    public static DBConnectionParameters readConnectionInfo() {
        Properties properties = PropertyLoader.loadApplicationProperties();

        return new DBConnectionParameters(
                properties.getProperty("dbUrl"),
                properties.getProperty("dbUser"),
                properties.getProperty("dbPassword"),
                properties.getProperty("hsqlUrl"));

    }

}
