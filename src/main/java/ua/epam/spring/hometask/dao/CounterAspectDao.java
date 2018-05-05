package ua.epam.spring.hometask.dao;

import java.util.Map;

public interface CounterAspectDao {

    public void saveEventValueCouterByName(String eventName);

    public Map<String, Long> eventsByNameStorageGetAll();


    public Map<String, Long> eventsByPriceStorageGetAll();

    public void saveEventValueCouterByPrice(String eventName);

}
