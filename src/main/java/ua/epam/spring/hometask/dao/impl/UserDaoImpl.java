package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

//@Repository("userDao")
public class UserDaoImpl implements UserDao {

    private static Map<String, User> usersMap = new LinkedHashMap<>();

    public UserDaoImpl() {
    }

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return usersMap.get(email);
    }

    @Override
    public User save(User user) {
        usersMap.put(user.getEmail(), user);
        return user;
    }

    @Override
    public void remove(User user) {
        usersMap.remove(user.getEmail(), user);

    }

    @Override
    public User getById(Long id) {
       return usersMap.entrySet().stream()
                .filter(e -> e.getValue().getId().equals(id))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);

    }

    @Override
    public Collection<User> getAll() {
        return usersMap.values() ;
    }
}


