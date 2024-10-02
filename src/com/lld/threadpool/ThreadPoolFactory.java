package com.lld.threadpool;

public class ThreadPoolFactory {
    static FixedThreadPool getFixedThreadPool(int noOfThreads) {
        if (noOfThreads <= 0) {
            throw new IllegalArgumentException("Invalid number of threads");
        }

        return new FixedThreadPool(noOfThreads);
    }
}
