package employee;

import task.Task;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Manager extends Employee implements Manageable {

    private String teamName;
    private int teamSize;

    public Manager(String name, int id, LocalDate hireDate, BigDecimal salary, boolean fullTime, String teamName, int teamSize) {
        super(name, id, hireDate, salary, fullTime);
        this.teamName = teamName;
        this.teamSize = teamSize;
    }

    @Override
    public BigDecimal calculateBonus() {
        return getSalary().multiply(new BigDecimal("0.25"));
    }

    @Override
    public void assignTask(Task task) {
        System.out.println(getName() + " is assigning task '" + task.getTaskName() + "' to the team.");
    }

    @Override
    public void manageTeam() {
        System.out.println(getName() + " is managing the team of " + teamSize + " employees.");
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