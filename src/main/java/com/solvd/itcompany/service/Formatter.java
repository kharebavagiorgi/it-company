package com.solvd.itcompany.service;

@FunctionalInterface
public interface Formatter<T> {
    String format(T t);
}