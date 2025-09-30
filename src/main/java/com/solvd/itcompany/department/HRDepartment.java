package com.solvd.itcompany.department;

import com.solvd.itcompany.employee.Manageable;
import com.solvd.itcompany.task.Task;

public class HRDepartment extends Department implements Manageable {

    private int recruiterCount;

    public HRDepartment(String departmentName, String departmentLocation, int maxEmployees, int recruiterCount) {
        super(departmentName, departmentLocation, maxEmployees);
        this.recruiterCount = recruiterCount;
    }

    @Override
    public void assignTask(Task task) {
        System.out.println("HR Department is handling the main.java.task: " + task.getTaskName());
        task.setCompleted(true);
    }

    @Override
    public void manageTeam() {
        System.out.println("HR Department is managing hiring and onboarding for " + getDepartmentName() + ".");
    }

    public int getRecruiterCount() {
        return recruiterCount;
    }

    public void setRecruiterCount(int recruiterCount) {
        this.recruiterCount = recruiterCount;
    }

}