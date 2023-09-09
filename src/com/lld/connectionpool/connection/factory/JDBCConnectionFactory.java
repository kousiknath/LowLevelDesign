package com.lld.connectionpool.connection.factory;

import com.lld.connectionpool.connection.Connection;
import com.lld.connectionpool.connection.JDBCConnection;

public class JDBCConnectionFactory implements ConnectionFactory {
    @Override
    public Connection getConnection() {
        return new JDBCConnection();
    }
}
