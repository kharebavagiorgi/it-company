package com.solvd.itcompany.task;

public enum TaskComplexity {
    EASY(4.0),
    MEDIUM(8.0),
    HARD(16.0);

    private final double estimatedHours;

    TaskComplexity(double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public double getEstimatedHours() {
        return estimatedHours;
    }

    public String getSuggestion() {
        if (this == HARD) {
            return "Need two developers.";
        }
        return "Can be handled by a single developer.";
    }
}