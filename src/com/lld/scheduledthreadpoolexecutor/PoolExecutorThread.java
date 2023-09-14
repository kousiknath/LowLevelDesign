package com.lld.scheduledthreadpoolexecutor;

import java.util.concurrent.ExecutorService;
import java.util.function.Predicate;

public class PoolExecutorThread implements Runnable {
    private SharedState sharedState;
    private ExecutorService executorService;

    public PoolExecutorThread(SharedState sharedState, ExecutorService executorService) {
        this.sharedState = sharedState;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        while (true) {
            Predicate<Task> predicate = task -> System.nanoTime() >= task.getExecutionTime();
            try {
                Task task = this.sharedState.dequeue(predicate);
                if (task != null) {
                    System.out.println("Executing task: " + task.getId() + " at time: " + System.nanoTime()
                            + ", actual execution time: " + task.getExecutionTime());
//                    executorService.execute(task.getRunnable()); // TODO: Check why these tasks are getting rejected by the executor service

                    if (task.getPeriod() > 0) {
                        task.setExecutionTime(task.getExecutionTime() + task.getTimeUnit().toNanos(task.getPeriod()));
                        this.sharedState.enqueue(task);
                        System.out.println("Recurring task found. Enqueued task id: " + task.getId());
                    }
                }

                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
