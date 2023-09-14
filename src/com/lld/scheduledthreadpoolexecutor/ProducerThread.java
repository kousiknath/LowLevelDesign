package com.lld.scheduledthreadpoolexecutor;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ProducerThread implements Runnable {
    private ScheduledExecutor scheduledExecutor;
    public ProducerThread(ScheduledExecutor scheduledExecutor) {
        this.scheduledExecutor = scheduledExecutor;
    }

    @Override
    public void run() {
        Runnable r1 = () -> {
            System.out.println("Runnable with initial delay. Task: " + UUID.randomUUID().toString());
        };

        Runnable r2 = () -> {
            System.out.println("Runnable with initial delay and period. Task: " + UUID.randomUUID().toString());
        };

        while (true) {
            try {
                Task task1 = scheduledExecutor.executeWithDelay(r1, new Random().nextInt(5000), TimeUnit.MILLISECONDS);
                System.out.println("Produced delayed task: " + task1.getId());

                Task task2 = scheduledExecutor.executePeriodically(r2, new Random().nextInt(5000),
                        new Random().nextInt(10000), TimeUnit.MILLISECONDS);
                System.out.println("Produced repeating task: " + task2.getId());

                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
