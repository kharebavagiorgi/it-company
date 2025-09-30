package com.solvd.itcompany.service;

import com.solvd.itcompany.annotation.ReviewRequired;
import com.solvd.itcompany.company.ITCompany;
import com.solvd.itcompany.employee.Auditable;
import com.solvd.itcompany.employee.Employee;
import com.solvd.itcompany.employee.Manageable;
import com.solvd.itcompany.employee.Payable;
import com.solvd.itcompany.employee.Developer;
import com.solvd.itcompany.employee.EmployeeLevel;
import com.solvd.itcompany.exception.TaskAlreadyCompletedException;
import com.solvd.itcompany.project.Project;
import com.solvd.itcompany.project.ProjectStatus;
import com.solvd.itcompany.project.Reportable;
import com.solvd.itcompany.task.Task;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.*;
import java.util.stream.Collectors;
import com.solvd.itcompany.project.Priority;

public class ITCompanyService {

    @ReviewRequired(reviewer = "LeadDev", priority = 1, date = "2025-10-01") // Custom Annotation
    private final String serviceVersion = "1.1";

    public void executeEmployeeAction(Employee employee, Consumer<Employee> action) {
        action.accept(employee);
        System.out.println("Action complete for " + employee.getName() + ".");
    }

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

    @ReviewRequired(reviewer = "FinanceTeam", priority = 2, date = "2025-10-01")
    public BigDecimal calculateTotalEmployeeSalary(ITCompany company) {
        return company.getDepartments().stream()
                .flatMap(department -> department.getEmployees().stream())
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void demonstrateStreams(ITCompany company) {
        List<Employee> allEmployees = company.getDepartments().stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.toList());

        List<Developer> midLevelDevelopers = allEmployees.stream()
                .filter(e -> e instanceof Developer)
                .map(e -> (Developer) e)
                .filter(d -> d.getLevel().ordinal() >= EmployeeLevel.MID.ordinal())
                .collect(Collectors.toList());
        System.out.println("2. Mid-Level Developers (" + midLevelDevelopers.size() + "): " +
                midLevelDevelopers.stream().map(Employee::getName).collect(Collectors.joining(", ")));

        String projectNames = company.getProjects().stream()
                .map(p -> p.getProjectName().toUpperCase())
                .collect(Collectors.joining(" | "));
        System.out.println("3. All Project Names: " + projectNames);

        long highPriorityCount = company.getProjects().stream()
                .filter(p -> p.getPriority() == Priority.HIGH)
                .count();
        System.out.println("4. High Priority Projects Count: " + highPriorityCount);

        double avgYoE = allEmployees.stream()
                .filter(e -> e instanceof Developer)
                .map(e -> (Developer) e)
                .mapToDouble(Developer::getYearsOfExperience)
                .average()
                .orElse(0.0);
        System.out.printf("5. Average Developer Years of Experience: %.2f%n", avgYoE);

        Employee highestPaid = allEmployees.stream()
                .max((e1, e2) -> e1.getSalary().compareTo(e2.getSalary()))
                .orElse(null);
        System.out.println("6. Highest Paid Employee: " + (highestPaid != null ? highestPaid.getName() + " ($" + highestPaid.getSalary() + ")" : "N/A"));

        Map<Boolean, List<Employee>> rolePartition = allEmployees.stream()
                .collect(Collectors.partitioningBy(e -> e instanceof Developer));

        System.out.println("7. Partition: Developers (" + rolePartition.get(true).size() + ") vs Other Roles (" + rolePartition.get(false).size() + ")");
    }

    public void processEmployeesWithLambdas(ITCompany company) {

        List<Employee> allEmployees = company.getDepartments().stream()
                .flatMap(d -> d.getEmployees().stream())
                .collect(Collectors.toList());

        Predicate<Employee> isSeniorOrLead = emp -> emp.getLevel().ordinal() >= 2;
        System.out.println("\n[Predicate] Senior/Lead Employees:");
        allEmployees.stream()
                .filter(isSeniorOrLead)
                .map(Employee::getName)
                .forEach(System.out::println);

        Function<Employee, BigDecimal> getBonus = Employee::calculateBonus;
        System.out.println("\n[Function] Total Bonus to Pay: $" + allEmployees.stream()
                .map(getBonus)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        Consumer<Project> advanceStatus = p -> {
            ProjectStatus oldStatus = p.getStatus();
            p.setStatus(p.getStatus().nextStatus());
            System.out.printf("Project '%s' moved from %s to %s.%n", p.getProjectName(), oldStatus, p.getStatus());
        };
        System.out.println("\n[Consumer] Advancing Project Statuses:");
        company.getProjects().forEach(advanceStatus);

        Supplier<Integer> generateNewTaskId = () -> (int) (Math.random() * 1000) + 100;
        System.out.println("\n[Supplier] Generated New Task ID: " + generateNewTaskId.get());

        BiConsumer<Project, Double> updateExpenses = (p, exp) -> {
            p.setExpenses(p.getExpenses() + exp);
            System.out.printf("Project '%s' added $%,.2f in expenses.%n", p.getProjectName(), exp);
        };
        System.out.println("\n[BiConsumer] Updating Project Expenses:");
        company.getProjects().forEach(p -> updateExpenses.accept(p, 5000.00));

        Formatter<Employee> employeeFormatter = e -> String.format("ID: %d | Name: %s | Min Salary: $%,.2f",
                e.getId(), e.getName(), e.getSalary().subtract(e.calculateBonus()));

        System.out.println("\n[Custom Formatter] Formatted Employee Data:");
        allEmployees.stream()
                .limit(2)
                .map(employeeFormatter::format)
                .forEach(System.out::println);

        FinancialMetric totalPayroll = (salaries) -> {
            BigDecimal total = BigDecimal.ZERO;
            for (BigDecimal s : salaries) {
                total = total.add(s);
            }
            return total;
        };
        BigDecimal monthlyPayroll = allEmployees.stream().map(Employee::getSalary).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n[Custom FinancialMetric] Total Monthly Payroll: $" + totalPayroll.calculate(monthlyPayroll));

        ActionVerifier<Project> isHighPriority = p -> p.getPriority() == Priority.HIGH;
        System.out.println("\n[Custom ActionVerifier] High Priority Projects:");
        company.getProjects().stream()
                .filter(isHighPriority::verify)
                .map(Project::getProjectName)
                .forEach(System.out::println);
    }

}