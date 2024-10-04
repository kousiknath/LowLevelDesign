package com.lld.delayedscheduler;

import com.lld.delayedscheduler.scheduler.DelayedScheduler;
import com.lld.delayedscheduler.scheduler.PriorityQueueBasedDelayedScheduler;

public class Main {

    private static void test1() throws InterruptedException {
        DelayedScheduler delayedScheduler = new PriorityQueueBasedDelayedScheduler();
        delayedScheduler.schedule(new MyTask(), 2000);

        // Wait a random time before triggering stop()
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        delayedScheduler.stop();
    }

    public static void main(String[] args) throws InterruptedException {
        test1();
    }
}
