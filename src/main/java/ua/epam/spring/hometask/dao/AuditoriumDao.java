package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;

public interface AuditoriumDao extends AbstractDomainObjectDao<Auditorium> {

    Auditorium getByName(@Nonnull String name);
}
