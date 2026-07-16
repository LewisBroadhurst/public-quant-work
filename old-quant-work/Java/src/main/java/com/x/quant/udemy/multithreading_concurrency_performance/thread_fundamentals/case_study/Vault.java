package com.x.quant.udemy.multithreading_concurrency_performance.thread_fundamentals.case_study;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vault {

    public static final int MAX_PASSWORD = 9999;

    public static void main(String[] args) {
        Random random = new Random();

        VaultToCrack vault = new VaultToCrack(random.nextInt(MAX_PASSWORD));

        ArrayList<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for (Thread thread : threads) {
            thread.start();
        }

    }

    private static class VaultToCrack {
        private int password;

        public VaultToCrack(int password) {
            this.password = password;
        }

        public boolean isCorrectPassword(int guess) throws InterruptedException {
            Thread.sleep(5);
            return this.password == guess;
        }
    }

    private static abstract class HackerThread extends Thread {
        protected VaultToCrack vault;

        public HackerThread(VaultToCrack vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            System.out.println("Starting thread: " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {

        public AscendingHackerThread(VaultToCrack vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASSWORD; guess++) {

                try {

                    if (vault.isCorrectPassword(guess)) {
                        System.out.println(this.getName() + " cracked the password: " + guess);
                        System.exit(0);
                    }

                } catch (InterruptedException e) {

                    throw new RuntimeException(e);

                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {

        public DescendingHackerThread(VaultToCrack vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess >= 0; guess--) {

                try
                {
                    if (vault.isCorrectPassword(guess)) {
                        System.out.println(this.getName() + " cracked the password: " + guess);
                        System.exit(0);
                    }
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 10; i > 0; i--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Police thread interrupted");
                }
                System.out.println(i);
            }
            System.out.println("Game over for you hackers");
            System.exit(0);
        }
    }
}
