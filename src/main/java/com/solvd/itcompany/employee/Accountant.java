package com.solvd.itcompany.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Accountant extends Employee implements Payable, Auditable {

    private boolean certified;
    private String specialty;

    public Accountant(String name, int id, LocalDate hireDate, BigDecimal salary, boolean fullTime, EmployeeLevel level, boolean certified, String specialty) {
        super(name, id, hireDate, salary, fullTime, level);
        this.certified = certified;
        this.specialty = specialty;
    }

    @Override
    public BigDecimal calculateBonus() {
        return super.calculateBonus(); // Delegates to the updated base class logic
    }

    @Override
    public BigDecimal calculateSalary() {
        // Now includes the bonus calculated by the Enum
        return getSalary().add(calculateBonus());
    }

    @Override
    public void auditProject() {
        System.out.println(getName() + " is auditing a main.java.project's finances. Certified: " + certified);
    }

    public boolean isCertified() {
        return certified;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

}