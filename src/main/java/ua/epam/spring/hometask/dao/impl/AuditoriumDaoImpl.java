package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.PostConstruct;
import java.sql.Array;
import java.util.*;


@Repository("auditoriumDao")
public class AuditoriumDaoImpl implements AuditoriumDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AuditoriumDaoImpl() {
    }


    @PostConstruct
    public void init() {
        Auditorium auditoriumBlue = new Auditorium();
        auditoriumBlue.setName("blue auditorium");
        auditoriumBlue.setNumberOfSeats(200L);
        Set<Long> vipSeats = new HashSet<>();
        vipSeats.add(10L);
        vipSeats.add(20L);
        vipSeats.add(30L);
        auditoriumBlue.setVipSeats(vipSeats);

        Auditorium auditoriumRed = new Auditorium();
        auditoriumRed.setName("red auditorium");
        auditoriumRed.setNumberOfSeats(100L);
        Set<Long> vipSeatsRed = new HashSet<>();
        vipSeatsRed.add(1L);
        vipSeatsRed.add(2L);
        vipSeatsRed.add(3L);
        auditoriumRed.setVipSeats(vipSeatsRed);

        jdbcTemplate.update("INSERT INTO auditorium (name, numberOfSeats , vipSeats) VALUES (?,?,?)",
                auditoriumBlue.getName(),
                auditoriumBlue.getNumberOfSeats(),
                auditoriumBlue.getVipSeats().toArray((new Long[0]))
        );

        jdbcTemplate.update("INSERT INTO auditorium (name, numberOfSeats , vipSeats) VALUES (?,?,?)",
                auditoriumRed.getName(),
                auditoriumRed.getNumberOfSeats(),
                auditoriumRed.getVipSeats().toArray((new Long[0]))
        );

    }

    @Override
    public Auditorium save(@Nonnull Auditorium auditorium) {
        Long count = 0L;

        if (auditorium.getId() != null) {
            count = jdbcTemplate.queryForObject("SELECT count(*) FROM AUDITORIUM where id =?", new Object[]{auditorium.getId()}, Long.class);
        }

        if (count.equals(0L)) {
            jdbcTemplate.update("INSERT INTO auditorium (name, numberOfSeats, vipSeats) VALUES (?,?,?)",
                    auditorium.getName(),
                    auditorium.getNumberOfSeats(),
                    auditorium.getVipSeats().toArray((new Long[0]))
            );
        } else {
            jdbcTemplate.update("UPDATE auditorium SET name=?, numberOfSeats=?, vipSeats=? WHERE id =?",
                    auditorium.getName(),
                    auditorium.getNumberOfSeats(),
                    auditorium.getVipSeats().toArray((new Long[0])),
                    auditorium.getId()
            );
        }
        return auditorium;
    }

    @Override
    public void remove(@Nonnull Auditorium auditorium) {
        jdbcTemplate.update("DELETE FROM auditorium WHERE name = ?", auditorium.getName());
    }

    @Override
    public Auditorium getById(@Nonnull Long id) {
        List<Auditorium> list = jdbcTemplate.query("SELECT * FROM auditorium WHERE id = ?", new Object[]{id}, auditoriumRowMapperRowMapper);
        return list.isEmpty() ? null : list.get(0);
    }


    @Override
    public Collection<Auditorium> getAll() {
        return jdbcTemplate.query("SELECT * FROM auditorium", auditoriumRowMapperRowMapper);
    }

    @Override
    public Auditorium getByName(@Nonnull String name) {
        List<Auditorium> list = jdbcTemplate.query("SELECT * FROM auditorium WHERE name = ?", new Object[]{name}, auditoriumRowMapperRowMapper);
        return list.isEmpty() ? null : list.get(0);
    }

    private static final RowMapper<Auditorium> auditoriumRowMapperRowMapper = (rs, rowNum) -> {
        Auditorium auditorium = new Auditorium();
        auditorium.setId(rs.getLong("id"));
        auditorium.setName(rs.getString("name"));
        auditorium.setNumberOfSeats(rs.getLong("numberOfSeats"));
        Array vip_seats = rs.getArray("vipSeats");
        if (vip_seats != null) {
            Set<Long> vipSeats = new HashSet<>(Arrays.asList((Long[]) vip_seats.getArray()));
            auditorium.setVipSeats(vipSeats);
        }
        return auditorium;
    };
}
