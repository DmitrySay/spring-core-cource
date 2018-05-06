package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//@Repository
public class EventDaoImpl implements EventDao {

    private static List<Event> eventList = new ArrayList<>();


    public EventDaoImpl() {
    }

    public static List<Event> getEventList() {
        return eventList;
    }

    public static void setEventList(List<Event> eventList) {
        EventDaoImpl.eventList = eventList;
    }

    @Override
    public Event save(@Nonnull Event event) {
        eventList.add(event);
        return event;
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventList.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        for (Event event : eventList) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }


    @Override
    public Collection<Event> getAll() {
        return eventList;
    }


    @Override
    public Event getEvent(@Nonnull Event event) {
        return eventList.stream().filter(e -> e.equals(event)).findFirst().orElse(null);

    }
}
