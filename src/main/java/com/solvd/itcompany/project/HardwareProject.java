package com.solvd.itcompany.project;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HardwareProject extends Project {

    private String hardwareType;
    private boolean requiresAssembly;

    public HardwareProject(String projectName, BigDecimal budget, LocalDateTime startDate, int teamSize, Priority priority, String hardwareType, boolean requiresAssembly) {
        super(projectName, budget, startDate, teamSize, priority);
        this.hardwareType = hardwareType;
        this.requiresAssembly = requiresAssembly;
    }

    @Override
    public void generateReport() {
        System.out.println("Generating hardware main.java.project main.java.report for " + getProjectName());
        System.out.println("Budget: " + getBudget() + ", Expenses: " + getExpenses() + ", Priority: " + getPriority());
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

    public boolean requiresAssembly() {
        return requiresAssembly;
    }

    public void setRequiresAssembly(boolean requiresAssembly) {
        this.requiresAssembly = requiresAssembly;
    }
}