package ua.epam.spring.hometask.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.impl.AuditoriumDaoImpl;
import ua.epam.spring.hometask.dao.impl.EventDaoImpl;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;

import java.time.LocalDateTime;
import java.util.*;

@Configuration
@ComponentScan(basePackages = {"ua.epam.spring.hometask.*"})
@PropertySource({"classpath:auditoriums.properties"})
@EnableAspectJAutoProxy
@Import({StrategyConfig.class})

public class AppConfigTest {

    @Value("${blue.name}")
    private String blue_name;

    @Value("${blue.numberOfSeats}")
    private long blue_numberOfSeats;

    @Value("${red.name}")
    private String red_name;

    @Value("${red.numberOfSeats}")
    private long red_numberOfSeats;

    private Set<Long> blue_vipSeats = new HashSet<>(Arrays.asList(10L, 20L, 30L));
    private Set<Long> red_vipSeats = new HashSet<>(Arrays.asList(1L, 2L, 3L));

    @Bean
    public Auditorium auditorium1() {
        return new Auditorium(blue_name, blue_numberOfSeats, blue_vipSeats);
    }

    @Bean
    public Auditorium auditorium2() {
        return new Auditorium(red_name, red_numberOfSeats, red_vipSeats);
    }

    @Bean
    public List<Auditorium> auditoriumList() {
        List<Auditorium> auditoriumList = new ArrayList<>();
        auditoriumList.add(auditorium1());
        auditoriumList.add(auditorium2());
        return auditoriumList;
    }

    @Bean
    public AuditoriumDao auditoriumDao() {
        AuditoriumDaoImpl.setAuditoriumList(auditoriumList());
        return new AuditoriumDaoImpl();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Event event() {
        Event event = new Event();
        event.setName("New_Year_Party");
        NavigableSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(LocalDateTime.now());
        event.setAirDates(airDates);
        event.setBasePrice( 99.9 );
        event.setRating(EventRating.HIGH);
        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(LocalDateTime.now(), auditorium1());
        event.setAuditoriums( auditoriums );

        return event;
    }

    @Bean
    public EventDao eventDao(){
        List<Event> eventList = new ArrayList<>();
        eventList.add( event() );
        EventDaoImpl.setEventList(eventList);
        return new EventDaoImpl();
    }

}
