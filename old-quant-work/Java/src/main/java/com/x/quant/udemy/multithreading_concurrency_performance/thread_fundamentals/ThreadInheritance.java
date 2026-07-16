package com.x.quant.udemy.multithreading_concurrency_performance.thread_fundamentals;

public class ThreadInheritance {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new NewThread();

        thread.start();
    }

    private static class NewThread extends Thread {
        @Override
        public void run() {
            // Now the variables are stored in the this. object
            this.getName();
            System.out.println("Hello from " + Thread.currentThread().getName());
        }
    }

}
