package com.lld.multithreadedproducerconsumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueueImpl2<E> implements MyQueue<E> {
    private LinkedList<E> container;
    private ReentrantLock lock;
    private Condition condition;
    private int capacity;

    public MyQueueImpl2(int capacity) {
        this.capacity = capacity;
        this.container = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
    }

    @Override
    public void add(E element) throws InterruptedException {
        if (element == null) {
            throw new IllegalArgumentException("null element not allowed");
        }

        this.lock.lock();
        try {
            while (this.size() == this.capacity) {
                this.condition.await();
            }

            this.container.addLast(element);
            this.condition.signalAll();

        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        this.lock.lock();
        try {
            while (this.isEmpty()) {
                this.condition.await();
            }

            E result = this.container.pollFirst();
            this.condition.signalAll();

            return result;

        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public int size() {
        this.lock.lock();

        try {
            return this.container.size();
        } finally {
            this.lock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        this.lock.lock();

        try {
            return this.container.isEmpty();
        } finally {
            this.lock.unlock();
        }
    }
}
