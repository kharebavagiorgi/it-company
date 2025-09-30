package com.solvd.itcompany.report;

public class ReportGenerator implements AutoCloseable {

    public void generate() {
        System.out.println("Generating a detailed main.java.report...");
    }

    @Override
    public void close() {
        System.out.println("Report Generator closed. All resources released.");
    }

}