package com.lld.delayedscheduler.queue;

import java.util.PriorityQueue;

public class PriorityQueueBasedNonBlockingQueue<E extends Comparable<E>> implements CustomQueue<E> {
    private PriorityQueue<E> queue;

    public PriorityQueueBasedNonBlockingQueue() {
        this.queue = new PriorityQueue<>();
    }

    @Override
    public synchronized void add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Invalid element");
        }

        this.queue.add(e);
    }

    @Override
    public synchronized E peek() {
        if (isEmpty()) {
            return null;
        }

        return this.queue.peek();
    }

    @Override
    public synchronized E poll() {
        if (isEmpty()) {
            return null;
        }

        return this.queue.poll();
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
