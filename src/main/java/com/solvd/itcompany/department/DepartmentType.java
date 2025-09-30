package com.solvd.itcompany.department;

public enum DepartmentType {
    HR(50),
    ACCOUNTING(20),
    DEVELOPMENT(100);

    private final int suggestedMaxEmployees;

    DepartmentType(int suggestedMaxEmployees) {
        this.suggestedMaxEmployees = suggestedMaxEmployees;
    }

    public int getSuggestedMaxEmployees() {
        return suggestedMaxEmployees;
    }

    public String getSpecialDescription() {
        return "Department Type: " + this.name();
    }
}