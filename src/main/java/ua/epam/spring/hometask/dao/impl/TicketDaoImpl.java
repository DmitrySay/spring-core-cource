package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.config.AppConfig;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.domain.Ticket;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository("ticketDao")
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

      public TicketDaoImpl() {
    }



    @Override
    public Ticket save(@Nonnull Ticket ticket) {

        return ticket;
    }

    @Override
    public void remove(@Nonnull Ticket ticket) {

    }

    @Override
    public Ticket getById(@Nonnull Long id) {
        return null;

    }


    @Override
    public Collection<Ticket> getAll() {
        return null;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        TicketDao ticketDao = (TicketDao) ctx.getBean("ticketDao");

    }
}
