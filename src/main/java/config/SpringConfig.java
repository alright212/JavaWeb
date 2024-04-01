package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"servlet", "module", "config"})
public class SpringConfig {

    @Bean
    public JdbcTemplate getTemplate(@Qualifier("dataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }


}
