package ua.epam.spring.hometask.dao;

import java.util.List;
import java.util.Map;

public interface CounterAspectDao {

    void saveEventValueCouterByName(String eventName);

    public List<Map<String, Long>> eventsByNameStorageGetAll();

}