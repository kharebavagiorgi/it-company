package com.solvd.itcompany.department;

public class DevelopmentDepartment extends Department {

    private String specialization;

    public DevelopmentDepartment(String departmentName, String departmentLocation, int maxEmployees, String specialization) {
        super(departmentName, departmentLocation, maxEmployees);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}