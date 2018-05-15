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
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.*;

@Repository("ticketDao")
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TicketDaoImpl() {
    }

    @Override
    public void remove(@Nonnull Ticket ticket) {
        jdbcTemplate.update("DELETE FROM ticket where id = ?", ticket.getId());
    }

    @Override
    public Ticket getById(@Nonnull Long id) {
        List<Ticket> tickets = jdbcTemplate.query("SELECT * FROM TICKET t JOIN user u " +
                "ON t.user_id = u.id JOIN event e ON t.event_id =e.id WHERE t.id = ?", new Object[]{id}, (rs, rowNum) -> {

            Ticket ticket = createTicket(rs);
            User user = createUser(rs);
            Event e = createEvent(rs);
            ticket.setUser(user);
            ticket.setEvent(e);
            return ticket;
        });

        return tickets.isEmpty() ? null : tickets.get(0);
    }


    @Override
    public Ticket save(@Nonnull Ticket ticket) {
        Long count = 0L;

        if (ticket.getId() != null) {
            count = jdbcTemplate.queryForObject("SELECT count(*) FROM TICKET where id =?", new Object[]{ticket.getId()}, Long.class);
        }

        if (count.equals(0L)) {
            jdbcTemplate.update("INSERT INTO TICKET (user_id, event_id, dateTime, seat) VALUES (?,?,?,?)",

                    ticket.getUser().getId(),
                    ticket.getEvent().getId(),
                    Timestamp.valueOf(ticket.getDateTime()),
                    ticket.getSeat()
            );
        } else {

            jdbcTemplate.update("UPDATE TICKET SET user_id =?, event_id=?, dateTime=?, seat=? WHERE id =?",
                    ticket.getUser().getId(),
                    ticket.getEvent().getId(),
                    Timestamp.valueOf(ticket.getDateTime()),
                    ticket.getSeat(),
                    ticket.getId()
            );

        }
        return ticket;
    }


    @Override
    public Collection<Ticket> getAll() {

        return jdbcTemplate.query("SELECT * FROM TICKET t JOIN user u " +
                "ON t.user_id = u.id JOIN event e ON t.event_id =e.id", (rs, rowNum) -> {

            Ticket ticket = createTicket(rs);
            User user = createUser(rs);
            Event e = createEvent(rs);
            ticket.setUser(user);
            ticket.setEvent(e);
            return ticket;
        });
    }

    private Ticket createTicket(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong("id"));
        Timestamp timestamp = rs.getTimestamp("dateTime");
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        ticket.setDateTime(dateTime);
        ticket.setSeat(rs.getLong("seat"));
        return ticket;
    }

    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmail(rs.getString("email"));
        Date date = rs.getDate("birthDay");
        if (null != date) {
            user.setBirthday(date.toLocalDate());
        }
        return user;
    }

    private Event createEvent(ResultSet rs) throws SQLException {
        Event e = new Event();
        e.setId(rs.getLong("event_id"));
        e.setName(rs.getString("name"));
        e.setBasePrice(rs.getDouble("basePrice"));
        String ratingSql = rs.getString("rating");
        EventRating rating = EventRating.valueOf(ratingSql);
        e.setRating(rating);

        Array array = rs.getArray("airDates");
        Object[] objects = (Object[]) array.getArray();

        NavigableSet<LocalDateTime> airDates = new TreeSet<>();

        for (Object o : objects) {
            Timestamp t = (Timestamp) o;
            LocalDateTime localDateTime = t.toLocalDateTime();
            airDates.add(localDateTime);
        }
        e.setAirDates(airDates);

        return e;
    }
}
