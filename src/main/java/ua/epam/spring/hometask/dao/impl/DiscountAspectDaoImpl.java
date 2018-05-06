package ua.epam.spring.hometask.dao.impl;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.DiscountAspectDao;

import java.util.HashMap;
import java.util.Map;

@Component
public class DiscountAspectDaoImpl implements DiscountAspectDao {

    private Map<Class<?>, Long> discountCounterStorage = new HashMap<>();


    @Override
    public void count(Class clazz) {
        if (!discountCounterStorage.containsKey(clazz)) {
            discountCounterStorage.put(clazz, 0L);
        }
        discountCounterStorage.put(clazz, discountCounterStorage.get(clazz) + 1L);
    }


    public Map<Class<?>, Long> getDiscountCounterStorage() {
        return discountCounterStorage;
    }
}
