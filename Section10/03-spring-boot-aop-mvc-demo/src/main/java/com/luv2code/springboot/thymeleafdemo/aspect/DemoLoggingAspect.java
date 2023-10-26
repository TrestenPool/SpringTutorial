package com.luv2code.springboot.thymeleafdemo.aspect;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    // setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..) )")
    private void forControllerPackage() {}
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..) )")
    private void forServicePackage() {}
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..) )")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage() ")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        System.out.println("\n\n====================================");

       // display method we are calling
        System.out.println("Method: " + joinPoint.getSignature().getName());

        // display the arguments supplied to the method
        System.out.println("Args: " + Arrays.toString(joinPoint.getArgs()));

        System.out.println("=======================================");
    }


    @AfterReturning(pointcut = "forAppFlow()",
        returning = "returnObject")
    public void afterReturningDemo(JoinPoint joinPoint, Object returnObject) {
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("=====> in @AfterReturning: from method: " +theMethod);



        logger.info("====> in @AfterResult: result: " + returnObject);
    }

}
