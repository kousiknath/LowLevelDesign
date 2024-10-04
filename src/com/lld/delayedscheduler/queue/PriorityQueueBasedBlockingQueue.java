package com.lld.delayedscheduler.queue;

import java.util.PriorityQueue;

public class PriorityQueueBasedBlockingQueue<E extends Comparable<E>> implements CustomQueue<E> {
    private PriorityQueue<E> queue;
//    private int capacity;

    public PriorityQueueBasedBlockingQueue() {
        this.queue = new PriorityQueue<>();
//        this.capacity = Integer.MAX_VALUE;
    }

    @Override
    public void add(E e) throws InterruptedException {
        if (e == null) {
            throw new IllegalArgumentException("Invalid element");
        }

        synchronized (this) {
//            while (this.size() == this.capacity) {
//                wait();
//            }

            this.queue.add(e);
            notifyAll();
        }
    }

    @Override
    public synchronized E peek() {
        // Non-bocking
        return this.queue.peek();
    }

    @Override
    public synchronized E poll() throws InterruptedException {
        while (this.size() == 0) {
            wait();
        }

        E element = this.queue.poll();
        notifyAll();
        return element;
    }

    @Override
    public synchronized int size() {
        return this.queue.size();
    }

    @Override
    public synchronized boolean isEmpty() {
        return this.queue.isEmpty();
    }
}
