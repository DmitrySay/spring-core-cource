package ua.epam.spring.hometask.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test.xml"})
public class AuditoriumDaoTest {

    private int numberOfAuditoriumCollections = 2;

    @Autowired
    AuditoriumDao auditoriumDao;


    @Test
    public void getAllTest() {
        Collection<Auditorium> list = auditoriumDao.getAll();
        Assert.assertTrue(list.size() == numberOfAuditoriumCollections);
    }

}
