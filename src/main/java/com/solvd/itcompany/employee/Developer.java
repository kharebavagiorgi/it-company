package com.solvd.itcompany.employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Developer extends Employee implements Codeable {

    private String programmingLanguage;
    private int yearsOfExperience;

    public Developer(String name, int id, LocalDate hireDate, BigDecimal salary, boolean fullTime, EmployeeLevel level, String programmingLanguage, int yearsOfExperience) {
        super(name, id, hireDate, salary, fullTime, level);
        this.programmingLanguage = programmingLanguage;
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public BigDecimal calculateBonus() {
        return super.calculateBonus(); // Delegates to the updated base class logic
    }

    @Override
    public void writeCode() {
        System.out.println(getName() + " is writing code in " + programmingLanguage + " at " + getLevel() + " level.");
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

}