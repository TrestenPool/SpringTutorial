package com.tpool.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Order(3)
public class SecurityAspect {
    @Before("com.tpool.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
    public void security(JoinPoint joinPoint){
        System.out.println("=======> security check");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

//        System.out.println("args");
//        // go through all of the arguments
//        Object[] args = joinPoint.getArgs();
//        for(var arg: args) {
//            System.out.println(arg);
//        }
//        System.out.println("======");
    }
}
