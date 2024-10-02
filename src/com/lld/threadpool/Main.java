package com.lld.threadpool;

public class Main {

    static class MyTask implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("My thread printing = " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    // If we don't throw an exception here upon interruption,
                    // the WorkerThread which runs this `Runnable` won't be
                    // interrupted as well, thus, even after `shutDownNow()`
                    // is requested, the `WorkerThread` will keep on executing.
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private static void test1() throws RequestRejectionException {
        FixedThreadPool threadPool = ThreadPoolFactory.getFixedThreadPool(10);
        threadPool.submit(new MyTask());
        threadPool.submit(new MyTask());
        threadPool.submit(new MyTask());

//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//
//        threadPool.shutDownNow();
    }

    public static void main(String[] args) throws RequestRejectionException {
        test1();
    }
}
