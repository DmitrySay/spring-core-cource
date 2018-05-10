package ua.epam.spring.hometask.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.domain.Event;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class EventDaoTest {

    private Event event;
    private List<Event> eventList;

    @Autowired
    EventDao eventDao;


    @Before
    public void initEventList() {
        eventList = new ArrayList<>(eventDao.getAll());
        event = eventList.get(0);

    }

    @Test
    public void getEventTest() {
        Event currentEvent = eventDao.getEvent(event);
       // System.out.println(event);
      //  System.out.println(currentEvent);
        Assert.assertEquals(event, currentEvent);
    }


}
