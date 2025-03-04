package br.com.agenda.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/agenda_bd", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
