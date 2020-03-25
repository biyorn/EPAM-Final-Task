package com.epam.web.jdbc.connection;

import com.epam.web.reader.ConfigReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "db.host";
    private static final String NAME = "db.name";
    private static final String PASS = "db.pass";

    public Connection getConnection() throws IllegalArgumentException {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String host = ConfigReader.read(URL);
            String name = ConfigReader.read(NAME);
            String pass = ConfigReader.read(PASS);

            return DriverManager.getConnection(host, name, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
