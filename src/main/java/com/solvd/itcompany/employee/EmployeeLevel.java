package com.solvd.itcompany.employee;

import java.math.BigDecimal;

public enum EmployeeLevel {
    JUNIOR(new BigDecimal("0.05")),
    MID(new BigDecimal("0.10")),
    SENIOR(new BigDecimal("0.15")),
    LEAD(new BigDecimal("0.20")) {
        @Override
        public String getDescription() {
            return super.getDescription() + " (Requires mentorship and strategic vision.)";
        }
    };

    private final BigDecimal bonusRate;

    EmployeeLevel(BigDecimal bonusRate) {
        this.bonusRate = bonusRate;
    }

    public BigDecimal calculateBonus(BigDecimal salary) {
        return salary.multiply(bonusRate);
    }

    public String getDescription() {
        return this.name() + " level with a bonus rate of " + bonusRate.toPlainString();
    }
}