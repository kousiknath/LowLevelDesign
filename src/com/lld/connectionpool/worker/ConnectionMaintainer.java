package com.lld.connectionpool.worker;

import com.lld.connectionpool.pool.ConnectionPool;

public class ConnectionMaintainer implements Runnable {

    ConnectionPool pool;

    public ConnectionMaintainer(ConnectionPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true) {
            pool.expireConnections();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
