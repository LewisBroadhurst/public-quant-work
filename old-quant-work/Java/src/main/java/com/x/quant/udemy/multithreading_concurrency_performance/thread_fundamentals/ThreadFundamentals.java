package com.x.quant.udemy.multithreading_concurrency_performance.thread_fundamentals;

public class ThreadFundamentals {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            // Code that will be run in a new thread
            System.out.println("We are in the thread: " + Thread.currentThread().getName() + ".");
            System.out.println("Current thread priority is: " + Thread.currentThread().getPriority() + ".");
        });

        thread.setName("New Worker Thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        // Set the uncaught exception handler
        // Here we can clean up resources or provide some useful information to the developer
        thread.setUncaughtExceptionHandler(
                (t, e) -> System.out.println("A critical error happened in thread: " + t.getName() + " with error: " + e.getMessage())
        );

        // Start the thread
        System.out.println("We are in the thread: " + Thread.currentThread().getName() + " before starting a new thread");
        thread.start();
        System.out.println("We are in the thread: " + Thread.currentThread().getName() + " after starting a new thread");

        Thread.sleep(10000);
    }


}
