package com.lld.myblockingqueue;

public class Producer implements Runnable {
    private MyBlockingQueue<Integer> myBlockingQueue;

    public Producer(MyBlockingQueue<Integer> queue) {
        this.myBlockingQueue = queue;
    }

    @Override
    public void run() {
        int data  = 1;
        while (true) {
            System.out.println("Producing data = " + data + ", queue size = " + myBlockingQueue.size());

            try {
                myBlockingQueue.put(data);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data++;
        }
    }
}
