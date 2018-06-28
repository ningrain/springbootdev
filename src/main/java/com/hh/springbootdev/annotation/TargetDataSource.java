package com.hh.springbootdev.annotation;

import com.hh.springbootdev.entity.CustomizeDS;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    CustomizeDS value() default CustomizeDS.MASTER;

}
