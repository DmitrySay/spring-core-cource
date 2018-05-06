package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.CounterAspectDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;

import java.util.Set;

@Aspect
@Component
public class CounterAspect {

    @Autowired
    CounterAspectDao counterAspectDao;

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.EventServiceImpl.getByName(..))")
    private void eventServiceGetByName() {
    }

    @Before("eventServiceGetByName()")
    public void eventServiceGetByNameBeforeCall(JoinPoint joinPoint) {
        String eventName = (String) joinPoint.getArgs()[0];
        counterAspectDao.saveEventValueCouterByName(eventName);
    }


    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.BookingServiceImpl.getTicketsPrice(..))")
    private void bookingServiceGetTicketsPrice() {
    }

    @Before("bookingServiceGetTicketsPrice()")
    public void bookingServiceGetTicketsPriceBeforeCall(JoinPoint joinPoint) {
        Event event = (Event) joinPoint.getArgs()[0];
        counterAspectDao.saveEventValueCouterByPrice(event.getName());
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.BookingServiceImpl.bookTickets(..))")
    private void bookingServiceBookTickets() {
    }

    @After("bookingServiceBookTickets()")
    public void bookingServiceBookTicketsAfterCall(JoinPoint joinPoint) {
        Set<Ticket> tickets = (Set<Ticket>) joinPoint.getArgs()[0];
        for (Ticket ticket : tickets) {
            Event event = ticket.getEvent();
            if (event.getName() != null) {
                counterAspectDao.saveEventValueCouterByTicket(event.getName());
            }
        }
    }

}
