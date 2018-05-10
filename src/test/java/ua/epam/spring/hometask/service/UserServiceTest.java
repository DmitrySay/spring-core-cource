package ua.epam.spring.hometask.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;


    @Test
    public void getUserByEmailTest() {
        User user = userService.getUserByEmail("andrey@epam.com");
        Assert.assertEquals(user.getEmail(), "andrey@epam.com");
    }
}
