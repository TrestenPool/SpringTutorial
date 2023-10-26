package com.tpool.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class LoggingAspect {
    @Before("com.tpool.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void CloudLog(){
        System.out.println("=======> Cloud log");
    }
}
