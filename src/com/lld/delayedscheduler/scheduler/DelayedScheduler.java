package com.lld.delayedscheduler.scheduler;

public interface DelayedScheduler {
    void schedule(Runnable task, long delayMillis) throws InterruptedException;
    void stop();
    int pendingTasks();
}
