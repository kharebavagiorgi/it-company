package service;

import company.ITCompany;
import department.Department;
import employee.Auditable;
import employee.Employee;
import employee.Manageable;
import employee.Payable;
import exception.TaskAlreadyCompletedException;
import project.Reportable;
import task.Task;
import java.math.BigDecimal;

public class ITCompanyService {

    public void processEmployee(Employee employee) {
        System.out.println("Processing employee: " + employee.getName());
        BigDecimal bonus = employee.calculateBonus();
        BigDecimal totalCompensation = employee.getSalary().add(bonus);
        System.out.println("Total compensation: $" + totalCompensation);
    }

    public void assignTask(Manageable manager, Task task) {
        if (task.isCompleted()) {
            throw new TaskAlreadyCompletedException("Cannot assign a task that is already completed.");
        }
        System.out.println("Assigning task via a Manageable interface.");
        manager.assignTask(task);
        if (task.isCompleted()) {
            System.out.println("Task '" + task.getTaskName() + "' has been marked as completed.");
        }
    }

    public void generateProjectReport(Reportable project) {
        System.out.println("Generating report via a Reportable interface.");
        project.generateReport();
    }

    public BigDecimal getPayableSalary(Payable payable) {
        System.out.println("Calculating salary via a Payable interface.");
        return payable.calculateSalary();
    }

    public void performAudit(Auditable auditable) {
        System.out.println("Performing audit via an Auditable interface.");
        auditable.auditProject();
    }

    public BigDecimal calculateTotalEmployeeSalary(ITCompany company) {
        BigDecimal totalSalary = BigDecimal.ZERO;
        for (Department dept : company.getDepartments()) {
            if (dept != null) {
                for (Employee emp : dept.getEmployees()) {
                    if (emp != null) {
                        totalSalary = totalSalary.add(emp.getSalary());
                    }
                }
            }
        }
        return totalSalary;
    }

}