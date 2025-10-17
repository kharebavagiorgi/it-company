package com.solvd.itcompany.connectionpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private final BlockingQueue<Connection> pool;
    private final int poolSize;

    private ConnectionPool(int initialSize) {
        this.poolSize = initialSize;
        this.pool = new ArrayBlockingQueue<>(initialSize);

        System.out.println("ConnectionPool initialized with size " + initialSize);

        for (int i = 0; i < initialSize; i++) {
            pool.offer(new Connection());
        }
    }

    public static ConnectionPool getInstance(int initialSize) {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool(initialSize);
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " waiting for connection...");
        Connection conn = pool.take();
        System.out.println(Thread.currentThread().getName() + " acquired " + conn.getId());
        return conn;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            pool.offer(connection);
            System.out.println(Thread.currentThread().getName() + " released " + connection.getId());
        }
    }
}
