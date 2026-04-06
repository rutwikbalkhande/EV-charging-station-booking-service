package com.example.ev_station_service.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class PerformanceAspect {
    // @logExecutionTime annotation use in service. Method: getStationById, getAlltations.
    // use pkg: annotation-> class: interface LogExecutionTime

    @Around("@annotation(com.example.ev_station_service.annotation.LogExecutionTime)" )
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis();

    Object result = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - start;

        log.info("⏱ Time Taken: {}.{}() took: {} ms",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                timeTaken);

        return result;

        //⏱ Time Taken: {}.{}() took: {} ms" => [class].[method]() took [time] ms
    }
}
