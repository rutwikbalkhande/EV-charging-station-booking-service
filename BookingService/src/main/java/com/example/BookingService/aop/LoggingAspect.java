package com.example.BookingService.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class LoggingAspect {

    // ✅ FIXED package path
    @Pointcut("execution(* com.example.BookingService.controller..*(..)) || " +
            "execution(* com.example.BookingService.service..*(..))" +
            "com.example.BookingService.service.impl")
    public void applicationPackagePointcut() {}

    // 🔹 Before method
    @Before("applicationPackagePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("➡️ Entering: {}.{}() args={}",
                joinPoint.getSignature().getDeclaringTypeName(),  // class name
                joinPoint.getSignature().getName(),                // method name
                Arrays.toString(joinPoint.getArgs()));           // data value argument pass in url ex.: 15
    }

    // 🔹 After returning
    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("✅ Exiting: {}.{}() result={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                result);
    }

    // 🔹 Exception
    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        log.error("❌ Exception in {}.{}() message={}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                ex.getMessage());
    }
}
