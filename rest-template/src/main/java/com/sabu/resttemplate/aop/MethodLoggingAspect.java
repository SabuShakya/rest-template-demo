package com.sabu.resttemplate.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class MethodLoggingAspect {

    @Around("@annotation(com.sabu.resttemplate.aop.MethodLogging)")
    public Object aroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Executing {} ", MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName());
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        log.info("Executed {}: with parameters : {} : executed in {} ms",
                MethodSignature.class.cast(joinPoint.getSignature()).getMethod().getName(),
                joinPoint.getArgs(),
                System.currentTimeMillis() - start);
        return result;
    }
}
