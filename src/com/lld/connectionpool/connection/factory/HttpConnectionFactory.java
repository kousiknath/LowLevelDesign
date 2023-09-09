package com.lld.connectionpool.connection.factory;

import com.lld.connectionpool.connection.Connection;
import com.lld.connectionpool.connection.HttpConnection;

public class HttpConnectionFactory implements ConnectionFactory {
    @Override
    public Connection getConnection() {
        return new HttpConnection();
    }
}
