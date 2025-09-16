package employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Accountant extends Employee implements Payable, Auditable {

    private boolean certified;
    private String specialty;

    public Accountant(String name, int id, LocalDate hireDate, BigDecimal salary, boolean fullTime, boolean certified, String specialty) {
        super(name, id, hireDate, salary, fullTime);
        this.certified = certified;
        this.specialty = specialty;
    }

    @Override
    public BigDecimal calculateBonus() {
        return new BigDecimal("500.00");
    }

    @Override
    public BigDecimal calculateSalary() {
        return getSalary().add(calculateBonus());
    }

    @Override
    public void auditProject() {
        System.out.println(getName() + " is auditing a project's finances.");
    }

    public boolean isCertified() {
        return certified;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

}