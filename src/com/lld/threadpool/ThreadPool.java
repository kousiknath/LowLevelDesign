package com.lld.threadpool;

public interface ThreadPool {
    void submit(Runnable runnable) throws RequestRejectionException;
    void shutDown();
    void shutDownNow();
}
