package com.lld.delayedscheduler;


import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PriorityQueueBasedDelayedScheduler implements DelayedScheduler {
    private CustomQueue<Delayed> queue;
    private ExecutorService executorService;
    private SharedVariable sharedVariable;

    public PriorityQueueBasedDelayedScheduler() {
        this.queue = new PriorityQueueBasedBlockingQueue<>();
        this.executorService = Executors.newFixedThreadPool(10);
        this.sharedVariable = new SharedVariable();
        this.executorService.submit(new TaskRunner2(this.queue, this.executorService, this.sharedVariable));
    }

    @Override
    public void schedule(Runnable task, long delayMillis) throws InterruptedException {
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
            if (!this.executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                this.executorService.shutdownNow();

                if (!this.executorService.awaitTermination(30, TimeUnit.SECONDS)) {
                    System.out.println("Not stopped");
                }
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.out.println("Exception in stopping the thread");
        }
    }

    @Override
    public synchronized int pendingTasks() {
        return this.queue.size();
    }
}

class TaskRunner2 implements Runnable {
    private CustomQueue<Delayed> queue;
    private ExecutorService executorService;
    private SharedVariable sharedVariable;

    public TaskRunner2(CustomQueue<Delayed> queue, ExecutorService executorService, SharedVariable sharedVariable) {
        this.queue = queue;
        this.executorService = executorService;
        this.sharedVariable = sharedVariable;
    }

    @Override
    public void run() {
        while (!this.sharedVariable.isStop()) {
            DelayedTask delayedTask;

            try {
                delayedTask = (DelayedTask) this.queue.poll();
                if (delayedTask != null) {
                    this.executorService.submit(delayedTask.getTask());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
