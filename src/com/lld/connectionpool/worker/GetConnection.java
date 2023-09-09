package com.lld.connectionpool.worker;

import com.lld.connectionpool.connection.Connection;
import com.lld.connectionpool.pool.ConnectionPool;

import java.util.concurrent.BlockingQueue;

public class GetConnection implements Runnable {
    private ConnectionPool connectionPool;
    private BlockingQueue<Connection> queue;

    public GetConnection(ConnectionPool connectionPool, BlockingQueue<Connection> queue) {
        this.connectionPool = connectionPool;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Connection connection = connectionPool.getConnection();
            System.out.println("Getting a connection ... " + connection);
            this.queue.add(connection);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
