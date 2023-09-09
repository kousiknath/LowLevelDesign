package com.lld.myblockingqueue;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {
    private final E[] items;
    private int putIndex;
    private int takeIndex;
    private final int capacity;
    private final AtomicInteger size;
    private static final int DEFAULT_MAX_CAPACITY = 1;
    private final ReentrantLock lock;
    private final Condition canPut;
    private final Condition canTake;

    public MyBlockingQueue(int capacity) {
        this.capacity = Math.max(capacity, DEFAULT_MAX_CAPACITY);
        this.items = (E[]) new Object[capacity];
        this.size = new AtomicInteger(0);
        this.lock = new ReentrantLock();
        this.canPut = this.lock.newCondition();
        this.canTake = this.lock.newCondition();
    }

    public void put(E item) throws InterruptedException {
        Objects.requireNonNull(item);

        try {
            lock.lock();

            while (size.get() == capacity) {
                canPut.await();
            }

            items[putIndex] = item;
            putIndex = (putIndex + 1) % capacity;
            size.getAndIncrement();

            canTake.signal();
        } finally {
            lock.unlock();
        }
    }

    public E get() throws InterruptedException {
        try {
            lock.lock();

            while (size.get() == 0) {
                canTake.await();
            }

            E item = items[takeIndex];
            takeIndex = (takeIndex + 1) % capacity;
            size.getAndDecrement();
            canPut.signal();

            return item;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return this.size.get();
    }

    private static void test1() {
        // Add 10 items consecutively to the queue, it should not add more item beyond 10.
        // While adding 11-th item, the producer should block
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(10);
        Producer producer = new Producer(myBlockingQueue);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(producer);
        executorService.shutdown();
    }

    private static void test2() {
        // Since, we are not adding any element to the queue, the consumer would block forever
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(10);
        Consumer consumer = new Consumer(myBlockingQueue);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(consumer);
        executorService.shutdown();
    }

    private static void test3() {
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(10);
        Producer producer = new Producer(myBlockingQueue);
        Consumer consumer = new Consumer(myBlockingQueue);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(producer);
        executorService.execute(consumer);

        executorService.shutdown();
    }

    public static void main(String[] args) {
        test3();
    }
}
