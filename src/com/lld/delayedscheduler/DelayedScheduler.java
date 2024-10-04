package com.lld.delayedscheduler;

public interface DelayedScheduler {
    void schedule(Runnable task, long delayMillis) throws InterruptedException;
    void stop();
    int pendingTasks();
}
