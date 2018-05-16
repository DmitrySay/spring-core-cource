package ua.epam.spring.hometask.dao;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collection;

@ContextConfiguration(classes = {AppConfig.class})
public class UserDaoTest extends AbstractJUnit4SpringContextTests {

    private static User userEtalon;
    private static User user;

    @Autowired
    UserDao userDao;

    @BeforeClass
    public static void initUsersMap() {
        userEtalon = new User("Andrey", "Pupkin", "andrey@epam.com", LocalDate.of(1985, Month.MARCH, 13));
        user = new User("test", "test", "test@epam.com", LocalDate.of(1950, Month.MAY, 1));
    }


    @Test
    public void getUserByEmailTest() {
        User userDB = userDao.getUserByEmail("andrey@epam.com");
        System.out.println(userDB);
        System.out.println(userEtalon);
        Assert.assertEquals(userDB, userEtalon);
    }

    @Test
    public void getUserByIdTest() {
        User user = userDao.getById(1L);
        Assert.assertEquals(user, userEtalon);

    }

    @Test
    public void getAllTest() {
        Collection<User> users = userDao.getAll();
        Assert.assertNotNull(users);
    }

    @Test
    public void saveTest() {
        int before = userDao.getAll().size();
        userDao.save(user);
        int after = userDao.getAll().size();
        Assert.assertEquals(after - 1, before);
        userDao.remove(user);
    }

    @Test
    public void removeTest() {
        userDao.save(user);
        int before = userDao.getAll().size();
        userDao.remove(user);
        int after = userDao.getAll().size();
        Assert.assertEquals(after, before - 1);
    }
}
