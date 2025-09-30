package com.solvd.itcompany.main;

import com.solvd.itcompany.company.ITCompany;
import com.solvd.itcompany.department.*;
import com.solvd.itcompany.employee.*;
import com.solvd.itcompany.exception.*;
import com.solvd.itcompany.project.*;
import com.solvd.itcompany.report.*;
import com.solvd.itcompany.service.ITCompanyService;
import com.solvd.itcompany.task.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting IT Company project...");

        // Uses a method from the complex Enum to set capacity
        int devMax = DepartmentType.DEVELOPMENT.getSuggestedMaxEmployees();

        ITCompany itCompany = new ITCompany("Solved", 1L, LocalDate.of(2010, 5, 15));
        ITCompanyService service = new ITCompanyService();

        try {
            // Employee invalidEmployee = new Developer("Invalid Dev", -10, LocalDate.now(), new BigDecimal("90000"), true, EmployeeLevel.JUNIOR, "C#", 6);
        } catch (RuntimeException e) {
            System.err.println("Caught an expected RuntimeException: " + e.getMessage());
        }

        Developer developer1 = new Developer("Giorgi Dev", 101, LocalDate.of(2020, 1, 1), new BigDecimal("70000"), true, EmployeeLevel.MID, "Java", 5);
        Developer developer2 = new Developer("Luka Dev", 102, LocalDate.of(2021, 5, 1), new BigDecimal("60000"), true, EmployeeLevel.JUNIOR, "Python", 3);
        Manager manager1 = new Manager("Nia Manager", 201, LocalDate.of(2018, 11, 1), new BigDecimal("120000"), true, EmployeeLevel.SENIOR, "Alpha", 10);
        Accountant accountant1 = new Accountant("Anna Acc", 301, LocalDate.of(2019, 3, 15), new BigDecimal("85000"), true, EmployeeLevel.MID, true, "Taxation");

        DevelopmentDepartment devDept = new DevelopmentDepartment("DevOps", "Block A", devMax, "Cloud Engineering");
        HRDepartment hrDept = new HRDepartment("Talent", "Block B", DepartmentType.HR.getSuggestedMaxEmployees(), 5);
        AccountingDepartment accDept = new AccountingDepartment("Finance", "Block C", DepartmentType.ACCOUNTING.getSuggestedMaxEmployees(), new BigDecimal("5000000.00"));

        itCompany.addDepartment(devDept);
        itCompany.addDepartment(hrDept);
        itCompany.addDepartment(accDept);

        devDept.addEmployee(developer1);
        devDept.addEmployee(developer2);
        devDept.addEmployee(manager1);
        accDept.addEmployee(accountant1);

        SoftwareProject softwareProject1 = new SoftwareProject("E-commerce Platform", new BigDecimal("500000.00"), LocalDateTime.now(), 5, Priority.HIGH, "Java", 123L);
        HardwareProject hardwareProject1 = new HardwareProject("IoT Device Prototype", new BigDecimal("250000.00"), LocalDateTime.now().plusDays(30), 3, Priority.MEDIUM, "Sensor Array", true);

        Task task1 = new Task("Implement Login API", 1, TaskComplexity.HARD.getEstimatedHours());
        Task task2 = new Task("Setup Database", 2, TaskComplexity.EASY.getEstimatedHours());

        try {
            itCompany.addProject(softwareProject1);
            itCompany.addProject(hardwareProject1);
        } catch (ProjectCreationException e) {
            System.err.println("Caught an expected CheckedException: " + e.getMessage());
        }

        // 1. LIST (Projects)
        itCompany.getProjects().stream()
                .map(Project::getProjectName)
                .forEach(name -> System.out.println("- " + name)); // Terminal: print each name

        // 2. SET (Departments)
        itCompany.getDepartments().stream()
                .forEach(dept -> System.out.println("- " + dept.getDepartmentName() + " has " + dept.getEmployeeCount() + " employees."));

        // 3. MAP (GenericDataStore)
        GenericDataStore<Integer, Employee> dataStore = new GenericDataStore<>();
        dataStore.add(developer1.getId(), developer1);
        dataStore.add(manager1.getId(), manager1);
        dataStore.getEntrySet().stream()
                .forEach(entry -> System.out.println("- Key: " + entry.getKey() + ", Value: " + entry.getValue().getName()));

        service.processEmployeesWithLambdas(itCompany);

        System.out.println("\nDemonstrating stream functions");
        service.demonstrateStreams(itCompany);

        // 2. Record
        System.out.println("\n--- Record and Complex Enum Demonstration ---");
        ProjectSummary softwareSummary = softwareProject1.getSummary();
        System.out.println("Record Summary (Canonical): " + softwareSummary);
        System.out.println("Record Summary (Custom Accessor): " + softwareSummary.getBudgetSummary());

        System.out.println("Project Priority Review Days: " + softwareProject1.getPriority().getDaysToReview());
        System.out.println("Task Complexity Suggestion: " + TaskComplexity.HARD.getSuggestion());
        System.out.println("Employee Bonus Rate (LEAD): " + EmployeeLevel.LEAD.getDescription());

        // Demonstrate AutoCloseable with try-with-resources
        System.out.println("\n--- Demonstrating AutoCloseable and try-with-resources (Existing) ---");
        try (ReportGenerator reportGenerator = new ReportGenerator()) {
            reportGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Employee anEmployee = new Developer("Polymorphic Dev", 999, LocalDate.now(), new BigDecimal("90000"), true, EmployeeLevel.SENIOR, "C#", 6);
        service.processEmployee(anEmployee);

        service.assignTask(manager1, task1);

        service.executeEmployeeAction(developer1, emp -> {
            BigDecimal increase = emp.getSalary().multiply(new BigDecimal("0.10"));
            BigDecimal newSalary = emp.getSalary().add(increase);
            emp.setSalary(newSalary);
            System.out.println("Gave 10% raise. New salary: $" + newSalary);
        });

        service.executeEmployeeAction(manager1, emp -> {
            if (emp instanceof Manager manager) {
                System.out.println(": Auditing " + manager.getTeamName() + ".");
                if (manager.getTeamSize() > 5) {
                    System.out.println("ALERT: Team size (" + manager.getTeamSize() + ") exceeds standard limit of 5.");
                } else {
                    System.out.println("INFO: Team size is within limits.");
                }
            }
        });

        // TaskAlreadyCompletedException
        try {
            service.assignTask(manager1, task1);
        } catch (TaskAlreadyCompletedException e) {
            System.err.println("Caught an expected RuntimeException: " + e.getMessage());
        }

        // Original logic for reporting/salary
        softwareProject1.setExpenses(150000);
        hardwareProject1.setExpenses(50000);
        service.generateProjectReport(softwareProject1);
        service.generateProjectReport(hardwareProject1);

        BigDecimal accountantSalary = service.getPayableSalary(accountant1);
        System.out.println("Accountant's total salary: $" + accountantSalary);
        service.performAudit(accountant1);

        BigDecimal totalCompanySalary = service.calculateTotalEmployeeSalary(itCompany);
        System.out.println("Total base salary: $" + totalCompanySalary);
    }
}