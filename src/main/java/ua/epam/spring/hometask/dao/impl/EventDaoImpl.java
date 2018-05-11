package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Repository("eventDao")
public class EventDaoImpl implements EventDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public EventDaoImpl() {
    }


    @PostConstruct
    public void init() {
        Event event = new Event();
        event.setName("New_Year_Party");
        NavigableSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(LocalDateTime.now());
        airDates.add(LocalDateTime.now().plusDays(5));
        event.setAirDates(airDates);
        event.setBasePrice(99.9);

        event.setRating(EventRating.HIGH);
        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();

        Auditorium auditoriumBlue = new Auditorium();
        auditoriumBlue.setName("blue auditorium");
        auditoriums.put(LocalDateTime.now(), auditoriumBlue);

        event.setAuditoriums(auditoriums);

        jdbcTemplate.update("INSERT INTO event (name, basePrice , rating, airDates) VALUES (?,?,?,?)",
                event.getName(),
                event.getBasePrice(),
                String.valueOf(event.getRating()),
                event.getAirDates().toArray()

        );

        jdbcTemplate.update("INSERT INTO EVENT_AUDITORIUM (event_name, auditorium_name , event_airdate) VALUES (?,?,?)",
                event.getName(),
                auditoriumBlue.getName(),
                Timestamp.valueOf(LocalDateTime.now())
        );

    }


    @Override
    public Event save(@Nonnull Event event) {
        jdbcTemplate.update("INSERT INTO event (name, basePrice , rating, airDates) VALUES (?,?,?,?)",
                event.getName(),
                event.getBasePrice(),
                String.valueOf(event.getRating()),
                event.getAirDates().toArray()

        );

        NavigableMap<LocalDateTime, Auditorium> auditoriums = event.getAuditoriums();
        auditoriums.entrySet().forEach(auditorium -> {
            jdbcTemplate.update("INSERT INTO EVENT_AUDITORIUM (event_name, auditorium_name , event_airdate) VALUES (?,?,?)",
                    event.getName(),
                    auditorium.getValue().getName(),
                    Timestamp.valueOf(auditorium.getKey())
            );
        });

        return event;
    }

    @Override
    public void remove(@Nonnull Event event) {
        jdbcTemplate.update("DELETE FROM EVENT_AUDITORIUM where event_name = ?", event.getName());
        jdbcTemplate.update("DELETE FROM event where name = ?", event.getName());
    }

    @Override
    public Collection<Event> getAll() {
        return jdbcTemplate.query("SELECT * FROM event", (ResultSet rs, int rowNum) -> {
            Event event = new Event();
            event.setId(rs.getLong(1));
            event.setName(rs.getString(2));
            event.setBasePrice(rs.getDouble(3));
            String ratingSql = rs.getString(4);
            EventRating rating = EventRating.valueOf(ratingSql);
            event.setRating(rating);

            Array array = rs.getArray(5);
            Object[] objects = (Object[]) array.getArray();

            NavigableSet<LocalDateTime> airDates = new TreeSet<>();

            for (Object o : objects) {
                Timestamp timestamp = (Timestamp) o;
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                airDates.add(localDateTime);
            }

            event.setAirDates(airDates);

            //--------------------------------------------------------------------//
            NavigableMap<LocalDateTime, String> auditoriumsName = new TreeMap<>();
            jdbcTemplate.query("SELECT * FROM EVENT_AUDITORIUM WHERE event_name = ?", new Object[]{event.getName()},
                    (resultSet) -> {

                        Timestamp timestamp = resultSet.getTimestamp("event_airdate");
                        LocalDateTime event_airdate = timestamp.toLocalDateTime();
                        String auditorium_name = resultSet.getString("auditorium_name");

                        auditoriumsName.put(event_airdate, auditorium_name);
                    });


            //--------------------------------------------------------------------//
            NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();

            auditoriumsName.entrySet().forEach(a -> {
                jdbcTemplate.query("SELECT * FROM AUDITORIUM where name = ?", new Object[]{a.getValue()},
                        resultSet -> {
                            Auditorium auditorium = new Auditorium();
                            auditorium.setId(resultSet.getLong(1));
                            auditorium.setName(resultSet.getString(2));
                            auditorium.setNumberOfSeats(resultSet.getLong(3));
                            Array vip_seats = resultSet.getArray(4);
                            if (vip_seats != null) {
                                Set<Long> vipSeats = new HashSet<>(Arrays.asList((Long[]) vip_seats.getArray()));
                                auditorium.setVipSeats(vipSeats);
                            }

                            auditoriums.put(a.getKey(), auditorium);
                        });

            });
            event.setAuditoriums(auditoriums);
            return event;

        });
    }

    @Override
    public Event getById(@Nonnull Long id) {

        return null;
    }


    @Override
    public Event getEvent(@Nonnull Event event) {
        List<Event> events = jdbcTemplate.query("SELECT * FROM event where name = ?", new Object[]{event.getName()}, (ResultSet rs, int rowNum) -> {
            Event e = new Event();
            e.setId(rs.getLong(1));
            e.setName(rs.getString(2));
            e.setBasePrice(rs.getDouble(3));
            String ratingSql = rs.getString(4);
            EventRating rating = EventRating.valueOf(ratingSql);
            e.setRating(rating);

            Array array = rs.getArray(5);
            Object[] objects = (Object[]) array.getArray();

            NavigableSet<LocalDateTime> airDates = new TreeSet<>();

            for (Object o : objects) {
                Timestamp timestamp = (Timestamp) o;
                LocalDateTime localDateTime = timestamp.toLocalDateTime();
                airDates.add(localDateTime);
            }

            e.setAirDates(airDates);


            //--------------------------------------------------------------------//
            NavigableMap<LocalDateTime, String> auditoriumsName = new TreeMap<>();
            jdbcTemplate.query("SELECT * FROM EVENT_AUDITORIUM WHERE event_name = ?", new Object[]{e.getName()},
                    (resultSet) -> {

                        Timestamp timestamp = resultSet.getTimestamp("event_airdate");
                        LocalDateTime event_airdate = timestamp.toLocalDateTime();
                        String auditorium_name = resultSet.getString("auditorium_name");

                        auditoriumsName.put(event_airdate, auditorium_name);
                    });


            //--------------------------------------------------------------------//


            NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();

            auditoriumsName.entrySet().forEach(a -> {
                jdbcTemplate.query("SELECT * FROM AUDITORIUM where name = ?", new Object[]{a.getValue()},
                        resultSet -> {
                            Auditorium auditorium = new Auditorium();
                            auditorium.setId(resultSet.getLong(1));
                            auditorium.setName(resultSet.getString(2));
                            auditorium.setNumberOfSeats(resultSet.getLong(3));
                            Array vip_seats = resultSet.getArray(4);
                            if (vip_seats != null) {
                                Set<Long> vipSeats = new HashSet<>(Arrays.asList((Long[]) vip_seats.getArray()));
                                auditorium.setVipSeats(vipSeats);
                            }

                            auditoriums.put(a.getKey(), auditorium);
                        });

            });
            e.setAuditoriums(auditoriums);

            return e;

        });

        return events.isEmpty() ? null : events.get(0);

    }
}
