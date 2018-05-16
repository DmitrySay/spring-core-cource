package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.CounterAspectDao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CounterAspectDaoImpl implements CounterAspectDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveEventValueCouterByName(String eventName) {
        Long count = 0L;
        count = countEventsByNameStorage(eventName);
        if (count > 0) {
            Long counter = getByNameEventsByNameStorage(eventName) + 1L;
            updateEventsByNameStorage(eventName, counter);
        } else {
            insertEventsByNameStorage(eventName);
        }
    }

    private Long countEventsByNameStorage(String eventName) {
        Long count = 0L;
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM eventsByNameStorage where event_name =?", new Object[]{eventName}, Long.class);
        return count;
    }

    private Long getByNameEventsByNameStorage(String eventName) {
        Long counter = 0L;
        counter = jdbcTemplate.queryForObject("SELECT counter FROM eventsByNameStorage where event_name = ?", new Object[]{eventName}, Long.class);
        return counter;
    }

    private void insertEventsByNameStorage(String eventName) {
        jdbcTemplate.update("INSERT INTO eventsByNameStorage (event_name, counter) VALUES (?,?)", eventName, 1L);
    }

    private void updateEventsByNameStorage(String eventName, Long counter) {
        jdbcTemplate.update("UPDATE eventsByNameStorage SET event_name=?, counter=? WHERE event_name =?", eventName, counter, eventName);
    }

    public List<Map<String, Long>> eventsByNameStorageGetAll() {
        return jdbcTemplate.query("SELECT * FROM eventsByNameStorage", (ResultSet rs, int rowNum) ->
        {
            Map<String, Long> m = new HashMap<>();
            String eventName = rs.getString("event_name");
            Long counter = rs.getLong("counter");
            m.put(eventName, counter);
            return m;
        });
    }


    @Override
    public void saveEventValueCouterByPrice(String eventName) {
        Long count = 0L;
        count = countEventsByPriceStorage(eventName);
        if (count > 0) {
            Long counter = getByNameEventsByPriceStorage(eventName) + 1L;
            updateEventsByPriceStorage(eventName, counter);
        } else {
            insertEventsByPriceStorage(eventName);
        }
    }

    private void insertEventsByPriceStorage(String eventName) {
        jdbcTemplate.update("INSERT INTO eventsByPriceStorage (event_name, counter) VALUES (?,?)", eventName, 1L);
    }

    private void updateEventsByPriceStorage(String eventName, Long counter) {
        jdbcTemplate.update("UPDATE eventsByPriceStorage SET event_name=?, counter=? WHERE event_name =?", eventName, counter, eventName);
    }

    private Long countEventsByPriceStorage(String eventName) {
        Long count = 0L;
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM eventsByPriceStorage where event_name =?", new Object[]{eventName}, Long.class);
        return count;
    }

    private Long getByNameEventsByPriceStorage(String eventName) {
        Long counter = 0L;
        counter = jdbcTemplate.queryForObject("SELECT counter FROM eventsByPriceStorage where event_name = ?", new Object[]{eventName}, Long.class);
        return counter;
    }

    @Override
    public List<Map<String, Long>> eventsByPriceStorageGetAll() {
        return jdbcTemplate.query("SELECT * FROM eventsByPriceStorage", (ResultSet rs, int rowNum) ->
        {
            Map<String, Long> m = new HashMap<>();
            String eventName = rs.getString("event_name");
            Long counter = rs.getLong("counter");
            m.put(eventName, counter);
            return m;
        });
    }


    @Override
    public void saveEventValueCouterByTicket(String eventName) {
         //TODO
    }


}
