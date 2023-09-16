package com.lld.scheduledthreadpoolexecutor.executor;

import com.lld.scheduledthreadpoolexecutor.worker.PoolExecutorThread;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutor implements ScheduledExecutor {
    private int numExecutors;

    private SharedState sharedState;

    public ScheduledThreadPoolExecutor(int numExecutors, ExecutorService executorService) {
        this.numExecutors = numExecutors;
        this.sharedState = new SharedState(new PriorityQueue<>(Comparator.comparingLong(Task::getExecutionTime)));

        setUpPoolThreads(executorService);
    }

    private void setUpPoolThreads(ExecutorService executorService) {
        for (int i = 0; i < numExecutors; i++) {
            executorService.execute(new PoolExecutorThread(this.sharedState, executorService));
        }
    }

    @Override
    public Task executeWithDelay(Runnable runnable, int initialDelay, TimeUnit timeUnit) throws InterruptedException {
        if (runnable == null || initialDelay < 0 || timeUnit == null) {
            throw new IllegalArgumentException("Invalid parameters ...");
        }

        Task task = new Task(runnable, initialDelay, 0, timeUnit);
        this.sharedState.enqueue(task);
        return task;
    }

    @Override
    public Task executePeriodically(Runnable runnable, int initialDelay, int period, TimeUnit timeUnit) throws InterruptedException {
        if (runnable == null || initialDelay < 0 || period < 0 || timeUnit == null) {
            throw new IllegalArgumentException("Invalid parameters ...");
        }

        Task task = new Task(runnable, initialDelay, period, timeUnit);
        this.sharedState.enqueue(task);
        return task;
    }
}
