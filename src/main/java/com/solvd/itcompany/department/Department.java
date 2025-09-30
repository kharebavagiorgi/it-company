package com.solvd.itcompany.department;

import com.solvd.itcompany.employee.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Department {

    protected String departmentName;
    protected String departmentLocation;
    protected ArrayList<Employee> employees;

    public Department(String departmentName, String departmentLocation, int maxEmployees) {
        this.departmentName = departmentName;
        this.departmentLocation = departmentLocation;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentLocation() {
        return departmentLocation;
    }

    public void setDepartmentLocation(String departmentLocation) {
        this.departmentLocation = departmentLocation;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentLocation='" + departmentLocation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(departmentLocation, that.departmentLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentName, departmentLocation);
    }
}