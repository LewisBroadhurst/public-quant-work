package com.x.quant.udemy.multithreading_concurrency_performance.thread_fundamentals;

import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {

    List<Runnable> tasks;
    int numberOfTasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
        this.numberOfTasks = tasks.size();
    }

    public void executeAll() {

        List<Thread> threads = new ArrayList<>(numberOfTasks);

        for (Runnable task : tasks) {
            threads.add(new Thread(task));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
