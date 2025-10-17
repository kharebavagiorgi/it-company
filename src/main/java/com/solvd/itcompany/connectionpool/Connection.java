package com.solvd.itcompany.connectionpool;

import java.util.UUID;

public class Connection {
    private final String id;

    public Connection() {
        // Create a unique, short ID for logging
        this.id = "Conn-" + UUID.randomUUID().toString().substring(0, 4);
        System.out.println(Thread.currentThread().getName() + " created new " + id);
    }

    public String getId() {
        return id;
    }

    // Mock DAO method (simulates create, get, update, delete)
    public void executeQuery(String query) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " executing '" + query + "' using " + id);
        // Simulate work being done (100ms latency)
        Thread.sleep(100);
    }
}
