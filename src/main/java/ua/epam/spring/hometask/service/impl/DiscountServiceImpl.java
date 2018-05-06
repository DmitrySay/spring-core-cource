package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.strategy.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private List<DiscountStrategy> strategies;

    public DiscountServiceImpl() {
    }

    public void setStrategies(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }


    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        byte discountMax = 0;
        for (DiscountStrategy strategy : strategies) {
            byte currentDiscount = strategy.calculateDiscount(user, event, airDateTime, numberOfTickets);
            if (currentDiscount > discountMax) {
                discountMax = currentDiscount;
            }
        }
        return discountMax;
    }
}
