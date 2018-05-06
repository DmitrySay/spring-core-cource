package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service("auditoriumService")
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired()
    private AuditoriumDao auditoriumDao;


    public AuditoriumServiceImpl() {
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        Set<Auditorium> set = new HashSet<>(auditoriumDao.getAll());
        return set;
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        Collection<Auditorium> list = auditoriumDao.getAll();
        return list.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);

    }
}
