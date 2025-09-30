package com.solvd.itcompany.task;

public final class Task {

    private String taskName;
    private int taskId;
    private boolean completed;
    private double estimatedHours;

    public Task(String taskName, int taskId, double estimatedHours) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.estimatedHours = estimatedHours;
        this.completed = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public double getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

}