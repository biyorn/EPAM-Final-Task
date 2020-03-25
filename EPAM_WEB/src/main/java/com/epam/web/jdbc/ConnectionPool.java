package com.epam.web.jdbc;

import com.epam.web.jdbc.connection.ConnectionFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static final int MAX_NUMBER_OF_CONNECTIONS = 5;
    private static final AtomicReference<ConnectionPool> INITIALIZED = new AtomicReference<>();
    private static final ReentrantLock INSTANCE_LOCK = new ReentrantLock();
    private static final ReentrantLock CONNECTION_LOCK = new ReentrantLock();
    private static final Semaphore SEMAPHORE = new Semaphore(MAX_NUMBER_OF_CONNECTIONS, true);

    private Queue<Connection> connections = new LinkedList<>();
    private List<Connection> connectionsIssued = new ArrayList<>();

    public static ConnectionPool getInstance() {
        if (INITIALIZED.get() == null) {
            INSTANCE_LOCK.lock();
            try {
                if (INITIALIZED.get() == null) {
                    final ConnectionPool local = new ConnectionPool();
                    local.init();
                    INITIALIZED.set(local);
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INITIALIZED.get();
    }

    public void putConnection(Connection connection) {
        CONNECTION_LOCK.lock();
        try {
            if (connectionsIssued.contains(connection)) {
                connectionsIssued.remove(connection);
                connections.add(connection);
                SEMAPHORE.release();
            }
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }

    public Connection getConnection() throws InterruptedException {
        SEMAPHORE.acquire();
        CONNECTION_LOCK.lock();
        try {
            Connection local = connections.remove();
            connectionsIssued.add(local);
            return local;
        } finally {
            CONNECTION_LOCK.unlock();
        }
    }

    private void init() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        IntStream.range(0, MAX_NUMBER_OF_CONNECTIONS)
                .forEach(x -> connections.add(connectionFactory.getConnection()));
    }

    public void close() {
        try {
            connectionsIssued.forEach(this::putConnection);
            for (Connection connection : connections) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
