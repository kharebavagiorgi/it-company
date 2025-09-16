package department;

import employee.Employee;
import exception.DepartmentFullException;
import java.util.Objects;

public abstract class Department {

    protected String departmentName;
    protected String departmentLocation;
    protected Employee[] employees;
    private int employeeCount;

    public Department(String departmentName, String departmentLocation, int maxEmployees) {
        this.departmentName = departmentName;
        this.departmentLocation = departmentLocation;
        this.employees = new Employee[maxEmployees];
    }

    public void addEmployee(Employee employee) {
        if (employeeCount >= employees.length) {
            throw new DepartmentFullException("Cannot add employee. Department array is full.");
        }
        employees[employeeCount++] = employee;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentLocation() {
        return departmentLocation;
    }

    public void setDepartmentLocation(String departmentLocation) {
        this.departmentLocation = departmentLocation;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentLocation='" + departmentLocation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(departmentLocation, that.departmentLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentName, departmentLocation);
    }
}