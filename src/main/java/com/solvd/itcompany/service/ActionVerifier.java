package com.solvd.itcompany.service;

@FunctionalInterface
public interface ActionVerifier<T> {
    boolean verify(T t);
}