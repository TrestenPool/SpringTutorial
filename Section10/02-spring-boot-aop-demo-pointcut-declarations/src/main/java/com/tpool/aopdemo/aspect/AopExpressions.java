package com.tpool.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {
    // all methods that are in the dao package
    @Pointcut("execution(* com.tpool.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){};

    // getter methods
    @Pointcut("execution(* com.tpool.aopdemo.dao.*.get*(..))")
    public void DaoGetters(){};

    // setter methods
    @Pointcut("execution(* com.tpool.aopdemo.dao.*.set*(..))")
    public void DaoSetters(){};

    // all in the dao package but no getters or setters
    @Pointcut("forDaoPackage() && !(DaoGetters() || DaoSetters())")
    public void forDaoPackageNoGetterSetter(){}
}
