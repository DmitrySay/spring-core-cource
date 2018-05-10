package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.*;

@Repository("ticketDao")
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TicketDaoImpl() {
    }


    @Override
    public Ticket save(@Nonnull Ticket ticket) {

        return ticket;
    }

    @Override
    public void remove(@Nonnull Ticket ticket) {

    }

    @Override
    public Ticket getById(@Nonnull Long id) {
        return null;

    }


    @Override
    public Collection<Ticket> getAll() {
        return null;
    }

    public static void main(String[] args) {
        /*
        selectStudent = "SELECT S.id, S.name, S.surname, S.enrolment_date, S.group_id, G.id, G.number, G.department  FROM `Student` S INNER JOIN `Group` G ON S.group_id = G.id WHERE S.id = ?;";
         */
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        TicketDao ticketDao = (TicketDao) ctx.getBean("ticketDao");
        EventDao eventDao = (EventDao)ctx.getBean("eventDao");
        UserDao userDao = (UserDao)ctx.getBean("userDao");

        List<Event> eventList = new ArrayList<>(eventDao.getAll());
        //System.out.println(eventList);

        Event  eventEtalon = eventList.get(0);
        //System.out.println(eventEtalon);

        NavigableSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(eventEtalon.getAirDates().iterator().next());
        LocalDateTime dateTime = eventEtalon.getAuditoriums().keySet().iterator().next();
        Ticket ticket1 = new Ticket();

        ticket1.setSeat(10L);
        ticket1.setDateTime(LocalDateTime.now().plusDays(1));

        ticket1.setEvent(eventEtalon);


        //System.out.println(userDao.getAll());

        ticket1.setUser(userDao.getAll().iterator().next());

        System.out.println(ticket1);
        ticketDao.save(ticket1);
        System.out.println(ticketDao.getAll());

    }
}
