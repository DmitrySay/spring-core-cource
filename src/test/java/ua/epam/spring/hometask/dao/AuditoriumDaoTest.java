package ua.epam.spring.hometask.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AuditoriumDaoTest {

    private int numberOfAuditorium = 2;

    @Autowired
    AuditoriumDao auditoriumDao;


    @Test
    public void getAllTest() {
        Collection<Auditorium> list = auditoriumDao.getAll();
        Assert.assertTrue(list.size() == numberOfAuditorium);
    }

    @Test
    public void getUserByIdTest() {
        Auditorium auditoriumBlue = new Auditorium();
        auditoriumBlue.setName("blue auditorium");
        auditoriumBlue.setNumberOfSeats(200L);
        Set<Long> vipSeats = new HashSet<>();
        vipSeats.add(10L);
        vipSeats.add(20L);
        vipSeats.add(30L);
        auditoriumBlue.setVipSeats(vipSeats);

        Auditorium auditorium = auditoriumDao.getById(1L);
        Assert.assertEquals(auditorium, auditoriumBlue);

    }

    @Test
    public void saveTest() {
        Auditorium auditoriumBlue = new Auditorium();
        auditoriumBlue.setName("test auditorium");
        auditoriumBlue.setNumberOfSeats(300L);
        Set<Long> vipSeats = new HashSet<>();
        vipSeats.add(40L);
        vipSeats.add(50L);
        vipSeats.add(10L);
        auditoriumBlue.setVipSeats(vipSeats);
        int before = auditoriumDao.getAll().size();
        auditoriumDao.save(auditoriumBlue);
        int after = auditoriumDao.getAll().size();
        Assert.assertEquals(after - 1, before);
        auditoriumDao.remove(auditoriumBlue);
    }


    @Test
    public void removeTest() {
        Auditorium auditoriumBlue = new Auditorium();
        auditoriumBlue.setName("test auditorium");
        auditoriumBlue.setNumberOfSeats(300L);
        Set<Long> vipSeats = new HashSet<>();
        vipSeats.add(40L);
        vipSeats.add(50L);
        vipSeats.add(10L);
        auditoriumBlue.setVipSeats(vipSeats);
        auditoriumDao.save(auditoriumBlue);
        int before = auditoriumDao.getAll().size();
        auditoriumDao.remove(auditoriumBlue);
        int after = auditoriumDao.getAll().size();
        Assert.assertEquals(after, before -1);
    }
}
