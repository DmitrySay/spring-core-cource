package ua.epam.spring.hometask.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import ua.epam.spring.hometask.config.AppConfigTest;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ContextConfiguration(classes = {AppConfigTest.class})
public class TicketDaoTest extends AbstractJUnit4SpringContextTests {

    private Ticket ticketEtalon;
    private Event event;
    private List<Event> eventList;

    @Autowired
    EventDao eventDao;

    @Autowired
    TicketDao ticketDao;

    @Before
    public void initTickets() {
        eventList = new ArrayList<>(eventDao.getAll());
        event = eventList.get(0);

        ticketEtalon = new Ticket();
        ticketEtalon.setId(1L);
        ticketEtalon.setEvent(event);
        ticketEtalon.setSeat(40L);
        ticketEtalon.setDateTime(LocalDateTime.now().plusDays(1));
        ticketDao.save(ticketEtalon);
    }

    @After
    public void tearDown() {
        ticketDao.remove(ticketEtalon);
    }

    @Test
    public void getAllTest() {
        Collection<Ticket> ticketsList = ticketDao.getAll();
        Assert.assertNotNull(ticketsList);
    }

    @Test
    public void getTicketByIdTest() {
        Ticket ticket = ticketDao.getById(1L);
        Assert.assertEquals(ticket, ticketEtalon);
    }
}


