package com.lld.myblockingqueue;

public class Consumer implements Runnable {
    private MyBlockingQueue<Integer> myBlockingQueue;

    public Consumer(MyBlockingQueue<Integer> queue) {
        this.myBlockingQueue = queue;
    }

    public Consumer() {
        this.myBlockingQueue = new MyBlockingQueue<>(10);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Polling ...");
            try {
                Integer data = myBlockingQueue.get();
                System.out.println("Consumed data = " + data + ", queue size = " + myBlockingQueue.size());

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
