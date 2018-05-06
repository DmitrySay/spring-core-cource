package ua.epam.spring.hometask.dao;

import java.util.Map;

public interface CounterAspectDao {

    void saveEventValueCouterByName(String eventName);

    Map<String, Long> eventsByNameStorageGetAll();

    Map<String, Long> eventsByPriceStorageGetAll();

    void saveEventValueCouterByPrice(String eventName);

    void saveEventValueCouterByTicket(String eventName);

    Map<String, Long> eventsByTicketsStorageGetAll();

}
