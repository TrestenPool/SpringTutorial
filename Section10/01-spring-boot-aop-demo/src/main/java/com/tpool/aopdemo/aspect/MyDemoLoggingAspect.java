package com.tpool.aopdemo.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// just a collection of related advices
@Aspect
@Component
public class MyDemoLoggingAspect {
    // this is where we add all our related advices for logging

    // let's start with an @Before advice
    // pointcut expression goes between the parens of @Before
    @Before("execution(* com.tpool.aopdemo.dao.*.*(..))")
    public void loggingAdvice() {
        System.out.println("===============================");
        System.out.println("===> Logging stuff here... <===");
        System.out.println("===============================");
    }
}
