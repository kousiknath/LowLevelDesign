package com.lld.scheduledthreadpoolexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApplication {

    private static void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        ScheduledExecutor scheduledExecutor = new ScheduledThreadPoolExecutor(5, executorService);

        ProducerThread producerThread = new ProducerThread(scheduledExecutor);
        executorService.execute(producerThread);
        executorService.shutdown();
    }

    public static void main(String[] args) {
        test1();
    }
}
