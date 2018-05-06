package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.domain.Ticket;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class TicketDaoImpl implements TicketDao {

    private static List<Ticket> ticketsList = new ArrayList<>();

    public TicketDaoImpl() {
    }

    public static void setTicketsList(List<Ticket> ticketsList) {
        TicketDaoImpl.ticketsList = ticketsList;
    }

    public static List<Ticket> getTicketsList() {
        return ticketsList;
    }

    @Override
    public Ticket save(@Nonnull Ticket ticket) {
        ticketsList.add(ticket);
        return ticket;
    }

    @Override
    public void remove(@Nonnull Ticket ticket) {
        ticketsList.remove(ticket);
    }

    @Override
    public Ticket getById(@Nonnull Long id) {
        return ticketsList.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);

    }


    @Override
    public Collection<Ticket> getAll() {
        return ticketsList;
    }
}
