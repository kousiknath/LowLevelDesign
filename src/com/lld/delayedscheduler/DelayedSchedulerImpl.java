package com.lld.delayedscheduler;

import java.util.concurrent.*;

public class DelayedSchedulerImpl implements DelayedScheduler {
    private DelayQueue<Delayed> queue;
    private ExecutorService executorService;
    private SharedVariable sharedVariable;

    public DelayedSchedulerImpl() {
        this.queue = new DelayQueue<>();
        this.executorService = Executors.newFixedThreadPool(10);
        this.sharedVariable = new SharedVariable();
        this.executorService.submit(new TaskRunner(this.queue, this.executorService, this.sharedVariable));
    }

    @Override
    public void schedule(Runnable task, long delayMillis) {
        DelayedTask delayedTask = new DelayedTask(task, delayMillis);
        this.queue.add(delayedTask);
    }

    @Override
    public void stop() {
        // `sharedVariable` = false will make sure our `TaskRunner` stops execution
        // and we stop scheduling any more user threads.
        this.sharedVariable.setStop(true);
        // Call `executorService.shutdown()` to stop accepting new tasks
        // Once user submitted tasks stop executing, the system will automatically
        // shut down
        this.executorService.shutdown();

        try {
            // If the system has not gracefully shutdown yet, call `executorService.shutdownNow()`
            // to forcefully interrupt all the current threads.
            // If currently running threads throw `RunTimeException` once interrupted,
            // the system will shut down completely else it will still keep on running
            if (!this.executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                this.executorService.shutdownNow();

                if (!this.executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                    System.out.println("Not stopped");
                }
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println("Exception in stopping the thread");
        }
    }

    @Override
    public int pendingTasks() {
        return this.queue.size();
    }
}

class TaskRunner implements Runnable {
    private DelayQueue<Delayed> queue;
    private ExecutorService executorService;
    private SharedVariable sharedVariable;

    public TaskRunner(DelayQueue<Delayed> queue, ExecutorService executorService, SharedVariable sharedVariable) {
        this.queue = queue;
        this.executorService = executorService;
        this.sharedVariable = sharedVariable;
    }

    @Override
    public void run() {
        while (!this.sharedVariable.isStop()) {
            DelayedTask task = (DelayedTask) this.queue.poll();

            if (task != null) {
                this.executorService.submit(task.getTask());
            }
        }
    }
}
