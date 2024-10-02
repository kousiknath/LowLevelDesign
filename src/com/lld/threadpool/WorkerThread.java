package com.lld.threadpool;

public class WorkerThread extends Thread {
    private Runnable task;
    private ThreadState workerThreadState;

    public WorkerThread(ThreadState state) {
        this.workerThreadState = state;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public ThreadState getWorkerThreadState() {
        return workerThreadState;
    }

    @Override
    public void run() {
        try {
            this.task.run();
            this.workerThreadState.setState(com.lld.threadpool.State.TASK_COMPLETE);

        } catch (Exception ex) {
            if (ex.getCause().getClass() == InterruptedException.class) {
                Thread.currentThread().interrupt();
            }

            this.workerThreadState.setState(com.lld.threadpool.State.TASK_COMPLETE_WITH_EXCEPTION);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        return "WorkerThread{" +
                "workerThreadState=" + workerThreadState +
                '}';
    }
}
