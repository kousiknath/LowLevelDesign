package com.lld.connectionpool.connection;

import com.lld.connectionpool.constant.ConnectionState;

import java.util.UUID;

public class JDBCConnection implements Connection {

    private final String id;
    ConnectionState state;
    long ttl;
    static final int DEFAULT_TIMEOUT_MS = 50000; // 20 sec default timeout

    public JDBCConnection() {
        // Establish connection with JDBC driver here
        this.id = UUID.randomUUID().toString();
        this.state = ConnectionState.AVAILABLE;
        this.ttl = System.currentTimeMillis() + DEFAULT_TIMEOUT_MS;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public long getTtl() {
        return ttl;
    }

    @Override
    public ConnectionState getState() {
        return state;
    }

    @Override
    public void setState(ConnectionState state) {
        this.state = state;
    }

    @Override
    public void close() {

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        JDBCConnection other = (JDBCConnection) obj;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "JDBCConnection - " + "Id: " + id + " TTL: " + ttl + " Current Time: " + System.currentTimeMillis();
    }
}
