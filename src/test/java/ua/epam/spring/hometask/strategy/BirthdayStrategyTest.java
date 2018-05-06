package ua.epam.spring.hometask.strategy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfigTest;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.EventService;

import java.time.LocalDateTime;
import java.time.Month;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class})
public class BirthdayStrategyTest {

    @Autowired
    BirthdayStrategy birthdayStrategy;

    @Autowired
    UserDao userDao;

    @Autowired
    EventService eventService;

    private LocalDateTime airDateTime;
    private Event event;
    private User user1;

    @Before
    public void init() {
        user1 = new User(1L, "Andrey", "Pupkin", "andrey@epam.com");
        User user2 = new User(2L, "Anna", "Pupkina", "anna@epam.com");
        airDateTime = LocalDateTime.of(1985, Month.MAY, 1, 0, 0);
        user1.setBirthday(airDateTime);
        user2.setBirthday(airDateTime);
        userDao.save(user1);
        userDao.save(user2);
        event = eventService.getByName("New_Year_Party");
    }

    @Test
    public void calculateDiscountTest() {
        byte discount = birthdayStrategy.calculateDiscount(user1, event, airDateTime, 0);
        byte expected = 0;
        Assert.assertEquals(expected, discount);

    }
}
