package com.solvd.itcompany.connectionpool;

public class ConnectionWorker implements Runnable {
    private final ConnectionPool pool;
    private final String taskName;

    public ConnectionWorker(ConnectionPool pool, String taskName) {
        this.pool = pool;
        this.taskName = taskName;
    }

    @Override
    public void run() {
        Connection connection = null;
        try {
            connection = pool.getConnection();
            connection.executeQuery("REQUEST_DATA_FOR_" + taskName);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(taskName + " was interrupted.");
        } finally {
            if (connection != null) {
                pool.releaseConnection(connection);
            }
        }
    }
}
