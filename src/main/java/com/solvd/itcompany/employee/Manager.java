package com.solvd.itcompany.employee;

import com.solvd.itcompany.task.Task;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Manager extends Employee implements Manageable {

    private String teamName;
    private int teamSize;

    public Manager(String name, int id, LocalDate hireDate, BigDecimal salary, boolean fullTime, EmployeeLevel level, String teamName, int teamSize) {
        super(name, id, hireDate, salary, fullTime, level);
        this.teamName = teamName;
        this.teamSize = teamSize;
    }

    @Override
    public BigDecimal calculateBonus() {
        return super.calculateBonus(); // Delegates to the updated base class logic
    }

    @Override
    public void assignTask(Task task) {
        System.out.println(getName() + " is assigning main.java.task '" + task.getTaskName() + "' to the team. Priority: " + getLevel());
    }

    @Override
    public void manageTeam() {
        System.out.println(getName() + " is managing the team of " + teamSize + " employees, acting as a " + getLevel() + " manager.");
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
}