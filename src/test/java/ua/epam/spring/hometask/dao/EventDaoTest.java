package ua.epam.spring.hometask.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfigTest;
import ua.epam.spring.hometask.domain.Event;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfigTest.class})
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
        Assert.assertEquals(event, currentEvent);
    }


}
