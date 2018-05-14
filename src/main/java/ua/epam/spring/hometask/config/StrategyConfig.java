package ua.epam.spring.hometask.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import ua.epam.spring.hometask.strategy.BirthdayStrategy;
import ua.epam.spring.hometask.strategy.NTicketStrategy;

@Configuration
@PropertySource(value = {"classpath:strategy.properties"})
public class StrategyConfig {

    @Value("${b.discount}")
    private byte discount_birthday;

    @Value("${b.airDaysUp}")
    private int airDaysUp_birthday;

    @Value("${n.discount}")
    private byte discount_Nticket;

    @Bean()
    @Primary()
    public BirthdayStrategy birthdayStrategy() {
        BirthdayStrategy.setDiscount(discount_birthday);
        BirthdayStrategy.setAirDaysUp(airDaysUp_birthday);
        return new BirthdayStrategy();
    }


    @Bean()
    public NTicketStrategy nTicketStrategy() {
        NTicketStrategy.setDiscount(discount_Nticket);
        return new NTicketStrategy();
    }


}
