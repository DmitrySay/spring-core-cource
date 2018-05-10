package ua.epam.spring.hometask.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class EventServiceTest {

    @Autowired
    EventService eventService;

    @Test
    public void getByNameTest() {
        Assert.assertEquals(eventService.getByName("New_Year_Party"), eventService.getAll().iterator().next());
    }
}
