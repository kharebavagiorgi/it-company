package com.solvd.itcompany.exception;

public class TaskAlreadyCompletedException extends RuntimeException {

    public TaskAlreadyCompletedException(String message) {
        super(message);
    }

}
