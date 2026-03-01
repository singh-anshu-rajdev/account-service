package com.smartBankElite.accountService.Configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("within(com.smartBankElite.accountService.controller..*) " +
            "|| within(com.smartBankElite.accountService.service..*) " +
            "|| within(com.smartBankElite.accountService.repository..*) " +
            "|| within(com.smartBankElite.accountService.serviceImpl..*)")
    public Object logsExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Started execution: {}", methodName);
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsed = System.currentTimeMillis() - startTime;
        logger.info("Finished execution: {} in {} ms", methodName, elapsed);
        return result;
    }
}
