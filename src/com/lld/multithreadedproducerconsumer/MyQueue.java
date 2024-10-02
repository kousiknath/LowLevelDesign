package com.lld.multithreadedproducerconsumer;

public interface MyQueue<E> {
    void add(E element) throws InterruptedException;
    E poll() throws InterruptedException;
    int size();
    boolean isEmpty();
}
