package com.example.BookingService.aop;

import com.example.BookingService.annotation.AuditAction;
import com.example.BookingService.security.SecurityUtil;
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
// use in Serviceimpl with CREATE / UPDATE / DELETE methods. SecurityUtil.getCurrentRole available in pkg:= security

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