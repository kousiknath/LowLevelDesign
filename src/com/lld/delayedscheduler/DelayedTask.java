package com.lld.delayedscheduler;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Delayed {
    private final Runnable task;
    private final long startTime;


    public DelayedTask(Runnable task, long delay) {
        if (task == null || delay < 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        this.task = task;
        this.startTime = System.currentTimeMillis() + delay;
    }

    public Runnable getTask() {
        return task;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = System.currentTimeMillis() - startTime;
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.getDelay(TimeUnit.MILLISECONDS),
                o.getDelay(TimeUnit.MILLISECONDS));
    }
}
