package com.example.ev_station_service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {

    // @LogExcecution time. use in method: "getAllStations(), getById()". Service impli class.
    // pkg: annotation  => all classes implement in aop pkg.
    // check pkg. "aop"  # class: "PerformanceAspect"  class there implement method execute timing.

}
