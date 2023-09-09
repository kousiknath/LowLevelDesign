package com.lld.connectionpool.pool;

import com.lld.connectionpool.connection.Connection;
import com.lld.connectionpool.connection.factory.ConnectionFactory;
import com.lld.connectionpool.constant.ConnectionState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
    TODO: This implementation has some bug due to which probably a deadlock is hit. Fix it.
 */
public class ConnectionPoolImpl implements ConnectionPool {
    private final int capacity;
    private final List<Connection> available; // unbounded
    private final List<Connection> taken; // unbounded
    private final ReentrantLock lock;
    private final Condition canGet;
    private final Condition canRelease;
    private final ConnectionFactory connectionFactory;

    public ConnectionPoolImpl(int capacity, ConnectionFactory factory) {
        this.capacity = capacity; // We won't increase the capacity unless needed
        this.connectionFactory = factory;

        this.available = new ArrayList<>();
        this.taken = new ArrayList<>();
        this.lock = new ReentrantLock();
        this.canGet = this.lock.newCondition();
        this.canRelease = this.lock.newCondition();

        seedConnections();
    }

    @Override
    public Connection getConnection() {
        // What should be the behaviour of this method? What happens if there is no connection?
        // check in available connection if any connection is available
        Connection connection = null;

        try {
            lock.lock();

            while (available.isEmpty()) {
                System.out.println("Awaiting for available");
                this.canGet.await(200, TimeUnit.MILLISECONDS);
            }

            connection = available.remove(available.size() - 1);
            connection.setState(ConnectionState.IN_USE);
            taken.add(connection);

            canRelease.signalAll();
            lock.unlock();

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return connection;
    }

    private void seedConnections() {
        // This can be an expensive operation in real life
        for (int count = 0; count < this.capacity; count++) {
            this.available.add(this.connectionFactory.getConnection());
        }
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        Objects.requireNonNull(connection);

        try {
            lock.lock();

            while (taken.isEmpty()) {
                System.out.println("releaseConnection - Awaiting for taken");
                canRelease.await(200, TimeUnit.MILLISECONDS);
            }

            Iterator<Connection> itr = this.taken.iterator();
            while (itr.hasNext()) {
                Connection con = itr.next();
                if (con.equals(connection)) {
                    System.out.println("Releasing connection: " + connection);
                    itr.remove();
                    addToAvailable(con);
                    return true;
                }
            }

            canGet.signalAll();
            lock.unlock();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public void expireConnections() {
        try {
            lock.lock();

            while (taken.isEmpty()) {
                System.out.println("expireConnections - Awaiting for taken");
                canRelease.await(200, TimeUnit.MILLISECONDS);
            }

            Iterator<Connection> itr = this.taken.iterator();
            while (itr.hasNext()) {
                Connection con = itr.next();
                if (System.currentTimeMillis() > con.getTtl()) {
                    System.out.println("Expiring connection: " + con);
                    itr.remove();
                    addToAvailable(con);
                }
            }

            canGet.signalAll();
            lock.unlock();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void addToAvailable(Connection connection) {
        connection.setState(ConnectionState.AVAILABLE);
        this.available.add(connection);
    }
}
