package com.solvd.itcompany.project;

public enum ProjectStatus {
    PLANNING {
        @Override
        public ProjectStatus nextStatus() {
            return IN_PROGRESS;
        }
    },
    IN_PROGRESS {
        @Override
        public ProjectStatus nextStatus() {
            return TESTING;
        }
    },
    TESTING {
        @Override
        public ProjectStatus nextStatus() {
            return COMPLETED;
        }
    },
    COMPLETED {
        @Override
        public ProjectStatus nextStatus() {
            return COMPLETED;
        }
    };

    public abstract ProjectStatus nextStatus();
}