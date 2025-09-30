package com.solvd.itcompany.employee;

import com.solvd.itcompany.company.ITCompany;
import com.solvd.itcompany.exception.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Employee {

    protected String name;
    protected int id;
    protected LocalDate hireDate;
    protected BigDecimal salary;
    protected boolean fullTime;
    protected EmployeeLevel level;

    public Employee(String name, int id, LocalDate hireDate, BigDecimal salary, boolean fullTime, EmployeeLevel level) {
        if (id <= 0) {
            throw new InvalidEmployeeIdException("Employee ID must be a positive number.");
        }
        this.name = name;
        this.id = id;
        this.hireDate = hireDate;
        this.salary = salary;
        this.fullTime = fullTime;
        this.level = level; // ADDED: Assignment
        ITCompany.incrementTotalEmployees();
    }

    public BigDecimal calculateBonus() {
        return level.calculateBonus(getSalary());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }

    public EmployeeLevel getLevel() {
        return level;
    }

    public void setLevel(EmployeeLevel level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", level=" + level +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", fullTime=" + fullTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                fullTime == employee.fullTime &&
                Objects.equals(name, employee.name) &&
                Objects.equals(hireDate, employee.hireDate) &&
                Objects.equals(salary, employee.salary) &&
                level == employee.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, hireDate, salary, fullTime, level);
    }
}