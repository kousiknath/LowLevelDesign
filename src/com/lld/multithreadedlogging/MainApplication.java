package com.lld.multithreadedlogging;

import com.lld.multithreadedlogging.service.QueueService;
import com.lld.multithreadedlogging.service.impl.QueueServiceImpl;
import com.lld.multithreadedlogging.worker.Consumer;
import com.lld.multithreadedlogging.worker.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApplication {
    private static void test1() {
        QueueService queueService = new QueueServiceImpl(100);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Producer(queueService));
        executorService.execute(new Producer(queueService));
        executorService.execute(new Consumer(queueService));
        executorService.shutdown();
    }

    public static void main(String[] args) {
        test1();
    }
}
