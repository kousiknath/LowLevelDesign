package com.lld.multithreadedproducerconsumer;

public class ConsumerWithSharedState implements Runnable {
    private MyQueue<Integer> queue;
    private SharedVariable variable;

    public ConsumerWithSharedState(MyQueue<Integer> queue, SharedVariable variable) {
        this.queue = queue;
        this.variable = variable;
    }

    @Override
    public void run() {
        while (!variable.isStop()) { // This is required for graceful system shutdown
            try {
                Integer data = this.queue.poll();
                System.out.println("Thread = "
                        + Thread.currentThread().getName() + " consumed data = " + data);

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(ex);
            }
        }
    }
}
