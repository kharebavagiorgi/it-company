package com.solvd.itcompany.connectionpool;

public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " running using Thread class.");
    }
}
