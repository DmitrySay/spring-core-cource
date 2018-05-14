package ua.epam.spring.hometask.config;


import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = {"ua.epam.spring.hometask.*"})
@EnableAspectJAutoProxy
@Import({StrategyConfig.class, DataBaseConfig.class})
public class AppConfig {


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
