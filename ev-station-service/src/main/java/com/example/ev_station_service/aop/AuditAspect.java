package com.example.ev_station_service.aop;

import com.example.ev_station_service.annotation.AuditAction;
import com.example.ev_station_service.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AuditAspect {
// use in Serviceimpl with CREATE / UPDATE / DELETE methods.

    @AfterReturning(value = "@annotation(audit)", returning = "result")
    public void auditLog(JoinPoint joinPoint, AuditAction audit, Object result){

        String action = audit.action(); // CREATE / UPDATE / DELETE
        String username = SecurityUtil.getCurrentUserRole();  // later replace with user

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        log.info("📌 AuditAspect LOG → User:{}, Action:{}, ClassName: {}, Method: {}.{}(), Result: {}",
                                  username, action, className, methodName, result);

    }

    // Execute After Throwing Exception
    @AfterThrowing(value = "@annotation(audit)", throwing = "ex")
    public void logError(JoinPoint joinPoint, AuditAction audit, Exception ex) {

        log.error("❌ AUDIT ERROR → Action: {}, Method: {}, Error: {}",
                audit.action(),
                joinPoint.getSignature().getName(),
                ex.getMessage());
    }
}

/*
🚀 When to Use What
Use Case	Aspect
Debug API flow	LoggingAspect
Track business actions	AuditAspect
Measure time	PerformanceAspect
Security check	SecurityAspect

🚀 Request Flow:
1. user call api ->
2. aop -> LoggingAspect @Before method call
3. "Entering logs:" in controller then Entering in service with logs
4. Execute any one method Ex: // CREATE / UPDATE / DELETE we use Annotation  @AuditAction(action = "create")
5. service method execute
6. call @AuditAction and print Logs with those action we perform\
7. loggingAspect => @AfterReturning method call
           Exiting logs print:  controller & service method

Example :
✅ Exiting: com.example.ev_station_service.service.impl.StationServiceImpl.deleteStation() result=null
2026-04-06 12:06:09 - 📌 AUDIT LOG → User:ADMIN, Action:DELETE, ClassName: com.example.ev_station_service.service.impl.StationServiceImpl, Method: deleteStation.null(), Result: {}
2026-04-06 12:06:09 - ✅ Exiting: com.example.ev_station_service.controller.StationController.delete() result=<200 OK OK,Deleted successfully,[]>
2026-04-06 12:07:43 - Resolving eureka endpoints via configuration
2026-04-06 12:11:32 - ➡️ Entering: com.example.ev_station_service.controller.StationController.getById() args=[4]
2026-04-06 12:11:32 - ➡️ Entering: com.example.ev_station_service.service.impl.StationServiceImpl.getStationById() args=[4]
 */