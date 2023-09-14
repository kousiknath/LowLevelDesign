package com.lld.scheduledthreadpoolexecutor;

import java.util.concurrent.TimeUnit;

public interface ScheduledExecutor {
    Task executeWithDelay(Runnable runnable, int initialDelay, TimeUnit timeUnit) throws InterruptedException;
    Task executePeriodically(Runnable runnable, int initialDelay, int period, TimeUnit timeUnit) throws InterruptedException;
}
