package com.solvd.itcompany.project;

public enum Priority {
    LOW(30),
    MEDIUM(15),
    HIGH(5) {
        {
            System.out.println("-> Project with HIGH priority has been initialized.");
        }
    };

    private final int daysToReview;

    Priority(int daysToReview) {
        this.daysToReview = daysToReview;
    }

    public int getDaysToReview() {
        return switch (this) {
            case HIGH -> 5;
            case MEDIUM -> 15;
            case LOW -> 30;
        };
    }
}