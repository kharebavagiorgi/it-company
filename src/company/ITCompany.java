package company;

import department.Department;
import exception.ProjectCreationException;
import project.Project;
import java.time.LocalDate;
import java.util.Arrays;

public class ITCompany {

    private static int totalEmployees;

    static {
        System.out.println("2/5 Remote Days(From static block)");
        totalEmployees = 0;
    }

    private final String name;
    private final Long companyId;
    private final LocalDate foundationDate;
    private Department[] departments;
    private Project[] projects;
    private int departmentCount;
    private int projectCount;

    public ITCompany(String name, long companyId, LocalDate foundationDate) {
        this.name = name;
        this.companyId = companyId;
        this.foundationDate = foundationDate;
        this.departments = new Department[5];
        this.projects = new Project[5];
    }

    public void addDepartment(Department department) {
        if (departmentCount >= departments.length) {
            departments = Arrays.copyOf(departments, departments.length + 5);
        }
        departments[departmentCount++] = department;
    }

    public void addProject(Project project) throws ProjectCreationException {
        if (projectCount >= projects.length) {
            projects = Arrays.copyOf(projects, projects.length + 5);
        }
        if (project.getBudget().doubleValue() < 100000) {
            throw new ProjectCreationException("Project budget is too low for new creation.");
        }
        projects[projectCount++] = project;
    }

    public String getCompanyName() {
        return this.name;
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }

    public static void incrementTotalEmployees() {
        totalEmployees++;
    }

    public long getCompanyId() {
        return companyId;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public Department[] getDepartments() {
        return departments;
    }

    public Project[] getProjects() {
        return projects;
    }

    public int getDepartmentCount() {
        return departmentCount;
    }

    public int getProjectCount() {
        return projectCount;
    }

}
