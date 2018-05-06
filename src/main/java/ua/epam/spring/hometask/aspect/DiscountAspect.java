package ua.epam.spring.hometask.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.dao.DiscountAspectDao;

@Aspect
@Component
public class DiscountAspect {


    @Autowired
    DiscountAspectDao discountAspectDao;

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.DiscountServiceImpl.getDiscount(..)))")
    private void discountServiceGetDiscount() {
    }

    @After("discountServiceGetDiscount()")
    public void discountServiceGetDiscountAfterCall(JoinPoint jp) {
        Class<?> clazz = jp.getTarget().getClass();
        discountAspectDao.count(clazz);
    }
}
