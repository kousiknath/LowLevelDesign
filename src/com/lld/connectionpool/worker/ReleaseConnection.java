package com.lld.connectionpool.worker;

import com.lld.connectionpool.connection.Connection;
import com.lld.connectionpool.pool.ConnectionPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ReleaseConnection implements Runnable {
    private ConnectionPool connectionPool;
    private BlockingQueue<Connection> queue;

    public ReleaseConnection(ConnectionPool connectionPool, BlockingQueue<Connection> queue) {
        this.connectionPool = connectionPool;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Connection connection;
            try {
                connection = queue.poll(200, TimeUnit.MILLISECONDS);

                if (connection != null) {
                    System.out.println("Releasing the connection: " + connectionPool.releaseConnection(connection));
                }

                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
