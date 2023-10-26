package com.tpool.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Aspect
@Component
@Order(2)
public class ApiAspect {

    @Before("com.tpool.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void apiLog(){
        System.out.println("=======> API log");
    }

    @AfterReturning(
            pointcut = "execution(* RetrieveMemberships(..))",
            returning = "result"
    )
    public void afterReturningResult(List<String> result) {
        // remove all of the elements in the list
        result.clear();
        result.addAll(Arrays.asList("You", "are", "awesome", "!"));
    }





    @AfterThrowing(
            pointcut = "execution(* com.tpool.aopdemo.dao.MembershipDAOImpl.simulateException(..) )",
            throwing = "throwingException"
    )
    public void afterThrowing(Throwable throwingException){
        System.out.println("you threw an exception buddy");

        // print out the exception message
        System.out.println(throwingException.getMessage());
    }


//    @After("execution(* com.tpool.aopdemo.dao.MembershipDAOImpl.getAccount(..) )")
//    public void afterDemo(JoinPoint joinPoint) {
//        System.out.println("==> Executing @After (finally) advice");
//        System.out.println("==> Method signature: " + joinPoint.getSignature());
//        System.out.println("==> getKind(): " + joinPoint.getKind());
//    }

//    @Around("execution(* com.tpool.aopdemo.dao.MembershipDAOImpl.getAccount(..) )")
//    public Object aroundDemo(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//        // start time
//        long begin = System.currentTimeMillis();
//
//        // calls getAccount()
//        Object result = proceedingJoinPoint.proceed();
//
//        // end time
//        long end = System.currentTimeMillis();
//
//        long duration = end - begin;
//        System.out.println("\n======> DURATION " + duration + " milliseconds");
//
//        return result;
//    }

    @Around("execution(* com.tpool.aopdemo.dao.MembershipDAOImpl.getAccount(..) )")
    public Object handleException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("beginning of handleException()");

        Object result;
        try {
            result = proceedingJoinPoint.proceed();
        }
        catch (Exception e) {
            throw e;
//            result = "exception occured, but we don't really care";
        }

        System.out.println("end of handleException()");
        return result;
    }


}
