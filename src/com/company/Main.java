package com.company;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong sum = new AtomicLong();
        long currentTime = System.currentTimeMillis();

        for (int i = 1; i <= 10000000; i++) {
            sum.addAndGet(i);
        }

        System.out.println("Single Thread  " + sum + "made calculation in  " + (System.currentTimeMillis() - currentTime));


        AtomicLong sum1 = new AtomicLong();
        Runnable runnable = () -> {
            for (int i = 1; i <= 5000000; i++) {
                sum1.addAndGet(i);
            }

        };

        Runnable runnable1 = () -> {
            for (int i = 5000001; i <= 10000000; i++) {
                sum1.addAndGet(i);
            }
        };
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable1);

        long multiThreadCurrentTime = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - multiThreadCurrentTime);
        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        System.out.println("Single Thread  " + sum1 + "made calculation in " + (System.currentTimeMillis() - multiThreadCurrentTime));

    }
}

