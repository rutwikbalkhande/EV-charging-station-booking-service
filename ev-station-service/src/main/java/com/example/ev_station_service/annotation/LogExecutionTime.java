package com.example.ev_station_service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {

    // create annotation @LogExcecution time. use in Service impli class with method: getAllStations(), getById().
    // pkg: annotation  => all classes impliment in aop pkg.
    // check pkg. "aop"  # class: "PerformanceAspect"  class there impliment method execute timing.
    // which is use in service impli layer method: getById, getAllStations.

}
