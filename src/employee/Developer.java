package employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Developer extends Employee implements Codeable {

    private String programmingLanguage;
    private int yearsOfExperience;

    public Developer(String name, int id, LocalDate hireDate, BigDecimal salary, boolean fullTime, String programmingLanguage, int yearsOfExperience) {
        super(name, id, hireDate, salary, fullTime);
        this.programmingLanguage = programmingLanguage;
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public BigDecimal calculateBonus() {
        return getSalary().multiply(new BigDecimal("0.15"));
    }

    @Override
    public void writeCode() {
        System.out.println(getName() + " is writing code in " + programmingLanguage + ".");
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

}