package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl() {
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        List<User> list = jdbcTemplate.query("SELECT * FROM user WHERE email = ?", new Object[]{email},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getLong(1));
                    user.setFirstName(rs.getString(2));
                    user.setLastName(rs.getString(3));
                    user.setEmail(rs.getString(4));
                    Date date = rs.getDate(5);
                    if (null != date) {
                        user.setBirthday(date.toLocalDate());
                    }
                    return user;
                });
        return list.isEmpty() ? null : list.get(0);
    }


    @Override
    public User save(@Nonnull User user) {
        jdbcTemplate.update("INSERT INTO user (firstName, lastName, email, birthDay) VALUES (?,?,?,?)",
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthday()
        );

        List<User> list = jdbcTemplate.query("SELECT * FROM user WHERE email = ?", new Object[]{user.getEmail()},
                (rs, rowNum) -> {
                    User userNew = new User();
                    userNew.setId(rs.getLong(1));
                    userNew.setFirstName(rs.getString(2));
                    userNew.setLastName(rs.getString(3));
                    userNew.setEmail(rs.getString(4));
                    Date date = rs.getDate(5);
                    if (null != date) {
                        userNew.setBirthday(date.toLocalDate());
                    }
                    return userNew;
                });
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void remove(@Nonnull User user) {
        jdbcTemplate.update("DELETE FROM user where email = ?",  user.getEmail());
    }

    @Override
    public User getById(@Nonnull Long id) {
        List<User> list = jdbcTemplate.query("SELECT * FROM user WHERE id = ?", new Object[]{id},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getLong(1));
                    user.setFirstName(rs.getString(2));
                    user.setLastName(rs.getString(3));
                    user.setEmail(rs.getString(4));
                    Date date = rs.getDate(5);
                    if (null != date) {
                        user.setBirthday(date.toLocalDate());
                    }
                    return user;
                });
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Collection<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM user", (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong(1));
            user.setFirstName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setEmail(rs.getString(4));
            Date date = rs.getDate(5);
            if (null != date) {
                user.setBirthday(date.toLocalDate());
            }
            return user;
        });
    }

    /*  public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = (UserDao) ctx.getBean("userDao");

    }*/
}


