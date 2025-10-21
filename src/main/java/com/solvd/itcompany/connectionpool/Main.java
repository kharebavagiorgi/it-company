package com.solvd.itcompany.connectionpool;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int POOL_SIZE = 5;
    private static final int THREAD_COUNT = 7;

    public static void main(String[] args) throws InterruptedException {

        ConnectionPool pool = ConnectionPool.getInstance(POOL_SIZE);

        Thread t1 = new Thread(new MyRunnable("Runnable-T1"), "Runnable-T1");
        MyThread t2 = new MyThread("Thread-T2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Pool size: " + POOL_SIZE + ", Total Threads: " + THREAD_COUNT);

        List<Thread> workers = new ArrayList<>();
        for (int i = 1; i <= THREAD_COUNT; i++) {
            Thread worker = new Thread(
                    new ConnectionWorker(pool, "Task-" + i),
                    "Worker-T" + i
            );
            workers.add(worker);
            worker.start();
        }

        System.out.println("\nMain thread waiting for all 7 workers to complete...");
        for (Thread worker : workers) {
            worker.join();
        }

        System.out.println("All 7 manual threads have completed their tasks.");

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 1; i <= THREAD_COUNT; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(
                    new ConnectionWorker(pool, "CFTask-" + i)
            );
            futures.add(future);
        }

        System.out.println("\nMain thread waiting for all CompletableFutures to complete...");
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        try {
            allOf.get(30, TimeUnit.SECONDS);
            System.out.println("All CompletableFutures tasks completed.");
        } catch (TimeoutException e) {
            System.err.println("CompletableFuture tasks timed out.");
        } catch (ExecutionException e) {
            System.err.println("One or more CompletableFuture tasks failed: " + e.getCause());
        }

        executor.shutdown();
    }
}
