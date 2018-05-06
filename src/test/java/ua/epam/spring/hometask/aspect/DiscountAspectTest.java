package ua.epam.spring.hometask.aspect;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfigTest;
import ua.epam.spring.hometask.dao.DiscountAspectDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.impl.DiscountServiceImpl;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class})
public class DiscountAspectTest {

    @Autowired
    DiscountAspectDao discountAspectDao;

    @Autowired
    EventService eventService;

    @Autowired
    DiscountService discountService;


    @Before
    public void init() {
        User user1 = new User(1L, "Andrey", "Pupkin", "andrey@epam.com");
        User user2 = new User(2L, "Anna", "Pupkina", "anna@epam.com");
        LocalDateTime airDateTime = LocalDateTime.of(1985, Month.MAY, 1, 0, 0);
        user1.setBirthday(airDateTime);
        user2.setBirthday(airDateTime);
        Event event = eventService.getByName("New_Year_Party");
        discountService.getDiscount(user1, event, airDateTime, 99);
        discountService.getDiscount(user1, event, airDateTime, 200);
        discountService.getDiscount(user2, event, airDateTime, 100);
    }

    @Test
    public void getDiscountTest() {
        Map<Class<?>, Long> map = discountAspectDao.getDiscountCounterStorage();
        Long result = map.get(DiscountServiceImpl.class);
        Assert.assertTrue(result.equals(3L));
    }
}
