package com.lld.multithreadedproducerconsumer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static void test1() {
        System.out.println("-----------------------test 1-------------------------");

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyQueue<Integer> queue = new MyQueueImpl<>(10);

        executorService.submit(new Producer(queue));
        executorService.submit(new Consumer(queue));
        executorService.submit(new Consumer(queue));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        /*
            `executorService.shutdown()` does not forcefully shut down the threads.
            It just stops accepting the next threads.
            Whatever threads are still doing work, they are still executed unless
            they finish.

            Note: If a thread runs in an infinite loop or with some condition, they
            won't get shutdown when `executorService.shutdown()` is called. They can
            be shut down by calling `executorService.shutdownNow()` once they throw
            `RunTimeException` and exit upon interruption.
         */
        System.out.println("Shutting down...");
        executorService.shutdown();

        // Wait for some time for termination
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                /*
                    `executorService.shutdownNow()` will try to shut down all the
                    running threads forcefully by interrupting them. So, if a thread
                    does not throw a `RunTimeException` and exit once interrupted,
                    the shutdown won't be graceful and as a result, that thread will
                    keep on running.

                    For more details, please check the `Producer` class documentation.
                 */
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            System.out.println("Error in shutting down!");
        }
    }

    public static void test2() {
        System.out.println("-----------------------test 2-------------------------");

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyQueue<Integer> queue = new MyQueueImpl<>(10);
        SharedVariable sharedVariable = new SharedVariable();

        executorService.submit(new ProducerWithSharedState(queue, sharedVariable));
        executorService.submit(new ConsumerWithSharedState(queue, sharedVariable));
        executorService.submit(new ConsumerWithSharedState(queue, sharedVariable));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("Setting the shared variable to true. " +
                "Threads will stop execution now");
        sharedVariable.setStop(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        /*
            Since, no threads are executing at this point, `executorService.shutdownNow()`
            shuts down the executor service completely, even as the threads are not
            throwing `RunTimeException` anymore upon interruption.
         */
        executorService.shutdownNow();
    }

    public static void test3() {
        System.out.println("-----------------------test 2-------------------------");

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyQueue<Integer> queue = new MyQueueImpl2<>(10);
        SharedVariable sharedVariable = new SharedVariable();

        executorService.submit(new ProducerWithSharedState(queue, sharedVariable));
        executorService.submit(new ConsumerWithSharedState(queue, sharedVariable));
        executorService.submit(new ConsumerWithSharedState(queue, sharedVariable));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("Setting the shared variable to true. " +
                "Threads will stop execution now");
        sharedVariable.setStop(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        /*
            Since, no threads are executing at this point, `executorService.shutdownNow()`
            shuts down the executor service completely, even as the threads are not
            throwing `RunTimeException` anymore upon interruption.
         */
        executorService.shutdownNow();
    }

    private static void test4() {
        MyQueue<Integer> queue = new MyQueueImpl<>(10);
        CountDownLatch countDownLatch = new CountDownLatch(2);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new BoundedProducer(queue, 20, countDownLatch));
        executorService.submit(new BoundedProducer(queue, 10, countDownLatch));
        executorService.submit(new Consumer(queue));
        executorService.submit(new Consumer(queue));

        // Shutdown the executor when all the producers and consumers are done
        try {
            countDownLatch.await();
            while (!queue.isEmpty()) {
                System.out.println("Current Queue Size = " + queue.size());
                Thread.sleep(2000);
            }

            System.out.println("Current Queue Size = " + queue.size());
            executorService.shutdownNow();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}
