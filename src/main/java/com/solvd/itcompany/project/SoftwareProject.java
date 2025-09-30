package com.solvd.itcompany.project;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SoftwareProject extends Project {

    private String technologyStack;
    private long projectCode;

    public SoftwareProject(String projectName, BigDecimal budget, LocalDateTime startDate, int teamSize, Priority priority, String technologyStack, long projectCode) {
        super(projectName, budget, startDate, teamSize, priority);
        this.technologyStack = technologyStack;
        this.projectCode = projectCode;
    }

    @Override
    public void generateReport() {
        System.out.println("Generating software main.java.project main.java.report for " + getProjectName());
        System.out.println("Budget: " + getBudget() + ", Expenses: " + getExpenses() + ", Status: " + getStatus());
    }

    public String getTechnologyStack() {
        return technologyStack;
    }

    public void setTechnologyStack(String technologyStack) {
        this.technologyStack = technologyStack;
    }

    public long getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(long projectCode) {
        this.projectCode = projectCode;
    }
}