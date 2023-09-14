package com.lld.scheduledthreadpoolexecutor;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

class SharedState {
    private final PriorityQueue<Task> priorityQueue;
    private static final int MAX_TASKS = Integer.MAX_VALUE; // The maximum number of tasks that the priority queue can have
    private final AtomicInteger size;
    private final ReentrantLock lock;
    private final Condition canAdd;
    private final Condition canRemove;

    public SharedState(PriorityQueue<Task> priorityQueue) {
        this.priorityQueue = priorityQueue;
        this.size = new AtomicInteger(0);
        this.lock = new ReentrantLock();
        this.canAdd = this.lock.newCondition();
        this.canRemove = this.lock.newCondition();
    }

    public PriorityQueue<Task> getPriorityQueue() {
        return this.priorityQueue;
    }

    public void enqueue(Task task) throws InterruptedException {
        try {
            this.lock.lock();

            while (this.size.get() == MAX_TASKS) {
                this.canAdd.await();
            }

            this.priorityQueue.add(task);
            this.size.incrementAndGet();

            this.canRemove.signalAll();
        } finally {
            this.lock.unlock();
        }
    }

    public Task dequeue(Predicate<Task> predicate) throws InterruptedException {
        try {
            this.lock.lock();

            while (this.size.get() == 0) {
                this.canRemove.await();
            }

            Task task = this.priorityQueue.peek();

            if (predicate.test(task)) {
                task = this.priorityQueue.poll();
                this.size.decrementAndGet();
                this.canAdd.signalAll();
                return task;
            }

            return null;
        } finally {
            this.lock.unlock();
        }
    }

}
