package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.CounterAspectDao;

import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CounterAspectDaoImpl implements CounterAspectDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveEventValueCouterByName(String eventName) {
        Long count = 0L;
        count = count(eventName);
        if (count > 0) {
            Long counter = getByName(eventName)+1L;
            update(eventName, counter);
        } else {
            insert(eventName);
        }
    }

    public Long count(String eventName) {
        Long count = 0L;
        count = jdbcTemplate.queryForObject("SELECT count(*) FROM eventsByNameStorage where event_name =?", new Object[]{eventName}, Long.class);
        return count;
    }

    public Long getByName(String eventName) {
        Long counter = 0L;
        counter = jdbcTemplate.queryForObject("SELECT counter FROM eventsByNameStorage where event_name = ?", new Object[]{eventName}, Long.class);
        return counter;
    }

    public void insert(String eventName) {
        jdbcTemplate.update("INSERT INTO eventsByNameStorage (event_name, counter) VALUES (?,?)", eventName, 1L);
    }

    public void update(String eventName, Long counter) {
        jdbcTemplate.update("UPDATE eventsByNameStorage SET event_name=?, counter=? WHERE event_name =?", eventName, counter, eventName);
    }

    public List<Map<String, Long>> eventsByNameStorageGetAll() {
        return jdbcTemplate.query("SELECT * FROM eventsByNameStorage", (ResultSet rs, int rowNum) ->
        {
            Map<String, Long> m = new LinkedHashMap<>();
            String eventName = rs.getString("event_name");
            Long counter = rs.getLong("counter");
            m.put(eventName, counter);
            return m;
        });
    }


}
