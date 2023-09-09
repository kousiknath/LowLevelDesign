package com.lld.connectionpool.pool;


import com.lld.connectionpool.connection.Connection;

public interface ConnectionPool {
    Connection getConnection();
    boolean releaseConnection(Connection connection);
    void expireConnections();
}
