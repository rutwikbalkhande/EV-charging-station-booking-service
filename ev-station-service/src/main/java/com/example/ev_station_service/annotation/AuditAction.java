package com.example.ev_station_service.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuditAction {

    String action();
}
// @AuditAspect(action="create)= use in Serviceimpl with "CREATE / UPDATE / DELETE" methods.
// implement all Custom Annotation logs logic in aop pkg.
