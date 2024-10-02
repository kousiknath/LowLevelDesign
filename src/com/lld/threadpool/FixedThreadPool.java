package com.lld.threadpool;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class FixedThreadPool implements ThreadPool {
    private WorkerThread[] workers;
    private BlockingQueue<Runnable> queue;
    private Map<WorkerThread, ThreadState> threadStates;
    private Thread dispatcher;
    private Thread houseKeeper;
    private boolean shutDownRequested;

    public FixedThreadPool(int no) {
        if (no <= 0) {
            throw new IllegalArgumentException("Invalid thread count");
        }

        this.workers = new WorkerThread[no];
        this.queue = new LinkedBlockingQueue<>();
        initializeWorkerThreads();

        initializeBackgroundThreads();
    }

    private void initializeWorkerThreads() {
        this.threadStates = new HashMap<>();

        for (int index = 0; index < this.workers.length; index++) {
            ThreadState state = new ThreadState();
            this.workers[index] = new WorkerThread(state);
            this.threadStates.put(this.workers[index], state);
        }
    }

    private void initializeBackgroundThreads() {
        this.dispatcher = new Thread(new FixedThreadPoolDispatcher(this.queue, this));
        this.houseKeeper = new Thread(new HouseKeeper(this.threadStates, this));

        dispatcher.start();
        houseKeeper.start();
    }

    @Override
    public void submit(Runnable runnable) throws RequestRejectionException {
        if (runnable == null) {
            throw new IllegalArgumentException("Invalid task");
        }

        if (this.shutDownRequested) {
            throw new RequestRejectionException("Can't take any more request");
        }

        this.queue.add(runnable);
    }

    @Override
    public void shutDown() {
        this.shutDownRequested = true;
    }

    @Override
    public void shutDownNow() {
        this.dispatcher.interrupt();
        this.houseKeeper.interrupt();

        for (WorkerThread thread: this.workers) {
            thread.interrupt();
        }
    }

    synchronized WorkerThread getAvailableThread() {
        for (WorkerThread thread: this.threadStates.keySet()) {
            ThreadState state = this.threadStates.get(thread);

            if (state.getState() == State.AVAILABLE) {
                state.setState(State.ASSIGNED);
                return thread;
            }
        }

        return null;
    }

    synchronized boolean isThreadAvailable() {
        for (WorkerThread thread: this.threadStates.keySet()) {
            ThreadState state = this.threadStates.get(thread);

            if (state.getState() == State.AVAILABLE) {
                return true;
            }

        }

        return false;
    }

    synchronized void recycleThread(WorkerThread thread) {
        for (int index = 0; index < this.workers.length; index++) {
            if (this.workers[index] == thread) {
                ThreadState state = new ThreadState();
                this.workers[index] = new WorkerThread(state);
                this.threadStates.remove(thread);
                this.threadStates.put(this.workers[index], state);
            }
        }
    }
}

class FixedThreadPoolDispatcher implements Runnable {
    private BlockingQueue<Runnable> queue;
    private FixedThreadPool threadPool;

    public FixedThreadPoolDispatcher(BlockingQueue<Runnable> queue, FixedThreadPool threadPool) {
        this.queue = queue;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        while (true) {
            if (threadPool.isThreadAvailable() && !this.queue.isEmpty()) {
                WorkerThread workerThread = threadPool.getAvailableThread();
                if (workerThread != null) {
                    try {
                        Runnable task = this.queue.poll(100, TimeUnit.MILLISECONDS);
                        if (task != null) {
                            workerThread.setTask(task);
                            workerThread.getWorkerThreadState().setState(State.BUSY);
                            workerThread.start();
                        }
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Dispatcher interrupted!");
                    }
                }
            }
        }
    }
}

class HouseKeeper implements Runnable {
    private Map<WorkerThread, ThreadState> threadStates;
    private FixedThreadPool threadPool;

    public HouseKeeper(Map<WorkerThread, ThreadState> threadStates, FixedThreadPool threadPool) {
        this.threadStates = threadStates;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        // Check thread states periodically
        // Update internal statistics
        // Reset thread status if necessary
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                throw new RuntimeException("Interrupted. Closing down!");
            }

            synchronized (threadPool) {
                Iterator<Map.Entry<WorkerThread, ThreadState>> iterator = threadStates.entrySet().iterator();
                while (iterator.hasNext()) {
                    // TODO: Fix java.util.ConcurrentModificationException here
                    Map.Entry<WorkerThread, ThreadState> next = iterator.next();
                    State currentState = next.getKey().getWorkerThreadState().getState();
                    if (currentState == State.TASK_COMPLETE || currentState == State.TASK_COMPLETE_WITH_EXCEPTION) {
                        // reset them to available
                        this.threadPool.recycleThread(next.getKey());
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(ex);
            }
        }
    }
}
