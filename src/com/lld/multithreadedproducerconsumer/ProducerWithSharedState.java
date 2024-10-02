package com.lld.multithreadedproducerconsumer;

public class ProducerWithSharedState implements Runnable {
    private MyQueue<Integer> queue;
    private SharedVariable variable;

    public ProducerWithSharedState(MyQueue<Integer> queue, SharedVariable variable) {
        this.queue = queue;
        this.variable = variable;
    }

    @Override
    public void run() {
        Integer data = 0;

        while (!variable.isStop()) { // This is required for graceful system shutdown
            try {
                this.queue.add(data);
                System.out.println(Thread.currentThread().getName() + " produced data = " + data);
                data++;
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                // Even though we are using the shared variable,
                // we still need to handle interruption as the thread might
                // be busy doing some processing before it checks the variable
                // state in the next loop. Hence, with interruption, it will get
                // a chance to be interrupted and throw an exception.
                // Note: It depends on the interrupt mechanism also whether
                // a thread would be interrupted while it's busy.
                throw new RuntimeException(e);

            }
        }
    }
}
