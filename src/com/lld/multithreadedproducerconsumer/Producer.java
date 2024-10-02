package com.lld.multithreadedproducerconsumer;

public class Producer implements Runnable {
    private MyQueue<Integer> que;
    public Producer(MyQueue<Integer> que) {
        this.que = que;
    }

    @Override
    public void run() {
        Integer data = 0;

        while (true) {
            try {
                this.que.add(data);
                System.out.println(Thread.currentThread().getName() + " produced data = " + data);
                data++;
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                // When a thread is interrupted, the `interrupt` status of the thread is set to true.
                // When the `InterruptedException` is caught in a `catch` block, JVM clears
                // the status and now up to the developer how to handle this interruption.
                // We are resetting the `interrupt` status of the thread to true.
                // We are also throwing a `RuntimeException` exception so that once interrupted,
                // the thread can stop its execution.
                // This step is necessary as when ExecutorService.shutDownNow() is called,
                // the executor service tries to stop all the threads by interrupting them.
                // There is no guarantee that the threads would stop unless and until the threads
                // exit once interrupted.
                // Hence, if a thread decided to continue its operation even after being interrupted.
                // the executor service won't be able to stop it.
                // For a graceful shutdown, all the threads should raise `RunTimeException` when
                // they are interrupted.
                // Remember, if the thread is doing some IO intensive operation like waiting
                // on a socket or reading from / writing to a file, it won't be immediately
                // interrupted.
                // Re-setting the interrupt status also makes sure that the caller of this thread
                // who is interested in this thread's interruption status knows that the thread
                // is interrupted.
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
