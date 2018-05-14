package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl() {
    }

    private User userMapper(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmail(rs.getString("email"));
        Date date = rs.getDate("birthDay");
        if (null != date) {
            user.setBirthday(date.toLocalDate());
        }
        return user;
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        List<User> list = jdbcTemplate.query("SELECT * FROM user WHERE email = ?", new Object[]{email},
                (rs, rowNum) -> {
                    User user = userMapper(rs, rowNum);
                    return user;
                });
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void remove(@Nonnull User user) {
        jdbcTemplate.update("DELETE FROM user where email = ?", user.getEmail());
    }

    @Override
    public User getById(@Nonnull Long id) {
        List<User> list = jdbcTemplate.query("SELECT * FROM user WHERE id = ?", new Object[]{id},
                (rs, rowNum) -> {
                    User user = userMapper(rs, rowNum);
                    return user;
                });
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Collection<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM user", (rs, rowNum) -> {
            User user = userMapper(rs, rowNum);
            return user;
        });
    }

    @Override
    public User save(@Nonnull User user) {
        Long count = 0L;

        if (user.getId() != null) {
            count = jdbcTemplate.queryForObject("SELECT count(*) FROM USER where id =?", new Object[]{user.getId()}, Long.class);
        }


        if (count.equals(0L)) {
            jdbcTemplate.update("INSERT INTO user (firstName, lastName, email, birthDay) VALUES (?,?,?,?)",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getBirthday()
            );
        } else {
            jdbcTemplate.update("UPDATE USER SET firstName=?, lastName=?, email=?, birthDay=? WHERE id =?",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getBirthday(),
                    user.getId()
            );
        }

        Long userId = jdbcTemplate.queryForObject("SELECT id FROM USER ORDER BY id DESC LIMIT 1",  Long.class);
        user.setId(userId);
        return user;
    }


/*    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = (UserDao) ctx.getBean("userDao");
        System.out.println(userDao.getAll());
    }*/
}


