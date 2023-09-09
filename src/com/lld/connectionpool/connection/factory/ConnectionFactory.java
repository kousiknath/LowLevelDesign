package com.lld.connectionpool.connection.factory;

import com.lld.connectionpool.connection.Connection;

public interface ConnectionFactory {
    Connection getConnection();
}
