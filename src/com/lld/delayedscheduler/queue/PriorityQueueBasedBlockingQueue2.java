package com.lld.delayedscheduler.queue;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PriorityQueueBasedBlockingQueue2<E extends Comparable<E>> implements CustomQueue<E> {
    private PriorityQueue<E> queue;
    private Lock lock;
    private Condition condition;

    public PriorityQueueBasedBlockingQueue2() {
        this.queue = new PriorityQueue<>();
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
    }

    @Override
    public void add(E e) throws InterruptedException {
        if (e == null) {
            throw new IllegalArgumentException("Invalid element");
        }

        this.lock.lock();

        try {
            this.queue.add(e);
            this.condition.signalAll();
        } finally {
            this.lock.unlock();
        }

    }

    @Override
    public E peek() {
        this.lock.lock();
        try {
            return this.queue.peek();
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        this.lock.lock();
        while (this.size() == 0) {
            this.condition.await();
        }

        try {
            return this.queue.poll();
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public int size() {
        this.lock.lock();
        try {
            return this.queue.size();
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        this.lock.lock();
        try {
            return this.queue.isEmpty();
        } finally {
            this.lock.unlock();
        }
    }
}
