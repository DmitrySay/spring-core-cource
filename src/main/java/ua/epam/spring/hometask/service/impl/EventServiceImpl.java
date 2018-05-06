package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;

    public EventServiceImpl() {
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        List<Event> eventList = new ArrayList<>(eventDao.getAll());
        return eventList.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Event save(@Nonnull Event event) {
        eventDao.save(event);
        return event;
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventDao.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDao.getAll();
    }
}
