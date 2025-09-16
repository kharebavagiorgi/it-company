package main;

import company.ITCompany;
import department.AccountingDepartment;
import department.DevelopmentDepartment;
import department.HRDepartment;
import employee.Accountant;
import employee.Developer;
import employee.Employee;
import employee.Manager;
import exception.ProjectCreationException;
import exception.TaskAlreadyCompletedException;
import project.HardwareProject;
import project.SoftwareProject;
import report.ReportGenerator;
import service.ITCompanyService;
import task.Task;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting IT Company project...");

        ITCompany itCompany = new ITCompany("Solved",1L, LocalDate.of(2010, 5, 15));

        ITCompanyService service = new ITCompanyService();


        try {
            // InvalidEmployeeIdException
            // Employee invalidEmployee = new Developer("Invalid Dev", -10, LocalDate.now(), new BigDecimal("90000"), true, "C#", 6);
            // InvalidBudgetException
            // Project invalidProject = new SoftwareProject("Invalid Project", new BigDecimal("-100"), LocalDateTime.now(), 5, "Java", 123L);
        } catch (RuntimeException e) {
            System.out.println("Caught an expected RuntimeException: " + e.getMessage());
        }

        Developer developer1 = new Developer("Giorgi Kharebava", 101, LocalDate.of(2020, 1, 15), new BigDecimal("75000"), true, "Java", 5);
        Developer developer2 = new Developer("Bogdan Rutskov", 102, LocalDate.of(2021, 6, 20), new BigDecimal("80000"), true, "Java", 4);
        Manager manager1 = new Manager("Manager", 201, LocalDate.of(2018, 9, 10), new BigDecimal("120000"), true, "DevOps Team", 10);
        Accountant accountant1 = new Accountant("Accountant", 301, LocalDate.of(2019, 3, 25), new BigDecimal("60000"), true, true, "Taxes");

        SoftwareProject softwareProject1 = new SoftwareProject("Billing System", new BigDecimal("500000.00"), LocalDateTime.now(), 5, "Spring Boot, Angular", 123456L);
        HardwareProject hardwareProject1 = new HardwareProject("IoT Device Prototype", new BigDecimal("150000.00"), LocalDateTime.now().plusWeeks(2), 3, "Embedded Systems", true);
        HardwareProject smallProject = new HardwareProject("Test Project", new BigDecimal("50000"), LocalDateTime.now(), 1, "Test Type", false);

        DevelopmentDepartment devDept = new DevelopmentDepartment("Software Development", "Building A", 5, "Web Development");
        HRDepartment hrDept = new HRDepartment("Human Resources", "Building B", 2, 5);
        AccountingDepartment accDept = new AccountingDepartment("Accounting & Finance", "Building C", 1, new BigDecimal("1000000.00"));

        Task task1 = new Task("Implement Login Functionality", 1, 40.5);

        itCompany.addDepartment(devDept);
        itCompany.addDepartment(hrDept);
        itCompany.addDepartment(accDept);

        // Demonstrate handling of a Checked Exception
        try {
            itCompany.addProject(softwareProject1);
            itCompany.addProject(hardwareProject1);
            itCompany.addProject(smallProject);
        } catch (ProjectCreationException e) {
            System.err.println("\nCaught checked exception: " + e.getMessage());
        }

        devDept.addEmployee(developer1);
        devDept.addEmployee(developer2);
        devDept.addEmployee(manager1);
        accDept.addEmployee(accountant1);
        System.out.println("\nTotal employees created (static): " + ITCompany.getTotalEmployees());

        // Demonstrate AutoCloseable with try-with-resources
        System.out.println("\n--- Demonstrating AutoCloseable and try-with-resources ---");
        try (ReportGenerator reportGenerator = new ReportGenerator()) {
            reportGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Employee anEmployee = new Developer("Polymorphic Dev", 999, LocalDate.now(), new BigDecimal("90000"), true, "C#", 6);
        service.processEmployee(anEmployee);

        service.assignTask(manager1, task1);

        // TaskAlreadyCompletedException
        try {
            service.assignTask(manager1, task1);
        } catch (TaskAlreadyCompletedException e) {
            System.err.println("Caught an expected RuntimeException: " + e.getMessage());
        }


        softwareProject1.setExpenses(150000);
        hardwareProject1.setExpenses(50000);
        service.generateProjectReport(softwareProject1);
        service.generateProjectReport(hardwareProject1);


        BigDecimal accountantSalary = service.getPayableSalary(accountant1);
        System.out.println("Accountant's total salary: $" + accountantSalary);
        service.performAudit(accountant1);

        BigDecimal totalCompanySalary = service.calculateTotalEmployeeSalary(itCompany);
        System.out.println("Total company salary for all departments: $" + totalCompanySalary);
    }
}