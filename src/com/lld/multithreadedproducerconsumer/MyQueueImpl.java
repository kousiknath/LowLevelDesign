package com.lld.multithreadedproducerconsumer;

import java.util.LinkedList;

/*
    This is a blocking queue implementation.
    The producer or consumer waits till they are able to
    produce or consume from the queue
 */

public class MyQueueImpl<E> implements MyQueue<E> {
    private LinkedList<E> container;
    private int capacity;

    public MyQueueImpl(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity");
        }

        this.capacity = capacity;
        this.container = new LinkedList<>();
    }

    @Override
    public void add(E element) throws InterruptedException {
        if (element == null) {
            throw new IllegalArgumentException("Invalid element");
        }

        synchronized (this) {
            while (this.size() == this.capacity) {
                wait();
            }

            this.container.addLast(element);

            /*
                If notifyAll() is called without holding the monitor (outside
                the synchronized block), it will throw an IllegalMonitorStateException,
                because the thread doesn’t own the necessary lock to safely modify the
                state of waiting threads.
             */
            notifyAll();
        }
    }

    @Override
    public E poll() throws InterruptedException {
        synchronized (this) {
            while (isEmpty()) {
                wait();
            }

            E data = this.container.pollFirst();
            notifyAll();
            return data;
        }
    }

    @Override
    public synchronized int size() {
        return this.container.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return this.container.isEmpty();
    }
}
