package com.lld.multithreadedproducerconsumer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class BoundedProducer implements Runnable {
    private MyQueue<Integer> que;
    private int bound;
    private CountDownLatch latch;
    public BoundedProducer(MyQueue<Integer> que, int bound, CountDownLatch latch) {
        this.que = que;
        this.bound = bound;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < this.bound; i++) {
            try {
                Integer data = 31 * new Random().nextInt(1000);
                System.out.println(Thread.currentThread().getName() + " producing = " + data);
                this.que.add(data);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

        this.latch.countDown();
    }
}
