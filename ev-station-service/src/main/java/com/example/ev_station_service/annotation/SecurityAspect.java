package com.example.ev_station_service.annotation;

import com.example.ev_station_service.security.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class SecurityAspect {

    @Before("@annotation(checkRole)")
    public void checkRole(JoinPoint joinPoint, CheckRole checkRole) {

        log.info("AOP triggered");

        String requiredRole = checkRole.value();
        String userRole = SecurityUtil.getCurrentUserRole();   // security folder: securityutil class created value role taken .

        log.info("Required: {}, User: {}", requiredRole, userRole);

        if (userRole == null || !userRole.equalsIgnoreCase(requiredRole)) {
            throw new RuntimeException("Access Denied! Required: " + requiredRole);
        }
    }
}
/*
AOP Flow:
Step 1: AOP intercepts method
Step 2: Read required role (ADMIN)
Step 3: Get user role (USER)
Step 4: Compare
Step 5: If not match → throw error ❌
Step 6: Method NOT executed
 */