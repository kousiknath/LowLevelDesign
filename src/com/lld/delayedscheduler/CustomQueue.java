package com.lld.delayedscheduler;

public interface CustomQueue<E extends Comparable<E>> {
    void add(E e) throws InterruptedException;
    E peek();
    E poll() throws InterruptedException;
    int size();
    boolean isEmpty();
}
