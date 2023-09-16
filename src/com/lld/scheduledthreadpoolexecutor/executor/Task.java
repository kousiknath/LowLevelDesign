package com.lld.scheduledthreadpoolexecutor.executor;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Task {
    private String  id;
    private Runnable runnable;
    private int initialDelay;
    private int period;
    private TimeUnit timeUnit;
    private long executionTime;

    public Task(Runnable runnable, int delay, int period, TimeUnit timeUnit) {
        this.id = UUID.randomUUID().toString();
        this.runnable = runnable;
        this.initialDelay = delay;
        this.period = period;
        this.timeUnit = timeUnit;
        this.executionTime = System.nanoTime() + timeUnit.toNanos(delay);
    }

    public String getId() {
        return this.id;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public int getInitialDelay() {
        return initialDelay;
    }

    public int getPeriod() {
        return period;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}
