package com.solvd.itcompany.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.itcompany.department.Department;
import com.solvd.itcompany.exception.ProjectCreationException;
import com.solvd.itcompany.project.*;
import java.time.LocalDate;
import java.util.*;

public class ITCompany {

    private static final Logger LOGGER = LogManager.getLogger(ITCompany.class);
    private static int totalEmployees;

    static {
        LOGGER.info("2/5 Remote Days(From static block)");
        totalEmployees = 0;
    }

    private final String name;
    private final Long companyId;
    private final LocalDate foundationDate;
    private final Set<Department> departments;
    private final List<Project> projects;

    public ITCompany(String name, long companyId, LocalDate foundationDate) {
        this.name = name;
        this.companyId = companyId;
        this.foundationDate = foundationDate;
        this.departments = new HashSet<>();
        this.projects = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void addProject(Project project) throws ProjectCreationException {
        if (project.getBudget().doubleValue() < 100000) {
            throw new ProjectCreationException("Project budget is too low for new creation.");
        }
        projects.add(project);
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

    public Set<Department> getDepartments() {
        return departments;
    }

    public List<Project> getProjects() {
        return projects ;
    }

    public int getDepartmentCount() {
        return departments.size();
    }

    public int getProjectCount() {
        return projects.size();
    }

}
