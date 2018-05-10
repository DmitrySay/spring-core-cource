package ua.epam.spring.hometask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import ua.epam.spring.hometask.strategy.BirthdayStrategy;
import ua.epam.spring.hometask.strategy.NTicketStrategy;

@Configuration
public class StrategyConfig {

    private byte discount_birthday = 5;
    private int airDaysUp_birthday = 5;
    private byte discount_Nticket = 50;

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
