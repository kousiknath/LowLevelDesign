package com.lld.connectionpool.connection;

import com.lld.connectionpool.constant.ConnectionState;

public interface Connection {
    String getId();

    long getTtl();

    ConnectionState getState();

    void setState(ConnectionState state);

    void close();
}
