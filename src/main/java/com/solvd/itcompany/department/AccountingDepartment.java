package com.solvd.itcompany.department;

import java.math.BigDecimal;

public class AccountingDepartment extends Department {

    private BigDecimal monthlyBudget;

    public AccountingDepartment(String departmentName, String departmentLocation, int maxEmployees, BigDecimal monthlyBudget) {
        super(departmentName, departmentLocation, maxEmployees);
        this.monthlyBudget = monthlyBudget;
    }

    public BigDecimal getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(BigDecimal monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }
}