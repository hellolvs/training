package com.qunar.fresh2017.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopLog {
    private static final Logger LOG = LoggerFactory
            .getLogger(AopLog.class);

    @Pointcut("execution( * com.qunar.fresh2017.spring.service.UserService.*(..))")
    public void aspect() {
    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            LOG.info("=======before {}", joinPoint.getSignature().getName());
            Object proceed = joinPoint.proceed();
            LOG.info("=======after {}", joinPoint.getSignature().getName());
            long end = System.currentTimeMillis();
            LOG.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            return proceed;
        } catch (Throwable e) {
            LOG.error(e.getMessage(), e);
            long end = System.currentTimeMillis();
            LOG.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : " + e.getMessage());
            throw e;
        }
    }
}