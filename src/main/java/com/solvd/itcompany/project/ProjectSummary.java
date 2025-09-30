package com.solvd.itcompany.project;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProjectSummary(
        String projectName,
        BigDecimal budget,
        LocalDateTime startDate,
        ProjectStatus status) {

    public ProjectSummary {
        if (budget.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Budget cannot be negative.");
        }
    }

    public String getBudgetSummary() {
        return String.format("%s (Budget: $%,.2f)", projectName, budget);
    }
}