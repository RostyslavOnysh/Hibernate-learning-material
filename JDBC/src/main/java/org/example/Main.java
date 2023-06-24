package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties dbProperties = new Properties();
            dbProperties.put("user", "root");
            dbProperties.put("password", "13289812");
            DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db",dbProperties);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can`t load JDBC Driver for MySQL", e);
        } catch (SQLException throwables) {
            throw new RuntimeException("Can`t create connection to Database", throwables);
        }
    }
}
