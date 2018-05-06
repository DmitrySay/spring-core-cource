package ua.epam.spring.hometask.dao;

import java.util.Map;

public interface DiscountAspectDao {

   void count(Class clazz);

    Map<Class<?>, Long> getDiscountCounterStorage();
}
