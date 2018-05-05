package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuditoriumDaoImpl implements AuditoriumDao {

    private static List<Auditorium> auditoriumList = new ArrayList<>();

    public AuditoriumDaoImpl() {
    }

    public static void setAuditoriumList(List<Auditorium> auditoriumList) {
        AuditoriumDaoImpl.auditoriumList = auditoriumList;
    }

    @Override
    public Auditorium save(@Nonnull Auditorium auditorium) {
        auditoriumList.add(auditorium);
        return auditorium;
    }

    @Override
    public void remove(@Nonnull Auditorium auditorium) {
        auditoriumList.remove(auditorium);
    }

    @Override
    public Auditorium getById(@Nonnull Long id) {
        for (Auditorium auditorium : auditoriumList) {
            if (auditorium.getId().equals(id)) {
                return auditorium;
            }
        }
        return null;
    }

    @Nonnull
    @Override
    public Collection<Auditorium> getAll() {
        return auditoriumList;
    }

}
