package ua.epam.spring.hometask.strategy;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NavigableSet;

public class BirthdayStrategy implements DiscountStrategy {

    private static byte discount = 0;

    private static int airDaysUp = 0;

    public BirthdayStrategy() {
    }

    public static byte getDiscount() {
        return discount;
    }

    public static void setDiscount(byte discount) {
        BirthdayStrategy.discount = discount;
    }

    public static int getAirDaysUp() {
        return airDaysUp;
    }

    public static void setAirDaysUp(int airDaysUp) {
        BirthdayStrategy.airDaysUp = airDaysUp;
    }

    @Override
    public byte calculateDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        LocalDateTime birthday = user.getBirthday();
        LocalDate currentday = LocalDate.now();
        LocalDate currentYearBirhtday = LocalDate.of(currentday.getYear(), birthday.getMonth(), birthday.getDayOfMonth());

        NavigableSet<LocalDateTime> airDates = event.getAirDates();
        for (LocalDateTime aDate : airDates) {
            if (currentYearBirhtday.isAfter(aDate.toLocalDate())
                    && currentYearBirhtday.isBefore(aDate.plusDays(airDaysUp + 1).toLocalDate())) {
                return discount;
            }
        }
        return 0;
    }
}
