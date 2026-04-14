package com.example.ev_station_service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRole {

    String value();
}

// @CheckRole("ADMIN") // USER annotation created : using this class @interface CheckRole{ }  we create custom annotation.
// Custom Annotation Created.
// aop pkg => SecurityAspect