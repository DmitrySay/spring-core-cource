package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;

public interface EventDao extends AbstractDomainObjectDao<Event> {


    public Event getEvent(@Nonnull Event event);

    public Event getByName(@Nonnull String name);
}
