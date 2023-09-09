package com.lld.connectionpool.connection;

import com.lld.connectionpool.constant.ConnectionState;

import java.util.UUID;

public class HttpConnection implements Connection {
    private final String id;
    ConnectionState state;
    long ttl;
    static final long DEFAULT_TIMEOUT_MS = 20000; // 20 sec default timeout

    public HttpConnection() {
        this.id = UUID.randomUUID().toString();
        this.state = ConnectionState.AVAILABLE;
        this.ttl = System.currentTimeMillis() + DEFAULT_TIMEOUT_MS;

        // Take connection to actual http client here or whatever is necessary
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

        HttpConnection other = (HttpConnection) obj;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return "HttpConnection - " + "Id: " + id + " TTL: " + ttl + " Current Time: " + System.currentTimeMillis();
    }
}
