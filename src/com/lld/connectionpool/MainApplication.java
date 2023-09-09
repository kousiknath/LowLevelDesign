package com.lld.connectionpool;

import com.lld.connectionpool.connection.Connection;
import com.lld.connectionpool.connection.factory.HttpConnectionFactory;
import com.lld.connectionpool.worker.ConnectionMaintainer;
import com.lld.connectionpool.pool.ConnectionPool;
import com.lld.connectionpool.pool.ConnectionPoolImpl;
import com.lld.connectionpool.worker.GetConnection;
import com.lld.connectionpool.worker.ReleaseConnection;

import java.util.concurrent.*;

public class MainApplication {
    private static void test1() {
        BlockingQueue<Connection> queue = new LinkedBlockingQueue<>();
        ConnectionPool connectionPool = new ConnectionPoolImpl(10, new HttpConnectionFactory());
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new GetConnection(connectionPool, queue));
        executorService.execute(new ReleaseConnection(connectionPool, queue));
        executorService.execute(new ConnectionMaintainer(connectionPool));

        executorService.shutdown();
    }

    public static void main(String[] args) {
        test1();
    }
}


