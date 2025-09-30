package com.solvd.itcompany.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom main.java.annotation to mark methods or fields that require a code review or special attention.
 * RetentionPolicy.RUNTIME is required for main.java.reflection to access it at runtime.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
public @interface ReviewRequired {
    String reviewer() default "Architect"; // Default reviewer
    int priority() default 1; // 1 (High) to 5 (Low)
    String date() default "N/A";
}