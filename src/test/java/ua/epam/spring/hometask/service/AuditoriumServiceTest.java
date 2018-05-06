package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.domain.Auditorium;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test.xml"})
public class AuditoriumServiceTest {

    @Autowired
    AuditoriumService auditoriumService;

    @Test
    public void getAllTest() {
        Assert.assertNotNull(auditoriumService.getAll());
    }

    @Test
    public void getByNameTest() {
        Auditorium auditorium = auditoriumService.getByName("blue auditorium");
        Assert.assertEquals("blue auditorium", auditorium.getName());
    }
}
