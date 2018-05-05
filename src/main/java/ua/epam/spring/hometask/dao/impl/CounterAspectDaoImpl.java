package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.CounterAspectDao;

import java.util.HashMap;
import java.util.Map;

//@Repository
public class CounterAspectDaoImpl implements CounterAspectDao {

    private static Map<String, Long> eventsByNameStorage = new HashMap<>();
    private static Map<String, Long> eventsByPriceStorage = new HashMap<>();

    @Override
    public void saveEventValueCouterByName(String eventName) {
        if (eventsByNameStorage.containsKey(eventName)) {
            eventsByNameStorage.put(eventName, (eventsByNameStorage.get(eventName) + 1L));
        } else {
            eventsByNameStorage.put(eventName, 1L);
        }
    }


    @Override
    public Map<String, Long> eventsByNameStorageGetAll() {
        return eventsByNameStorage;
    }

    @Override
    public void saveEventValueCouterByPrice(String eventName) {
        if (eventsByPriceStorage.containsKey(eventName)) {
            eventsByPriceStorage.put(eventName, (eventsByPriceStorage.get(eventName) + 1L));
        } else {
            eventsByPriceStorage.put(eventName, 1L);
        }
    }

    @Override
    public Map<String, Long> eventsByPriceStorageGetAll() {
        return eventsByPriceStorage;
    }


}
