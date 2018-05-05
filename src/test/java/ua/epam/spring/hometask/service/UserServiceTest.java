package ua.epam.spring.hometask.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test.xml"})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Before
    public void initUsersMap() {
        User user1 = new User(1L, "Andrey", "Pupkin", "andrey@epam.com");
        User user2 = new User(2L, "Anna", "Pupkina", "anna@epam.com");
        userDao.save(user1);
        userDao.save(user2);
    }

    @Test
    public void getUserByEmailTest(){
        User user = userService.getUserByEmail("andrey@epam.com");
        Assert.assertEquals(user.getEmail(),"andrey@epam.com");
    }
}
