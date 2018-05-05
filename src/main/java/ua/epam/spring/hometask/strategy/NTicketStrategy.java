package ua.epam.spring.hometask.strategy;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

public class NTicketStrategy implements DiscountStrategy {

    private static byte discount;

    public NTicketStrategy() {
    }

    public static byte getDiscount() {
        return discount;
    }

    public static void setDiscount(byte discount) {
        NTicketStrategy.discount = discount;
    }

    @Override
    public byte calculateDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        long quantity = (numberOfTickets / 10);
        return (byte) (quantity * discount / numberOfTickets);
    }
}
