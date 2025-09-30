package com.solvd.itcompany.project;

import com.solvd.itcompany.exception.InvalidBudgetException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Project implements Reportable {

    protected String projectName;
    protected BigDecimal budget;
    protected LocalDateTime startDate;
    protected int teamSize;
    private double expenses;
    protected ProjectStatus status;
    protected Priority priority;

    public Project(String projectName, BigDecimal budget, LocalDateTime startDate, int teamSize, Priority priority) {
        if (budget.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidBudgetException("Project budget cannot be negative.");
        }
        this.projectName = projectName;
        this.budget = budget;
        this.startDate = startDate;
        this.teamSize = teamSize;
        this.status = ProjectStatus.PLANNING;
        this.priority = priority;
    }

    public final void getProjectDetails() {
        System.out.println("Project: " + projectName + ", Budget: " + budget + ", Status: " + status + ", Priority: " + priority);
    }

    @Override
    public void generateReport() {
        System.out.println("Generating general main.java.report for main.java.project: " + projectName);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public ProjectSummary getSummary() {
        return new ProjectSummary(projectName, budget, startDate, status);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", budget=" + budget +
                ", startDate=" + startDate +
                ", teamSize=" + teamSize +
                ", status=" + status +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return teamSize == project.teamSize &&
                Objects.equals(projectName, project.projectName) &&
                Objects.equals(budget, project.budget) &&
                Objects.equals(startDate, project.startDate) &&
                status == project.status &&
                priority == project.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, budget, startDate, teamSize, status, priority);
    }
}