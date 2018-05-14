package ua.epam.spring.hometask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
@PropertySource(value = {"classpath:database.properties"})
public class DataBaseConfig {

    @Autowired
    Environment environment;

    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        driverManagerDataSource.setUrl(environment.getRequiredProperty("db.url"));
        driverManagerDataSource.setUsername(environment.getRequiredProperty("db.user"));
        driverManagerDataSource.setPassword(environment.getRequiredProperty("db.password"));

        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:create_script.sql")
                .addScript("classpath:insert_script.sql")
                .build();
    }
}
