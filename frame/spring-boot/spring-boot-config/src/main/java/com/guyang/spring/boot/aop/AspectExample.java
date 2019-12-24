package com.guyang.spring.boot.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @author guyang <guyang@ebnew.com>
 * @description 切面例子
 * @date 2019-12-08 15:36
 */
@Component
@Aspect
@SuppressWarnings("all")
public class AspectExample {

    @Pointcut("execution(* com.guyang.spring.boot.service.impl..*.*(..))")
    public void pointCut() {}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before........");
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        System.out.println("around start .....");
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around end .....");

        return result;
    }

}
