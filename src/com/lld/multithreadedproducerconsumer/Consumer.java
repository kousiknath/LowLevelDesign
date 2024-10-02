package com.lld.multithreadedproducerconsumer;

public class Consumer implements Runnable {
    private MyQueue<Integer> que;

    public Consumer(MyQueue<Integer> que) {
        this.que = que;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer data = this.que.poll();
                System.out.println("Thread = "
                        + Thread.currentThread().getName() + " consumed data = " + data);
                Thread.sleep(500);

            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(ex);
            }
        }
    }
}
