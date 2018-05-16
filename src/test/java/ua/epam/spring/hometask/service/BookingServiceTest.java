package ua.epam.spring.hometask.service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class BookingServiceTest {

    @Autowired
    EventDao eventDao;

    @Autowired
    TicketDao ticketDao;

    @Autowired
    BookingService bookingService;

    @Autowired
    UserDao userDao;

    private Event eventEtalon;
    private LocalDateTime dateTime;
    private User userEtalon;
    private Set<Long> seats;
    private Ticket ticket1;
    private Ticket ticket2;

    @Before
    public void init() {
        List<Event> eventList = new ArrayList<>(eventDao.getAll());
        eventEtalon = eventList.get(0);
        NavigableSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(eventEtalon.getAirDates().iterator().next());
        dateTime = eventEtalon.getAuditoriums().keySet().iterator().next();
        ticket1 = new Ticket();
        ticket2 = new Ticket();
        ticket1.setId(1L);
        ticket2.setId(2L);
        ticket1.setSeat(10L);
        ticket2.setSeat(50L);
        ticket1.setDateTime(dateTime);
        ticket2.setDateTime(LocalDateTime.now().plusDays(1));
        ticket1.setEvent(eventEtalon);
        ticket2.setEvent(eventEtalon);
        User user = userDao.getUserByEmail("andrey@epam.com");
        ticket1.setUser(user);
        ticket2.setUser(user);

        userEtalon = new User(1L, "Andrey", "Pupkin", "andrey@epam.com");
        seats = new HashSet<>(Arrays.asList(10L, 70L));
    }

    @After
    public void tearDown() {
        ticketDao.remove(ticket1);
        ticketDao.remove(ticket2);
    }


    @Test
    public void bookTicketsTest() {
        Set<Ticket> ticketsList = new HashSet<>();
        ticketsList.add(ticket1);
        ticketsList.add(ticket2);
        int beforeSize = ticketDao.getAll().size();
        bookingService.bookTickets(ticketsList);
        System.out.println(ticketDao.getAll());
        int afterSize = ticketDao.getAll().size();
        Assert.assertEquals(beforeSize, afterSize - ticketsList.size());

    }

    @Test
    public void getTicketsPriceTest() {
        Double price = bookingService.getTicketsPrice(eventEtalon, dateTime, userEtalon, seats);
        Assert.assertEquals(price, (Double) 359.64);
    }


    @Test
    public void getPurchasedTicketsForEventTest() {
        Set<Ticket> set = bookingService.getPurchasedTicketsForEvent(eventEtalon, dateTime);
        Assert.assertNotNull(set);
    }
}
