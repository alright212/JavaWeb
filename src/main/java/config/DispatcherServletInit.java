package config;

import lombok.NonNull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Configures the servlet mappings and loads configuration classes.
 */
public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected String @NonNull [] getServletMappings() {
        return new String[]{"/api/*"};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                SpringConfig.class,
                HsqlDataSource.class,
                PostgresDataSource.class
        };
    }
}
