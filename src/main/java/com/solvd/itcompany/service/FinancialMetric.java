package com.solvd.itcompany.service;

import java.math.BigDecimal;

@FunctionalInterface
public interface FinancialMetric {
    BigDecimal calculate(BigDecimal... values);
}