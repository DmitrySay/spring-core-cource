package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.CounterAspectDao;

@Aspect
@Component
public class CounterAspect {

    @Autowired
    CounterAspectDao counterAspectDao;

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.EventServiceImpl.getByName(..))")
    private void eventServiceGetByName() {
    }

    @Before("eventServiceGetByName()")
    public void eventServiceGetByNameBeforeCall(JoinPoint joinPoint) {
        String eventName = (String) joinPoint.getArgs()[0];
        counterAspectDao.saveEventValueCouterByName(eventName);
    }

}
